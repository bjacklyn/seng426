/*
 * RegistrationFrame.java
 *
 * Created on May 3, 2007, 6:01 PM
 */

package eCheque;

/**
 *
 * @author  Saad
 */
//import com.Trendy.swing.plaf.TrendyLookAndFeel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.text.NumberFormatter;

import java.security.*;
import java.text.NumberFormat;
import java.io.File;

public class RegistrationJFrame extends javax.swing.JFrame {
    
   private boolean pathFlag;
   private String eWalletPath;
 
   /** Creates new form RegistrationFrame */
   public RegistrationJFrame() {
        try{
            //TrendyLookAndFeel tlf = new TrendyLookAndFeel();
            //tlf.setCurrentTheme( new com.Trendy.swing.plaf.Themes.TrendyOrangeTheme());
            //UIManager.setLookAndFeel(tlf);
        }
        catch(Exception e){
            
            //JOptionPane.showMessageDialog(null,"System Error", "can not found themes", JOptionPane.ERROR_MESSAGE);
        
        }
        
        pathFlag = false;
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
    	jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTBankName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTBankURLIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTClientName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTAccountNo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTIssuerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTDCURLIP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCValidation = new javax.swing.JComboBox();
        jCSubject = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTUserName = new javax.swing.JTextField();
        jTPassword = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jBeWallet = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTPassword2 = new javax.swing.JPasswordField();
        jBRFRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register");
        setResizable(false);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Client Form", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Financial Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51)));
        jLabel1.setText("Bank Name");

        jLabel2.setText("URL/IP");

        jLabel3.setText("Client Name");

        jLabel4.setText("Account no.");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(8, 8, 8)
                        .add(jTBankName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTClientName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(16, 16, 16)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTBankURLIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTAccountNo)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTBankName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTBankURLIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTAccountNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTClientName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Digital Certificate Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel5.setText("Issuer");

        jLabel6.setText("URL/IP");

        jLabel7.setText("Subject");

        jLabel8.setText("Validation");

        jCValidation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3 months", "6 months", "9 months", "12 months" }));

        jCSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "e-cheque services", "e-stocks", "other e-banking" }));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(jLabel7))
                .add(24, 24, 24)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jCSubject, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTIssuerName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(jLabel6))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(jLabel8)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jCValidation, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTDCURLIP, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTIssuerName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTDCURLIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jCValidation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCSubject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Security Sittings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        jLabel9.setText("User name");

        jLabel10.setText("Password");

        jBeWallet.setText("Create E-Wallet");
        jBeWallet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBeWalletMouseClicked(evt);
            }
        });

        jLabel11.setText("re-Password");
        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel9)
                    .add(jLabel10)
                    .add(jLabel11))
                .add(14, 14, 14)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTPassword2)
                    .add(jTPassword)
                    .add(jTUserName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .add(57, 57, 57)
                .add(jBeWallet)
                .add(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(jTUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(9, 9, 9)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel10)
                            .add(jTPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTPassword2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jBeWallet, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jBRFRegister.setText("Register");
        jBRFRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBRFRegisterMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .add(jBRFRegister, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(156, 156, 156))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBRFRegister, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-458)/2, (screenSize.height-482)/2, 458, 482);
   
        
    }// </editor-fold>//GEN-END:initComponents
    
    private String getWalletLoaction(String dialogTitle){
    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);      
        
        fileChooser.setDialogTitle(dialogTitle);
    
        int result = fileChooser.showOpenDialog( this );
 
        if ( result == JFileChooser.CANCEL_OPTION )
             return "";
 
        File fileName = fileChooser.getSelectedFile();
 
         // display error if invalid
         if ( ( fileName == null ) || ( fileName.getName().equals( "" ) ) )
         {
            JOptionPane.showMessageDialog( this, "Invalid File Name",
              "Invalid File Name", JOptionPane.ERROR_MESSAGE );
           return "";
         } // end if
        
        return fileName.getPath();
        
    }
    private void jBeWalletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBeWalletMouseClicked
// TODO add your handling code here:
        
        eWalletPath = getWalletLoaction("Set e-Wallet Location");
        File wallet;
        wallet= new File(eWalletPath+File.separator+"In Coming");
        wallet.mkdirs();
        wallet= new File(eWalletPath+File.separator+"Out going");
        wallet.mkdirs();
        wallet = new File(eWalletPath+File.separator+"Security Tools ");
        wallet.mkdirs();
        wallet = new File(eWalletPath+File.separator+"My Cheques ");
        wallet.mkdirs();
        wallet = new File(eWalletPath+File.separator+"History ");
        wallet.mkdirs();
        
        pathFlag = true;        
    }//GEN-LAST:event_jBeWalletMouseClicked
    private void jBRFRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBRFRegisterMouseClicked
// TODO add your handling code here:
        
        String bankName;
        String bankURL;
        String clientName;
        String accountNumber;
        String digitalCIssuer;
        String digitalCURL;
        String userName;
        char [] password;
        char []password2;
        int userNameCode;
        int passwordCode;
        
        
        bankName = jTBankName.getText();
        bankURL = jTBankURLIP.getText();
        clientName = jTClientName.getText();
        accountNumber = jTAccountNo.getText();
        digitalCIssuer = jTIssuerName.getText();
        digitalCURL = jTDCURLIP.getText();
        userName = jTUserName.getText();
        password =  jTPassword.getPassword();
        password2 = jTPassword2.getPassword();
        
        if(bankName.length()!=0){
            
                if(bankURL.length()!=0){
                    
                    if(clientName.length()!=0){
                        
                    	// Sanitize the account number 
                    	boolean validAccount = true;
                    	int accountNum = 0;
                    	try {
                    		accountNum = Integer.valueOf(accountNumber);
                    	}
                    	catch(NumberFormatException nfe)
                    	{
                    		validAccount = false;
                    	}
                    	
                            if(accountNum > 0 &&  validAccount){
                            	
                                if(digitalCIssuer.length()!=0){
                                    
                                    if(digitalCURL.length()!=0){
                                        
                                            if(userName.length()!=0){
                                                  String passTemp="";
                                                  String passTemp2="";
                                                        
                                                  for(int i=0; i<password.length; i++){
                                                       passTemp+=password[i];
                                                       passTemp2+=password2[i];
                                                  }
                                                if(passTemp.compareTo(passTemp2)==0){
                                                      
                                                
                                                if(password.length >= 8 && password.length <16)
                                                {
                                                    if(pathFlag){
                                                        
                                                        // prepare the user name and password.
                                                        userNameCode = userName.hashCode();
                                                        
                                                        
                                                        
                                                        if(password.length<16){
                                                          int pad = 16 - password.length;
                                                          
                                                          for(int i=0; i<pad; i++){
                                                            passTemp+=password[i];
                                                          }  
                                                        }
                                                        passwordCode = passTemp.hashCode();
                                                        
                                                        //For Test: JOptionPane.showMessageDialog(null,passTemp);
                                                        
                                                        //create a registeration object to save user registeration data
                                                        EChequeRegisteration registerationObj = new EChequeRegisteration();
                                                        registerationObj.setBankName(bankName);
                                                        registerationObj.setBankAddress(bankURL);
                                                        registerationObj.setClientName(clientName);
                                                        registerationObj.setAccountNumber(accountNumber);
                                                        registerationObj.setEWalletLoaction(eWalletPath);
                                                        registerationObj.setUsername(userNameCode);
                                                        registerationObj.setPasword(passwordCode);
                                                        
                                                        
                                                        try {
                                                            ObjectOutputStream outObj; 
                                                            // create the user digital certificate (digital identity)
                                                            RSAGenerator keyGen = new RSAGenerator();
                                                            KeyPair RSAKeys = keyGen.GenerateRSAKeys();
                                                            
                                                            // encrypt private key with user password. 
                                                            outObj = new ObjectOutputStream(new FileOutputStream(eWalletPath+File.separator+"Security Tools"+File.separator+"privateKey.key"));
                                                            outObj.writeObject(RSAKeys.getPrivate());
                                                            outObj.close();
                                                            
                                                            //create AES Key with user password and cipher  
                                                            AESCrypt aesCrypt = new AESCrypt();
                                                            Key AES128 = aesCrypt.inilizeAESKeyByPassword(passTemp);
                                                            Cipher cipher = aesCrypt.initializeCipher(AES128,0);
                                                            InputStream in = new FileInputStream(eWalletPath+File.separator+"Security Tools"+File.separator+"privateKey.key");
                                                            OutputStream out = new FileOutputStream(eWalletPath+File.separator+"Security Tools"+File.separator+"Private Key.key"); 
                                                            
                                                            // encrypt the private key with the AES key and delete the plain key
                                                            aesCrypt.crypt(in,out,cipher);
                                                            in.close();
                                                            out.close();
                                                            File control = new File(eWalletPath+File.separator+"Security Tools"+File.separator+"privateKey.key");
                                                            control.delete();
                                                            
                                                            // create Digital certificate object.
                                                            DigitalCertificate dcObj = new DigitalCertificate();
                                                            dcObj.setHolderName(clientName);
                                                            dcObj.setIssuer(digitalCIssuer);
                                                            dcObj.setSubject(jCSubject.getSelectedItem().toString());
                                                            dcObj.setValidFrom(jCValidation.getSelectedItem().toString());
                                                            dcObj.setValidTo(jCValidation.getSelectedItem().toString());
                                                            dcObj.setPublicKey(RSAKeys.getPublic());
                                                            
                                                            // save the user digital certificate
                                                            DigitalCertificateIO.saveDC(dcObj,eWalletPath+File.separator+"Security Tools"+File.separator+registerationObj.getClientName()+"DigCert.edc");
                                                            
                                                            //Connect to the bank server to activate the e-cheque account.
                                                            Runnable client = new EchequeClient(EChequeUtil.SERVER_PORT,BankMode.REGISTER,registerationObj.getBankAddress(),registerationObj,
                                                                    dcObj);
                                                            Thread t = new Thread(client);
                                                            t.start();
                                                            //JOptionPane.showMessageDialog(null,"Registeration Done\n\tYou have to restart your system","Confirm",
                                                                    //JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                        catch(IOException exp){
                                                            JOptionPane.showMessageDialog(null,"Access Disk Media is not allowed", "System Error", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                        catch (NoSuchAlgorithmException exp){
                                                            JOptionPane.showMessageDialog(null,"ONE of your Java Securiy Feature not available", "Platform Error", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                        catch (Exception exp){
                                                            JOptionPane.showMessageDialog(null,exp.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                        
                                                    }
                                                    else {
                                                        JOptionPane.showMessageDialog(null,"You have to create your e-wallet", "User Error", JOptionPane.ERROR_MESSAGE);
                                                            
                                                    }
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Your password should be between 8 - 15 characters", "User Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Passwords not match ", "User Error", JOptionPane.ERROR_MESSAGE);  
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null,"User name can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Certificate issuer URl or IP can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } 
                                else{
                                    JOptionPane.showMessageDialog(null,"Certificate issuer can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
                               }
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Account number must be a positive integer value", "User Error", JOptionPane.ERROR_MESSAGE);
       
                            }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Client name can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
       
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Bank URL or IP address can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        else{
            JOptionPane.showMessageDialog(null,"Bank Name can not be empty", "User Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jBRFRegisterMouseClicked
    
    /**
     * @param args the command line arguments
     */
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBRFRegister;
    private javax.swing.JButton jBeWallet;
    private javax.swing.JComboBox jCSubject;
    private javax.swing.JComboBox jCValidation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTAccountNo;
    private javax.swing.JTextField jTBankName;
    private javax.swing.JTextField jTBankURLIP;
    private javax.swing.JTextField jTClientName;
    private javax.swing.JTextField jTDCURLIP;
    private javax.swing.JTextField jTIssuerName;
    private javax.swing.JPasswordField jTPassword;
    private javax.swing.JPasswordField jTPassword2;
    private javax.swing.JTextField jTUserName;
    // End of variables declaration//GEN-END:variables
    
}