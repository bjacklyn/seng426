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
	
	public void testAccountNumber()
	{
		undertest.setAccountNumber(teststring+ "1");
		
		if(!undertest.getAccountNumber().equals(teststring+ "1"))
		{
			fail("The getter or setter didn't work");
		}
		
	}
	
	public void testBankAddress()
	{
		undertest.setBankAddress(teststring+ "2");
		
		if(!undertest.getBankAddress().equals(teststring+ "2"))
		{
			fail("The getter or setter didn't work");
		}
	}
	
	public void testBankName()
	{
		undertest.setBankName(teststring+ "3");
		
		if(!undertest.getBankName().equals(teststring+ "3"))
		{
			fail("The getter or setter didn't work");
		}
	}
	
	public void testClientName()
	{
		undertest.setClientName(teststring+ "4");
		
		if(!undertest.getClientName().equals(teststring+ "4"))
		{
			fail("The getter or setter didn't work");
		}
	}
	
	public void testEWalletLoaction()
	{
		undertest.setEWalletLoaction(teststring+ "5");
		
		if(!undertest.getEWalletLoaction().equals(teststring + "5"))
		{
			fail("The getter or setter didn't work");
		}
	}
	
	public void testPassword()
	{
		undertest.setPasword(testint+ 1);
		
		if(!(undertest.getPasword()==testint +1))
		{
			fail("The getter or setter didn't work");
		}
	}
	
	public void testUsername()
	{
		undertest.setUsername(testint+2);
		
		if(!(undertest.getUsername()== testint +2))
		{
			fail ("The getter or setter did not work.");
		}
	}
}
