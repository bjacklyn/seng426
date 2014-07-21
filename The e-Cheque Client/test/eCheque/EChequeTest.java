package eCheque;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;

// This class' methods are all getters and setters.

// They only access their ECheque's fields, and so 
// are guaranteed to succeed whenever they are called.

public class EChequeTest extends TestCase {
	ECheque undertest;
	
	String teststring = "test";
	boolean testbool = false;

	protected void setUp() throws Exception {
		super.setUp();
		undertest = new ECheque();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	
	public void testaccountholder()
	{
		undertest.setaccountholder(teststring);
		
		if(!undertest.getaccountholder().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
		
	}

	public void testaccountnumber()
	{
		undertest.setaccountNumber(teststring);
		
		if(!undertest.getaccountNumber().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}

	public void testamountofmoney()
	{
		undertest.setamountofMony(teststring);
		
		if(!undertest.getMoney().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testbankname()
	{
		undertest.setbankname(teststring);
		
		if(!undertest.getbankname().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testbanksig()
	{
		undertest.setbanksignature(teststring.getBytes());
		
		if(!Arrays.equals(undertest.getbanksignature(), teststring.getBytes()))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testchequenumber()
	{
		undertest.setchequeNumber(teststring);
		
		if(!undertest.getchequeNumber().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testcurrencytype()
	{
		undertest.setcurrencytype(teststring);
		
		if(!undertest.getcurrencytype().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testdrawersig()
	{
		undertest.setdrawersiganure(teststring.getBytes());
		
		if(!Arrays.equals(undertest.getdrawersiganure(), teststring.getBytes()))
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testearnday()
	{
		undertest.setearnday(teststring);
		
		if(!undertest.getearnday().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}

	public void testguaranteed()
	{
		undertest.setguaranteed(testbool);
		
		if(undertest.getguaranteed()!=testbool)
		{
			fail("The setter or getter didn't work.");
		}
	}
	
	public void testpayto()
	{
		undertest.setpayToOrderOf(teststring);
		
		if(!undertest.getpayToOrderOf().equals(teststring))
		{
			fail("The setter or getter didn't work.");
		}
	}
}
