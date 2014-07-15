package eCheque;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;

// This class' methods are all getters and setters.

// They only access their ECheque's fields, and so 
// are guaranteed to succeed whenever they are called.

public class EChequeTest extends TestCase {
	ECheque undertest;

	protected void setUp() throws Exception {
		super.setUp();
		undertest = new ECheque();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGettersSetters()
	{
		String teststring = "test";
		boolean testbool = false;
		
		
		undertest.setaccountholder(teststring);
		undertest.setaccountNumber(teststring);
		undertest.setamountofMony(teststring);
		undertest.setbankname(teststring);
		undertest.setbanksignature(teststring.getBytes());
		undertest.setchequeNumber(teststring);
		undertest.setcurrencytype(teststring);
		undertest.setdrawersiganure(teststring.getBytes());
		undertest.setearnday(teststring);
		undertest.setguaranteed(testbool);
		undertest.setpayToOrderOf(teststring);
		
		if (!(undertest.getaccountholder().equals(teststring) &
		undertest.getaccountNumber().equals(teststring) &
		undertest.getbankname().equals(teststring) &
		Arrays.equals(undertest.getbanksignature(), teststring.getBytes()) &
		undertest.getchequeNumber().equals(teststring) &
		undertest.getcurrencytype().equals(teststring) &
		Arrays.equals(undertest.getdrawersiganure(), teststring.getBytes()) &
		undertest.getearnday().equals(teststring) &
		(undertest.getguaranteed()==testbool) &
		undertest.getMoney().equals(teststring) &
		undertest.getpayToOrderOf().equals(teststring)))
		{
			fail("The setters or getters didn't work.");
		}
	}
}
