/*
 * Echqueserver.java
 *
 * Created on April 27, 2007, 8:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Basel
 */
package eCheque;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Echqueserver implements Runnable {

	/** Creates a new instance of Echqueserver */
	private ServerSocket server;
	private Socket ServerConnection;
	private ObjectInputStream socketInputObject;
	private ObjectOutputStream socketOutputObject;
	private InputStream socketInput;
	private OutputStream socketOutput;
	private JTextArea screenShell;
	private DigitalCertificate serverCerit;
	private String walletPath;
	private PrivateKey privKey;

	public Echqueserver(JTextArea screen, DigitalCertificate DC, String wPath,
			PrivateKey privateKey, ServerSocket serverSockect) {

		screenShell = screen;
		serverCerit = DC;
		walletPath = wPath;
		privKey = privateKey;
		server = serverSockect;
	}

	// private void startServer() throws Exception{

	// server = new ServerSocket(portID);
	// }

	 private boolean acceptConnection()throws IOException{
	     try {
	    	 ServerConnection = server.accept();
	     }
	     catch(SocketTimeoutException ste)
	     {
	    	 return true;
	     }
		 return false;
	} 

	private void getsocketStream() throws Exception {
		socketOutput = ServerConnection.getOutputStream();
		socketOutput.flush();
		socketInput = ServerConnection.getInputStream();

		socketOutputObject = new ObjectOutputStream(
				ServerConnection.getOutputStream());
		socketOutputObject.flush();
		socketInputObject = new ObjectInputStream(
				ServerConnection.getInputStream());
	}

	private void ProcessConnection() throws IOException,
			ClassNotFoundException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			InvalidKeyException, Exception {

		boolean sessionDone = false;
		DigitalCertificate clientCerit;

		if (!sessionDone) {

			// exchange digital certificates
			clientCerit = (DigitalCertificate) socketInputObject.readObject();
			socketOutputObject.writeObject(serverCerit);
			socketOutputObject.flush();

			// get the wraeped key and uwraped it
			byte[] wrappedKey;
			Key sessionKey;
			int keyLength;

			// read the session key form the socket
			keyLength = socketInputObject.readInt();
			wrappedKey = new byte[keyLength];
			socketInput.read(wrappedKey);

			// decrypt the session key with the user private key.
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.UNWRAP_MODE, privKey);
			sessionKey = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

			//
			Calendar currTime = new GregorianCalendar();
			String cheqName = "";
			cheqName += currTime.get(Calendar.YEAR);
			cheqName += currTime.get(Calendar.MONTH);
			cheqName += currTime.get(Calendar.DAY_OF_MONTH);
			cheqName += currTime.get(Calendar.HOUR_OF_DAY);
			cheqName += currTime.get(Calendar.MILLISECOND);

			// read the cheque from the socket
			FileOutputStream chqIn = new FileOutputStream(walletPath
					+ File.separator + "In Coming" + File.separator + cheqName
					+ ".cry");
			byte[] buffer = new byte[1024];
			int numread;
			while ((numread = socketInput.read(buffer)) >= 0) {
				chqIn.write(buffer, 0, numread);
			}

			chqIn.close();

			// validate the received cheque.
			File incoming = new File(walletPath + File.separator
					+ "In Coming" + File.separator + cheqName + ".cry");
			
			InputStream in = new FileInputStream(incoming);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		
			// create AES object to decrypt the received cheque
			AESCrypt aesObj = new AESCrypt();
			Cipher aesCipher = aesObj.initializeCipher(sessionKey, 1);
			aesObj.crypt(in, out, aesCipher);
			in.close();
			out.close();
			
			// verify the cheque siganture using the sender public key.
			DigitalSignature digitalSign = new DigitalSignature();

			// load decrypted chequeObject.
			ECheque recivedChq = new ECheque();
			ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
			recivedChq = (ECheque) objectIn.readObject();
			objectIn.close();
			String chqSign = ChequeReferenceString(recivedChq);

			boolean verifySign = digitalSign.verifySignature(
					recivedChq.getdrawersiganure(), chqSign,
					clientCerit.getpublicKey());
			if (verifySign) {
				JOptionPane.showMessageDialog(null, "The signature is vaild",
						"e-Cheque Clear", JOptionPane.INFORMATION_MESSAGE);
		
				FileOutputStream fos = new FileOutputStream(walletPath + File.separator
						+ "My Cheques" + File.separator + cheqName + ".sec");
				fos.write(out.toByteArray());
				fos.close();
			} else {
				JOptionPane.showMessageDialog(null,
						"The signature is not vaild", "e-Cheque not Clear",
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	private void closeConnection() {
		try {

			socketInput.close();
			socketOutput.close();
			socketInputObject.close();
			socketOutputObject.close();
			ServerConnection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

	}

	public void RunServer() {
		
		boolean timeout = false;
		
		try {

			screenShell.append("\n>>Status: Starting The Sever");
			// startServer();
			screenShell.append("\n>>Status: Wating for connection");
			timeout = acceptConnection();
	        if(timeout)
	        {
	        	 screenShell.append("\n>>Status: Timed out. Please try again");
	        	 return;
	        }
			screenShell.append("\n>>Status: connection accepted");
			getsocketStream();
			screenShell.append("\n>>Status: Socket I/O complete");
			screenShell.append("\n>>Status: processing started");
			ProcessConnection();
			screenShell.append("\n>>Status: process complete");
		}

		catch (Exception error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
			error.printStackTrace();
		}

		finally {
			if(!timeout)
	    	{
	    		closeConnection();
	    	}
		}
	}

	private String ChequeReferenceString(ECheque chq) {

		String ref = "";
		ref += chq.getaccountNumber() + chq.getaccountholder()
				+ chq.getbankname() + chq.getchequeNumber() + chq.getMoney()
				+ chq.getcurrencytype() + chq.getearnday()
				+ chq.getguaranteed() + chq.getpayToOrderOf();

		return ref;
	}

	public void run() {

		RunServer();
	}

}