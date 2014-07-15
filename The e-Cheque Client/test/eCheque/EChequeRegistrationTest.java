package eCheque;

import org.junit.Test;

import eCheque.EChequeRegisteration;
import junit.framework.TestCase;

//This class' methods are all getters and setters.

//They only access their ECheque's fields, and so 
//are guaranteed to succeed whenever they are called.

public class EChequeRegistrationTest extends TestCase {
	EChequeRegisteration undertest;
	
	String teststring = "Test!";
	int testint = 34;

	protected void setUp() throws Exception {
		super.setUp();
		undertest = new EChequeRegisteration();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testEChequeRegisteration() {
		EChequeRegisteration registration = new EChequeRegisteration();
	}

	@Test
	public void testGettersSetters()
	{
		undertest.setAccountNumber(teststring+ "1");
		undertest.setBankAddress(teststring+ "2");
		undertest.setBankName(teststring+ "3");
		undertest.setClientName(teststring+ "4");
		undertest.setEWalletLoaction(teststring+ "5");
		undertest.setPasword(testint+ 1);
		undertest.setUsername(testint+2);
		
		if(!(undertest.getAccountNumber().equals(teststring+ "1") &
		undertest.getBankAddress().equals(teststring+ "2")&
		undertest.getBankName().equals(teststring+ "3")&
		undertest.getClientName().equals(teststring+ "4")&
		undertest.getEWalletLoaction().equals(teststring + "5")&
		(undertest.getPasword()==testint +1)&
		(undertest.getUsername()== testint +2)))
		{
			fail ("The getters or setters did not work.");
		}
	}
}
