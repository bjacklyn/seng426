/*
 * ChequeJFrame.java
 *
 * Created on May 4, 2007, 2:31 AM
 * Modified on July 27, 2014
 */

/**
 *
 * @author  Saad
 * improved by Colin Knowles
 */

package eCheque;

import java.awt.BorderLayout;

import javax.crypto.Cipher;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.security.Key;
import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;

import java.awt.CardLayout;

import eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPasswordField;


public class ChequeJFrame extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8588787221908913914L;

	private JPanel contentPane;

	private EChequeRegisteration eChequeReg;
    private boolean newChequeFlag;
    private boolean loadChequeFlag = false;
    private String signPass;
    private ECheque oldCheque;
    
    private String amount;
    private String payTo;
    private String day;
    private String month;
    private String year;
    private boolean granteed;
    
    final static String DATE_FORMAT = "dd-MM-yyyy";
    
	public static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } 
	        catch (ParseException e) {
	            return false;
	        }
	}
	
    private String generateSerialNumber(){
        
        String serialNumber=""; 
        GregorianCalendar calender = new GregorianCalendar();
        
        serialNumber += calender.get(GregorianCalendar.DAY_OF_MONTH);
        serialNumber += calender.get(GregorianCalendar.MONTH);
        serialNumber += calender.get(GregorianCalendar.YEAR)+"  ";
        serialNumber += calender.get(GregorianCalendar.HOUR_OF_DAY);
        serialNumber += calender.get(GregorianCalendar.MINUTE)+" ";
        serialNumber += calender.get(GregorianCalendar.SECOND);
        serialNumber += calender.get(GregorianCalendar.MILLISECOND);
        
        return serialNumber;        
    }
    
    private String currentDate(){
        String currentDateInfo=""; 
        GregorianCalendar calender = new GregorianCalendar();
        
        currentDateInfo += calender.get(GregorianCalendar.DAY_OF_MONTH)+" - ";
        currentDateInfo += calender.get(GregorianCalendar.MONTH)+ 1 + " - ";
        currentDateInfo += calender.get(GregorianCalendar.YEAR);
        
        return currentDateInfo;
    }
    
    private String getFileLoaction(String dialogTitle){
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        fileChooser.setDialogTitle(dialogTitle);
        int result = fileChooser.showOpenDialog( this );
 
        if ( result == JFileChooser.CANCEL_OPTION )
             return "";
 
        File fileName = fileChooser.getSelectedFile();
 
         // display error if invalid
         if ( ( fileName == null ) || ( fileName.getName().equals( "" ) ) )
         {
        	 jErrorLabel.setForeground(Color.RED);
         	 jErrorLabel.setText("Error: Invaild Filename");
             return "";
         } // end if
        return fileName.getPath();
        
    }

    private String ChequeReferenceString(ECheque chq){
        
        String ref="";
        ref+= chq.getaccountNumber()+chq.getaccountholder()+chq.getbankname()+chq.getchequeNumber()+
              chq.getMoney()+chq.getcurrencytype()+chq.getearnday()+chq.getguaranteed()+chq.getpayToOrderOf();
       
        return ref;       
    }  

    private void getSign(){
        signPass="";
        CardLayout cl = (CardLayout)(panel_12.getLayout());
		cl.show(panel_12, "signPanel");
		jTuserSign.setEditable(true);
    }
    
	/**
	 * Create the frame.
	 */
	public ChequeJFrame(EChequeRegisteration registerUser) {
		setResizable(false);

		eChequeReg = registerUser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));
		
		jLDrawerName = new JLabel("Name:");
		panel_15.add(jLDrawerName);
		
		jLDate = new JLabel("Date:");
		panel_15.add(jLDate);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		jLBankName = new JLabel("Bank Name:");
		panel_13.add(jLBankName);
		
		jLAccountNum = new JLabel("Account #:");
		panel_13.add(jLAccountNum);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel jLabelPayTo = new JLabel("Pay to the order of:");
		panel_3.add(jLabelPayTo);
		
		jTPayTo = new JTextField();
		jTPayTo.setEditable(false);
		panel_3.add(jTPayTo);
		jTPayTo.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel jLabelAmount = new JLabel("Amount of Money:");
		panel_4.add(jLabelAmount);
		
		jTAmount = new JTextField();
		jTAmount.setEditable(false);
		jTAmount.setColumns(10);
		panel_4.add(jTAmount);
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		jLEarnDate = new JLabel("Earn Date (YYYY-MM-DD):");
		panel_5.add(jLEarnDate);
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 3, 0, 0));
		
		jTYear = new JTextField();
		jTYear.setEditable(false);
		jTYear.setText("YYYY");
		jTYear.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(jTYear);
		jTYear.setColumns(10);
		
		jTMonth = new JTextField();
		jTMonth.setEditable(false);
		jTMonth.setText("MM");
		jTMonth.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(jTMonth);
		jTMonth.setColumns(10);
		
		jTDay = new JTextField();
		jTDay.setEditable(false);
		jTDay.setText("DD");
		jTDay.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(jTDay);
		jTDay.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		contentPane.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		jCGranteed = new JCheckBox("Guaranteed");
		jCGranteed.setEnabled(false);
		jCGranteed.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(jCGranteed);
		
		jLSerialNumber = new JLabel("Serial");
		jLSerialNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(jLSerialNumber);
		
		JSeparator separator = new JSeparator();
		panel_10.add(separator);
		separator.setBackground(Color.BLACK);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_12 = new JPanel();
		panel_1.add(panel_12);
		panel_12.setLayout(new CardLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_12.add(panel_8, "menuPanel");
		
		JButton btnNew = new JButton("New");
		
		panel_8.add(btnNew);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnOpenActionPerformed(evt);
			}
		});
		panel_8.add(btnOpen);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});
		panel_8.add(btnSave);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnVerifyActionPerformed(evt);
			}
		});
		panel_8.add(btnVerify);
		panel_12.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_8, btnNew, btnOpen, btnSave, btnVerify}));
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnNewActionPerformed(evt);
			}
		});
		
		JPanel panel_14 = new JPanel();
		panel_12.add(panel_14, "signPanel");
		
		JLabel lblPassword = new JLabel("Enter Password:");
		panel_14.add(lblPassword);
		
		jTuserSign = new JPasswordField();
		jTuserSign.setEditable(false);
		jTuserSign.setColumns(16);
		panel_14.add(jTuserSign);
		
		jBOK = new JButton("Ok");
		jBOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBOKActionPerformed(evt);
			}
		});
		panel_14.add(jBOK);
		
		jBCancel = new JButton("Cancel");
		jBCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBCancelActionPerformed(evt);
			}
		});
		panel_14.add(jBCancel);
		
		CardLayout cl = (CardLayout)(panel_12.getLayout());
		cl.show(panel_12, "menuPanel");
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_9);
		
		jErrorLabel = new JLabel();
		panel_9.add(jErrorLabel);
		jErrorLabel.setForeground(Color.RED);
		jErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	private void btnNewActionPerformed(java.awt.event.ActionEvent evt){
		// Active cheque form
        jTAmount.setEditable(true);
        jTPayTo.setEditable(true);
        jTYear.setEditable(true);
        jTMonth.setEditable(true);
        jTDay.setEditable(true);
        jCGranteed.setEnabled(true);
        
        jErrorLabel.setText("");
        
        //set the user registeration data on the form
        jLDrawerName.setText("Name: " + eChequeReg.getClientName());
        jLBankName.setText("Bank Name: "+eChequeReg.getBankName());
        jLAccountNum.setText("Account no: "+eChequeReg.getAccountNumber());
        
        //Generate the cheque serial number
        jLSerialNumber.setText(generateSerialNumber()); 
        
        //Set check issue date
        jLDate.setText("Date: "+ currentDate());
        
        // clear any old data
        jTAmount.setText("");
        jTPayTo.setText("");
        jTDay.setText("");
        jTMonth.setText("");
        jTYear.setText("");
        jCGranteed.setSelected(false);
        
        newChequeFlag = true;
	}
	
	private void btnOpenActionPerformed(java.awt.event.ActionEvent evt){
		String chequePath;
        chequePath = getFileLoaction("Open Cheque");
        
        jErrorLabel.setText("");
        
        if(chequePath.length()!=0){
            oldCheque = new ECheque();
            EChequeIO loadCheque = new EChequeIO();
            
            try{
                // load cheque that already exist
                oldCheque = loadCheque.readcheque(chequePath);
                jLDrawerName.setText(oldCheque.getaccountholder());
                jLBankName.setText("Bank Name: "+oldCheque.getbankname());
                jLAccountNum.setText("Account no: "+oldCheque.getaccountNumber());

                // to get the earn date
                String []earnDate = oldCheque.getearnday().split(",");
                jTYear.setText(earnDate[0]);
                jTYear.setEditable(false);
                jTMonth.setText(earnDate[1]);
                jTMonth.setEditable(false);
                jTDay.setText(earnDate[2]);
                jTDay.setEditable(false);

                jTPayTo.setText(oldCheque.getpayToOrderOf());
                jTPayTo.setEditable(false);

                jTAmount.setText(oldCheque.getMoney());
                jTAmount.setEditable(false);

                if(oldCheque.getguaranteed()){
                    jCGranteed.setSelected(true);
                    jCGranteed.setEnabled(false);
                }
                jLSerialNumber.setText(oldCheque.getchequeNumber());
                jLDate.setText("Date: "+currentDate());
                
                loadChequeFlag = true;
                newChequeFlag = false;
            }
            catch(Exception exp){
            	jErrorLabel.setForeground(Color.RED);
            	jErrorLabel.setText("Security Warning: Invaild Cheque");
            }     
        }
	}
	
	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {		
      
        // get cheque info to save it.
        amount = jTAmount.getText();
        payTo = jTPayTo.getText();
        day = jTDay.getText();
        month = jTMonth.getText();
        year = jTYear.getText();
        if(jCGranteed.isSelected()){
            granteed = true;
        }
        else granteed = false;
         
        // validate the cheque data before saving it
        if(!newChequeFlag){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: You need to create a new cheque first");
        	return;
        }
        if(amount.length()==0){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: You can not write an empty cheque");
        	return;
        }
        
        float ps; 
        try { 
        	ps = Float.parseFloat(amount); 
        } catch(NumberFormatException e) { 
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: Invalid amount");
        	return;
        }
        
        if(ps<=0){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: Invalid amount");
        	return;
        }
        if(payTo.length()==0){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: You have to specify the cheque receiver");
        	return;
        }
        if(day.length()==0 || month.length()==0 || year.length()==0){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: Complete the earn date info");
        	return;
        }
        if(!isDateValid(day + "-" + month + "-" + year)){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User Error: Invalid date");
        	return;
        }
                    
        // Ask the user to enter his password to sign the cheque with his private key
        getSign();

	}
	
	private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {	     
	    String certificatePath;
	    if(loadChequeFlag){
	        certificatePath = getFileLoaction("Load Digital Certificate");   
	        if(certificatePath.length()!=0){
	           
	            //get the cheque reference sigantured. 
	            String sigatureRef= ChequeReferenceString(oldCheque);
	            
	            //Create Digital Certificate Object for verification
	            DigitalCertificate drawerDC = new DigitalCertificate();
	            
	            // Create Digital Certificate IO to load the Certificate
	            DigitalCertificateIO readDC = new DigitalCertificateIO();
	          
	            //Create a Digital signature object.
	            DigitalSignature verfiy = new DigitalSignature();
	              
	            try{
		            drawerDC = (DigitalCertificate)readDC.readDigitalCertificate(certificatePath);
		            
		            boolean verfiySign;
		            verfiySign =verfiy.verifySignature(oldCheque.getdrawersiganure(),sigatureRef,drawerDC.getpublicKey());
	            
	                if(verfiySign){
	                	jErrorLabel.setForeground(Color.GREEN);
	                	jErrorLabel.setText("e-Cheque Clear: The signature is valid");
	                }
	                else{
	                	jErrorLabel.setForeground(Color.RED);
	                	jErrorLabel.setText("e-Cheque Not Clear: The signature is not valid");
	                }
	            }
	            catch(Exception exp){
	            	jErrorLabel.setForeground(Color.RED);
	            	jErrorLabel.setText("Unknown Error: Digital Certificate corrupted");
	            }
	        }
	        else{
	        	jErrorLabel.setForeground(Color.RED);
            	jErrorLabel.setText("User Error: You have to load the drawer Digital Certificate");
	        }
	    }
	}
	
	private void jBOKActionPerformed(java.awt.event.ActionEvent evt) {
		
		char password[] =jTuserSign.getPassword();
        String passTemp="";
        if(password.length >=8 && password.length <16){
        
            for(int i=0; i<password.length; i++){
                passTemp+=password[i];
            }
        
            if(password.length < 16){
            int pad = 16 - password.length;
                for(int i=0; i<pad; i++){
                   passTemp+=password[i];
                }  
            } 
        }
        signPass = passTemp;
        
		if (signPass.hashCode()!=eChequeReg.getPasword()){
			
			jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("User error: Incorrect password");
        	
        	// Restore menu
        	jBCancelActionPerformed(null);
        	return;
        }
            
        ECheque chequeObj = new ECheque();
        try{
           //create AES Key with user password and cipher  
           AESCrypt aesCrypt = new AESCrypt();
           Key AES128 = aesCrypt.inilizeAESKeyByPassword(signPass);
           Cipher cipher = aesCrypt.initializeCipher(AES128,1);
           InputStream in = new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\Private Key.key");
           OutputStream out = new FileOutputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key"); 
           
           // decrypt the private key with the AES key and delete the plain key
           aesCrypt.crypt(in,out,cipher);
           in.close();
           out.close();
           ObjectInputStream objIn = new ObjectInputStream (new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key"));
           
           // load the user private key.
           PrivateKey privKey = (PrivateKey)objIn.readObject();
           objIn.close();
           
           // delete the un secure key.
           File control = new File(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key");
           control.delete();
           
           // Fill the Cheque Data
           chequeObj.setaccountNumber(eChequeReg.getAccountNumber());
           chequeObj.setaccountholder(eChequeReg.getClientName());
           chequeObj.setbankname(eChequeReg.getBankName());
           chequeObj.setchequeNumber(jLSerialNumber.getText());
           chequeObj.setamountofMony(jTAmount.getText());
           chequeObj.setcurrencytype("US $");
           chequeObj.setamountofMony(jTAmount.getText());
           chequeObj.setearnday(year+","+month+","+day);
           chequeObj.setpayToOrderOf(payTo);
           chequeObj.setguaranteed(granteed);
           
           // get cheque reference string and sign it.
           String chequeRef = ChequeReferenceString(chequeObj);
           DigitalSignature digitalSign = new DigitalSignature();
           chequeObj.setdrawersiganure(digitalSign.sign(chequeRef,privKey));
           
           //Save the cheque after you sign it
           EChequeIO drawCheque = new EChequeIO();
           drawCheque.savecheque(chequeObj,eChequeReg.getEWalletLoaction()+"\\My Cheques\\"+chequeObj.getchequeNumber()+".sec");
           jErrorLabel.setForeground(Color.GREEN);
           jErrorLabel.setText("Cheque Saved!");
           
           // Clear fields and create a new cheque
           newChequeFlag = false;
           jTAmount.setEditable(false);
           jTDay.setEditable(false);
           jTMonth.setEditable(false);
           jTPayTo.setEditable(false);
           jTYear.setEditable(false);
        }
        catch(Exception exp){
        	jErrorLabel.setForeground(Color.RED);
        	jErrorLabel.setText("System error: " + exp.getMessage());
        }
        
        // Bring back menu buttons
        jBCancelActionPerformed(null);
	}
	
	private void jBCancelActionPerformed(java.awt.event.ActionEvent evt) {	
		jTuserSign.setEditable(false);
		jTuserSign.setText("");
		CardLayout cl = (CardLayout)(panel_12.getLayout());
		cl.show(panel_12, "menuPanel");
    	return;
	}

	
	private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBOK;
    private javax.swing.JCheckBox jCGranteed;
    private javax.swing.JLabel jLAccountNum;
    private javax.swing.JLabel jLBankName;
    private javax.swing.JLabel jLDate;
    private JLabel jLEarnDate;
    private javax.swing.JLabel jLDrawerName;
    private javax.swing.JLabel jLSerialNumber;
    private javax.swing.JLabel jErrorLabel;
    private javax.swing.JPanel panel_12;
    private javax.swing.JTextField jTAmount;
    private javax.swing.JTextField jTDay;
    private javax.swing.JTextField jTMonth;
    private javax.swing.JTextField jTPayTo;
    private javax.swing.JTextField jTYear;
    private javax.swing.JPasswordField jTuserSign;

}
