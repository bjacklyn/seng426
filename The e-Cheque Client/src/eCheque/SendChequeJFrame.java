/*
 * SendChequeJFrame.java
 *
 * Created on May 4, 2007, 4:29 AM
 */

package eCheque;

/**
 *
 * @author  Saad
 */
//import com.Trendy.swing.plaf.TrendyLookAndFeel;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;

public class SendChequeJFrame extends javax.swing.JFrame {
    
    private String chequePath;
    private String recieverIP;
    private String cipherChequePath;
    private boolean selectChequeFlag;
    private EChequeRegisteration eChequeRegisterdUser; 
    
    /** Creates new form SendChequeJFrame */
    public SendChequeJFrame(EChequeRegisteration registerdUser) {
        try{
            //TrendyLookAndFeel tlf = new TrendyLookAndFeel();
            //tlf.setCurrentTheme( new com.Trendy.swing.plaf.Themes.TrendyOrangeTheme());
            //UIManager.setLookAndFeel(tlf);
        }
        catch(Exception e){
            
            //JOptionPane.showMessageDialog(null,"System Error", "can not found themes", JOptionPane.ERROR_MESSAGE);
        
        }    
        initComponents();
        eChequeRegisterdUser = registerdUser;
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
            JOptionPane.showMessageDialog( this, "Invalid File Name",
              "Invalid File Name", JOptionPane.ERROR_MESSAGE );
           return "";
         } 
        cipherChequePath = fileName.getName();
        JOptionPane.showMessageDialog(null,cipherChequePath);
        return fileName.getPath();
        
    }
   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTReciverIP = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jBselectChqPTP = new javax.swing.JButton();
        jBSendPTPCheque = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTReciverMail = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jBAttachZip = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTShellWindow = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Send e-Cheque");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PTP Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setText("Receiver URL / IP");

        jCheckBox1.setText("Time out  enable");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jBselectChqPTP.setText("select cheque");
        jBselectChqPTP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBselectChqPTPMouseClicked(evt);
            }
        });

        jBSendPTPCheque.setText("Send");
        jBSendPTPCheque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBSendPTPChequeMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jCheckBox1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jBselectChqPTP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jBSendPTPCheque, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTReciverIP)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTReciverIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCheckBox1)
                    .add(jBselectChqPTP)
                    .add(jBSendPTPCheque, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel2.setText("Receiver e-mail address");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eCheque/resources/icon-zip.gif"))); // NOI18N
        jButton3.setText("ZIP Cheque");

        jBAttachZip.setText("attach cheque");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jBAttachZip, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton3))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTReciverMail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 191, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTReciverMail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jBAttachZip)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTShellWindow.setColumns(20);
        jTShellWindow.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTShellWindow.setRows(5);
        jTShellWindow.setText(">>Status: disconnected");
        jScrollPane1.setViewportView(jTShellWindow);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-385)/2, (screenSize.height-417)/2, 385, 417);
    }// </editor-fold>//GEN-END:initComponents

    private void jBSendPTPChequeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSendPTPChequeMouseClicked
// TODO add your handling code here:
            if(selectChequeFlag){
                String hostName = jTReciverIP.getText();
                if(hostName.length()!=0){
                        try{
                            // genertate a session key.
                            AESCrypt aesKey128 = new AESCrypt();
                            Key sessionKey;
                            sessionKey = aesKey128.GenerateRandomAESKey();
                            Cipher aesCipher = aesKey128.initializeCipher(sessionKey,0);

                            InputStream in = new FileInputStream(chequePath);
                            JOptionPane.showMessageDialog(null,eChequeRegisterdUser.getEWalletLoaction());
                            OutputStream out = new FileOutputStream(eChequeRegisterdUser.getEWalletLoaction()+File.separator+"Out going"+File.separator+cipherChequePath); 
                            aesKey128.crypt(in,out,aesCipher);
                            in.close();
                            out.close();
                            chequePath =eChequeRegisterdUser.getEWalletLoaction()+File.separator+"Out going"+File.separator+cipherChequePath;
                            //Get the sever side digital certificate.
                            DigitalCertificate clientDC= new DigitalCertificate();
                            DigitalCertificateIO readClientDC = new DigitalCertificateIO();
                            clientDC = readClientDC.readDigitalCertificate(eChequeRegisterdUser.getEWalletLoaction()+File.separator+"Security Tools"+File.separator+eChequeRegisterdUser.getClientName()+"DigCert.edc");

                            JOptionPane.showMessageDialog(null,"Strating client");
                            //Start Server Thread.
                            Runnable threadingClient= new EchequeClient(jTShellWindow,clientDC,sessionKey,eChequeRegisterdUser.getEWalletLoaction(),
                                    chequePath,hostName,EChequeUtil.CLIENT_PORT);
                            Thread  client = new Thread(threadingClient);
                            client.start();



                        }
                        catch(Exception exp){
                            exp.printStackTrace();
                        }
                }
                else
                {
                    
                }
            }
            else
            {
                // you have to select cheque first
                JOptionPane.showMessageDialog(null,"Youhave to");
            }
            
                     
           
              
    }//GEN-LAST:event_jBSendPTPChequeMouseClicked

    private void jBselectChqPTPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBselectChqPTPMouseClicked
    // TODO add your handling code here:
    chequePath = getFileLoaction("Open Saved Cheque"); 
    
    if(chequePath.length()!=0){
        selectChequeFlag = true;
        
        
    }
       
    }//GEN-LAST:event_jBselectChqPTPMouseClicked
    
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAttachZip;
    private javax.swing.JButton jBSendPTPCheque;
    private javax.swing.JButton jBselectChqPTP;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTReciverIP;
    private javax.swing.JTextField jTReciverMail;
    private javax.swing.JTextArea jTShellWindow;
    // End of variables declaration//GEN-END:variables
    
}
