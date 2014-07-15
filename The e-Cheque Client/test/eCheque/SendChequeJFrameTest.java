package eCheque;

import java.io.File;

import org.junit.Before;
import org.uispec4j.Button;
import org.uispec4j.PasswordField;
import org.uispec4j.TextBox;
import org.uispec4j.Trigger;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.BasicHandler;
import org.uispec4j.interception.FileChooserHandler;
import org.uispec4j.interception.MainClassAdapter;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

import eCheque.ElectronicChequeJFrame;

/**
 * Test class for SendChequeJFrame
 * Prerequisites: Client must already be configured and username "testUser" with password "password"
 * must exist
 */
public class SendChequeJFrameTest extends UISpecTestCase {

	private static final String USERNAME = "testUser";
	private static final String PASSWORD = "password";
	private static final String CHEQUE_FILE = "validcheque.sec";
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		setAdapter(new MainClassAdapter(ElectronicChequeJFrame.class, new String[0]));
	}
	
	/**
	 * Test that a cheque cannot be sent if the receiver IP is left blank
	 */
	public void testBlankReceiverHostNameField()
	{
		Window mainWindow = getMainWindow();
		Button login = mainWindow.getButton("login");
		Button send = mainWindow.getButton("send");
		TextBox username = mainWindow.getTextBox("username");
		PasswordField password = mainWindow.getPasswordField("password");

		// Login the user
		username.setText(USERNAME);
		password.setPassword(PASSWORD);
		
		WindowInterceptor.init(login.triggerClick()).process(BasicHandler.init().
       		 assertContainsText("Welcome " + USERNAME).triggerButtonClick("OK")).run();
		
		// Click on the send cheque button to open the send cheque frame
		WindowInterceptor.init(send.triggerClick()).process(new WindowHandler() {
			public Trigger process(Window window) {
		           
				Button select = window.getButton("select");
				Button send = window.getButton("send");
				
				// Select a valid cheque file
				File chequeFile = new File(CHEQUE_FILE);
				WindowInterceptor.init(select.triggerClick()).process(FileChooserHandler.init().select(chequeFile));
				
				// Check that a message is displayed if no receiver ip is selected
				WindowInterceptor.init(send.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("You must enter a reciever IP.").triggerButtonClick("OK")).run();

	            return window.getButton("OK").triggerClick();
	        }
		});
	}
	
	/**
	 * Test that a cheque cannot be sent if a non-cheque file or no file is selected
	 */
	public void testSelectChequeFile()
	{
		Window mainWindow = getMainWindow();
		Button login = mainWindow.getButton("login");
		Button send = mainWindow.getButton("send");
		TextBox username = mainWindow.getTextBox("username");
		PasswordField password = mainWindow.getPasswordField("password");

		// Login the user
		username.setText(USERNAME);
		password.setPassword(PASSWORD);
		
		WindowInterceptor.init(login.triggerClick()).process(BasicHandler.init().
       		 assertContainsText("Welcome " + USERNAME).triggerButtonClick("OK")).run();
		
		// Click on the send cheque button to open the send cheque frame
		WindowInterceptor.init(send.triggerClick()).process(new WindowHandler() {
			public Trigger process(Window window) {
		           
				Button select = window.getButton("select");
				Button send = window.getButton("send");
				
				// Check that a message is displayed if a no cheque file is selected
				WindowInterceptor.init(send.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("You must select a cheque file.").triggerButtonClick("OK")).run();

				// Select a non cheque file
				File nonChequeFile = new File("notacheque.jpg");
				WindowInterceptor.init(select.triggerClick()).process(
						FileChooserHandler.init().assertAcceptsFilesOnly().select(nonChequeFile));
				
				// Check that a message is displayed if a non cheque file is selected
				WindowInterceptor.init(send.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("You must select a cheque file.").triggerButtonClick("OK")).run();

				// Close the window
				window.dispose();
				
				// Return a trigger that does nothing
	            return new Trigger() {
					public void run() throws Exception {}
				};
	        }
		});
	}
}
