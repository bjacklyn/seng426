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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchequeServer implements Runnable {

	/** Creates a new instance of Echqueserver */
	private Socket ServerConnection;
	private ObjectInputStream socketInputObject;
	private ObjectOutputStream socketOutputObject;
	private InputStream socketInput;
	private OutputStream socketOutput;

	public EchequeServer(Socket socket) {
		ServerConnection = socket;
	}

	private void getsocketStream() throws IOException {
		socketOutput = ServerConnection.getOutputStream();
		socketOutput.flush();
		socketInput = ServerConnection.getInputStream();

		socketOutputObject = new ObjectOutputStream(
				ServerConnection.getOutputStream());
		socketOutputObject.flush();
		socketInputObject = new ObjectInputStream(
				ServerConnection.getInputStream());
	}

	private void processConnection() {
		boolean sessionDone = false;
		BankMode mode;
		if (!sessionDone) {
			try {
				socketInputObject.readObject();
				mode = (BankMode) socketInputObject.readObject();
			} catch(IOException ioe) {
				ioe.printStackTrace();
				return;
			} catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return;
			}
			if (mode == BankMode.REGISTER) {
				registerClientInfo();
			}
			if (mode == BankMode.DEPOSIT) {
				depositCheque();
			}
			if (mode == BankMode.CANCEL) {
				cancelCheque();
			}
		}
	}

	private void registerClientInfo() {
		EChequeRegisteration registerClient = null;
		try {
			registerClient = (EChequeRegisteration) socketInputObject
					.readObject();

		} catch (IOException ioe) {
			System.err
					.println("IOException while reading EChequeRegistration from socket");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ClassNotFoundException while reading EChequeRegistration from socket");
			cnfe.printStackTrace();
		}

		DigitalCertificate registDC = null;
		try {
			registDC = (DigitalCertificate) socketInputObject.readObject();

		} catch (IOException ioe) {
			System.err
					.println("IOException while reading EChequeRegistration from socket");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ClassNotFoundException while reading EChequeRegistration from socket");
			cnfe.printStackTrace();
		}

		if (registerClient != null && registDC != null) {
			// get user account ID
			String accountID = "'" + registerClient.getAccountNumber() + "',";
			String cerit = "'" + registerClient.getClientName() + "DC.edc"
					+ "',";
			String clientName = "'" + registerClient.getClientName() + "',";

			String registerStat = "insert into accounts(accountID,clientName,dcPath,balance) values("
					+ accountID + clientName + cerit + 100000 + ")";

			// starting database
			EChequeDB chqDB = new EChequeDB();
			chqDB.runDB(1, registerStat);

			try {
				// store client digital certificate
				DigitalCertificateIO.saveDC(registDC, "Bank" + File.separator
						+ registerClient.getClientName() + "DC.edc");
				socketOutputObject.writeObject("registeration complete");
				socketOutputObject.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void depositCheque() {

		String depositResult = "";
		// read cheque from socket
		ECheque recivedCehq = null;
		try {
			recivedCehq = (ECheque) socketInputObject.readObject();

		} catch (IOException ioe) {
			System.err
					.println("IOException while reading ECheque from socket");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ClassNotFoundException while reading ECheque from socket");
			cnfe.printStackTrace();
		}
		
		// read deposit Account number.
		String depositAccount = null;
		try {
			depositAccount = (String) socketInputObject.readObject();

		} catch (IOException ioe) {
			System.err
					.println("IOException while reading Deposit Account from socket");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ClassNotFoundException while reading Deposit Account from socket");
			cnfe.printStackTrace();
		}
		
		if (recivedCehq != null && depositAccount != null) {
	
			// check the withdraw account.
			String withdrawStat = "Select balance from accounts where accountID ="
					+ recivedCehq.getaccountNumber();
			String cheqUpdate = "";
			double[] balanceValue = new double[1];
	
			EChequeDB chqDB = new EChequeDB();
			if (chqDB.runDB(0, withdrawStat, balanceValue)) {
				// check if the balance sufficient
				double chequeMoney = Double.parseDouble(recivedCehq.getMoney());
				if (chequeMoney <= balanceValue[0]) {
					// cheque that the cheque is not canceld
					withdrawStat = "Select * from cancelledCheque where accountID ='"
							+ recivedCehq.getaccountNumber()
							+ "'and chequeID ='"
							+ recivedCehq.getchequeNumber() + "'";
					if (!chqDB.runDB(withdrawStat, 0)) {
						withdrawStat = "Select * from eChequeOut where chequeID='"
								+ recivedCehq.getchequeNumber()
								+ "'and accountID='"
								+ recivedCehq.getaccountNumber() + "'";
						if (!chqDB.runDB(withdrawStat, 0)) {
							withdrawStat = "Update accounts set balance = balance -"
									+ chequeMoney
									+ "where accountID ="
									+ recivedCehq.getaccountNumber();
							chqDB.runDB(1, withdrawStat);
							withdrawStat = "Update accounts set balance = balance +"
									+ chequeMoney
									+ "where accountID ="
									+ depositAccount;
							chqDB.runDB(1, withdrawStat);
	
							// update cheque out and in table
							cheqUpdate = "Insert into eChequeOut(chequeID, accountID, balance) values("
									+ "'"
									+ recivedCehq.getchequeNumber()
									+ "','"
									+ recivedCehq.getaccountNumber()
									+ "',"
									+ chequeMoney + ")";
							chqDB.runDB(1, cheqUpdate);
	
							cheqUpdate = "Insert into eChequeIN(chequeID, accountID, balance) values("
									+ "'"
									+ recivedCehq.getchequeNumber()
									+ "','"
									+ recivedCehq.getaccountNumber()
									+ "',"
									+ chequeMoney + ")";
							chqDB.runDB(1, cheqUpdate);
	
							// report the deposit result
							depositResult = "Your account recieves the deposit cheque\nyour balance incremented by"
									+ recivedCehq.getMoney();
						} else {
							// report the deposit result
							depositResult = "This cheque is already deposit, sorry we can not deposit it twice";
	
						}
					} else {
						// report the deposit result
						depositResult = "This cheque is canceled by the drawer\nSorry we can not deposit it";
					}
				} else {
					// report the deposit result
					depositResult = "Drawer Balance is not sufficient for withdrawing\n";
				}
			} else {
				depositResult = "This cheque is not belong to one of our customers\nyou have to connect to the drawer bank server";
			}
			
			try {
				socketOutputObject.writeObject(depositResult);
				socketOutputObject.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cancelCheque() {
		ECheque recivedCehq = null;
		try {
			recivedCehq = (ECheque) socketInputObject.readObject();

		} catch (IOException ioe) {
			System.err
					.println("IOException while reading ECheque from socket");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ClassNotFoundException while reading ECheque from socket");
			cnfe.printStackTrace();
		}
		
		String cancelChequeStat;
		cancelChequeStat = "insert into cancelledCheque (accountID,chequeID) values('"
				+ recivedCehq.getaccountNumber()
				+ "','"
				+ recivedCehq.getchequeNumber() + "')";
		EChequeDB chqDB = new EChequeDB();
		
		try {
			if (chqDB.runDB(1, cancelChequeStat)) {
				socketOutputObject.writeObject("cheque cancelled done");
				socketOutputObject.flush();

			} else {
				socketOutputObject.writeObject("sorry cheque not cancelled");
				socketOutputObject.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			socketInput.close();
			socketOutput.close();
			socketInputObject.close();
			socketOutputObject.close();
			ServerConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RunServer() {
		try {
			getsocketStream();
			processConnection();
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		finally {
			closeConnection();
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