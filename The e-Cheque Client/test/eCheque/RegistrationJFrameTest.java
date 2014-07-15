package eCheque;

import org.junit.Before;
import org.uispec4j.Button;
import org.uispec4j.PasswordField;
import org.uispec4j.TextBox;
import org.uispec4j.Trigger;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.BasicHandler;
import org.uispec4j.interception.MainClassAdapter;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

import eCheque.ElectronicChequeJFrame;

public class RegistrationJFrameTest extends UISpecTestCase {

	private static final String USERNAME = "testUser";
	private static final String PASSWORD = "password";
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		setAdapter(new MainClassAdapter(ElectronicChequeJFrame.class, new String[0]));
	}
	
	/**
	 * Test that the password field can only contain valid values
	 */
	public void testPasswordField()
	{
		Window mainWindow = getMainWindow();
		Button login = mainWindow.getButton("login");
		Button configure = mainWindow.getButton("configure");
		TextBox username = mainWindow.getTextBox("username");
		PasswordField password = mainWindow.getPasswordField("password");

		// Login the user
		username.setText(USERNAME);
		password.setPassword(PASSWORD);
		
		WindowInterceptor.init(login.triggerClick()).process(BasicHandler.init().
       		 assertContainsText("Welcome " + USERNAME).triggerButtonClick("OK")).run();
		
		// Click on the send cheque button to open the configure frame
		WindowInterceptor.init(configure.triggerClick()).process(new WindowHandler() {
			public Trigger process(Window window) {
		           
				Button register = window.getButton("register");
				setValidValues(window);
				
				// Check that a short password is not accepted
				window.getTextBox("password1").setText("pass");
				window.getTextBox("password2").setText("pass");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Your password should be between 8 - 15 characters").triggerButtonClick("OK")).run();

				// Check that a long password is not accepted
				window.getTextBox("password1").setText("passwordpasswordpassword");
				window.getTextBox("password2").setText("passwordpasswordpassword");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Your password should be between 8 - 15 characters").triggerButtonClick("OK")).run();

				// Check that password fields must match
				window.getTextBox("password1").setText("hellohello");
				window.getTextBox("password2").setText("goodbyegoodbye");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Passwords not match ").triggerButtonClick("OK")).run();
				
				window.dispose();
			    return new Trigger() {
						public void run() throws Exception {}
					};
	        }
		});
	}
	
	/**
	 * Test that the account field can only contain valid values
	 */
	public void testAccountNumberField() {
		
		Window mainWindow = getMainWindow();
		Button login = mainWindow.getButton("login");
		Button configure = mainWindow.getButton("configure");
		TextBox username = mainWindow.getTextBox("username");
		PasswordField password = mainWindow.getPasswordField("password");

		// Login the user
		username.setText(USERNAME);
		password.setPassword(PASSWORD);
		
		WindowInterceptor.init(login.triggerClick()).process(BasicHandler.init().
       		 assertContainsText("Welcome " + USERNAME).triggerButtonClick("OK")).run();
		
		// Click on the send cheque button to open the configure frame
		WindowInterceptor.init(configure.triggerClick()).process(new WindowHandler() {
			public Trigger process(Window window) {
		           
				Button register = window.getButton("register");
				setValidValues(window);
				
				// Check that a non-integerer value causes an error
				window.getTextBox("account").setText("23hd84k");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Account number field must be a positive integer").triggerButtonClick("OK")).run();

				// Check a negative number causes an error
				window.getTextBox("account").setText("-12346");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Account number field must be a positive integer").triggerButtonClick("OK")).run();

				window.dispose();
			    return new Trigger() {
						public void run() throws Exception {}
					};
	        }
		});
	}
	
	/**
	 * Test that no fields can be left blank
	 */
	public void testBlankValues()
	{
		Window mainWindow = getMainWindow();
		Button login = mainWindow.getButton("login");
		Button configure = mainWindow.getButton("configure");
		TextBox username = mainWindow.getTextBox("username");
		PasswordField password = mainWindow.getPasswordField("password");

		// Login the user
		username.setText(USERNAME);
		password.setPassword(PASSWORD);
		
		WindowInterceptor.init(login.triggerClick()).process(BasicHandler.init().
       		 assertContainsText("Welcome " + USERNAME).triggerButtonClick("OK")).run();
		
		// Click on the send cheque button to open the configure frame
		WindowInterceptor.init(configure.triggerClick()).process(new WindowHandler() {
			public Trigger process(Window window) {
		           
				Button register = window.getButton("register");
				setValidValues(window);
				
				// Check that bank name cannot be blank
				window.getTextBox("bankname").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Bank Name can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that bank url cannot be blank
				window.getTextBox("bankurl").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Bank URL or IP address can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that client name cannot be blank
				window.getTextBox("clientname").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Client name can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that account number cannot be blank
				window.getTextBox("account").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Account number can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that issuer cannot be blank
				window.getTextBox("issuer").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Certificate issuer can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that certificate url cannot be blank
				window.getTextBox("dcurl").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("Certificate issuer URl or IP can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);
				
				// Check that username cannot be blank
				window.getTextBox("username").setText("");
				WindowInterceptor.init(register.triggerClick()).process(BasicHandler.init().
			       		 assertContainsText("User name can not be empty").triggerButtonClick("OK")).run();
				setValidValues(window);

				// Close the window and return an empty trigger
				window.dispose();
			    return new Trigger() {
						public void run() throws Exception {}
					};
	        }
		});
	}
	
	/**
	 * Sets the values of the configuration form to be valid
	 * 
	 * @param window Must be be configuration window
	 */
	private void setValidValues(Window window)
	{
		window.getTextBox("account").setText("736384");
		window.getTextBox("bankname").setText("eBank");
		window.getTextBox("bankurl").setText("localhost");
		window.getTextBox("clientname").setText("Steve");
		window.getTextBox("issuer").setText("Issuer");
		window.getTextBox("dcurl").setText("localhost");
		window.getTextBox("username").setText("testUser");
		window.getTextBox("password1").setText("password");
		window.getTextBox("password2").setText("password");
	}

}
