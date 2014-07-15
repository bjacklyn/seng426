package eCheque;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import eCheque.ECheque;
import eCheque.EChequeIO;
import junit.framework.TestCase;

public class EChequeIOTest extends TestCase {
	
	ECheque testchek;
	EChequeIO undertest;
	
	String testout = "test\\eCheque\\resources\\testout";
	String testin = "test\\eCheque\\resources\\testin";
	String badtestin = "test\\eCheque\\resources\\badtestin";
	String roundtest = "test\\eCheque\\resources\\roundtest";

	protected void setUp() throws Exception {
		super.setUp();
		
		undertest = new EChequeIO();
		testchek = new ECheque();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCanSavecheque() {
		try
		{
			undertest.savecheque(testchek, testout);
		}
		catch(IOException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testCanReadECheque() {
		ECheque temp;
		try
		{
			temp = undertest.readcheque(testin);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test(expected=ClassNotFoundException.class)
	public void testReadInvalidCheque()
	{
		ECheque temp;
		try
		{
			temp = undertest.readcheque(badtestin);
		}
		catch (Exception e)
		{
		}
		fail("Didn't catch invalid input."); //Should throw an exception on invalid ECheque file
	}
	
	@Test
	public void testRoundTripReadWrite()
	{
		ECheque a = new ECheque();
		ECheque b = null;
		String teststring = "smoop";
		boolean testbool = true;
		
		a.setaccountholder(teststring);
		a.setaccountNumber(teststring);
		a.setamountofMony(teststring);
		a.setbankname(teststring);
		a.setbanksignature(teststring.getBytes());
		a.setchequeNumber(teststring);
		a.setcurrencytype(teststring);
		a.setdrawersiganure(teststring.getBytes());
		a.setearnday(teststring);
		a.setguaranteed(testbool);
		a.setpayToOrderOf(teststring);
		
		try
		{
			undertest.savecheque(a, roundtest);
			b = undertest.readcheque(roundtest);
		}
		catch (Exception e)
		{
			fail ("Exception!" + e.getMessage());
		}
		
		if (!areEqual(a,b))
		{
			fail("Round trip failed!");
		}
	}
	
	@Test
	public void testEChequeIOConstructor() {
		EChequeIO io = new EChequeIO();
	}

	
	private boolean areEqual(ECheque a,ECheque b)
	{
		if (a == b)
			return true;
		
		if (!a.getaccountholder().equals(b.getaccountholder()))
			return false;
		if (!a.getaccountNumber().equals(b.getaccountNumber()))
			return false;
		if (!a.getbankname().equals(b.getbankname()))
			return false;
		if (!Arrays.equals(a.getbanksignature(), b.getbanksignature()))
			return false;
		if (!a.getchequeNumber().equals(b.getchequeNumber()))
			return false;
		if (!a.getcurrencytype().equals(b.getcurrencytype()))
			return false;
		if (!Arrays.equals(a.getdrawersiganure(), b.getdrawersiganure()))
			return false;
		if (!a.getearnday().equals(b.getearnday()))
			return false;
		if (!a.getguaranteed()==a.getguaranteed())
			return false;
		if (!a.getMoney().equals(b.getMoney()))
			return false;
		if (!a.getpayToOrderOf().equals(b.getpayToOrderOf()))
			return false;
		
		return true;
	}
}
