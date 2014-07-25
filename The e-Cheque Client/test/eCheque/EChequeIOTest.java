package eCheque;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import eCheque.ECheque;
import eCheque.EChequeIO;
import junit.framework.TestCase;

public class EChequeIOTest extends TestCase {
	
	ECheque testchek;
	EChequeIO undertest;
	
	String testout = "test"+File.separator+"eCheque"+File.separator+"resources"+File.separator+"testout";
	String testin = "test"+File.separator+"eCheque"+File.separator+"resources"+File.separator+"testin";
	String badtestin = "test"+File.separator+"eCheque"+File.separator+"resources"+File.separator+"badtestin";
	String roundtest = "test"+File.separator+"eCheque"+File.separator+"resources"+File.separator+"roundtest";

	protected void setUp() throws Exception {
		super.setUp();
		
		undertest = new EChequeIO();
		testchek = new ECheque();
		
		String teststring = "2143";
		boolean testbool = true;
		
		testchek.setaccountholder(teststring);
		testchek.setaccountNumber(teststring);
		testchek.setamountofMony(teststring);
		testchek.setbankname(teststring);
		testchek.setbanksignature(teststring.getBytes());
		testchek.setchequeNumber(teststring);
		testchek.setcurrencytype(teststring);
		testchek.setdrawersiganure(teststring.getBytes());
		testchek.setearnday(teststring);
		testchek.setguaranteed(testbool);
		testchek.setpayToOrderOf(teststring);
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
		catch (ClassNotFoundException e)
		{
			//nothing!
		}
		catch (IOException e)
		{
			return;
		}
		fail("Didn't catch invalid input."); //Should throw an exception on invalid ECheque file
	}
	
	@Test
	public void testRoundTripReadWrite()
	{
		ECheque b = null;
		
		try
		{
			undertest.savecheque(testchek, roundtest);
		}
		catch (Exception e)
		{
			fail ("Exception! "  + e.toString());
		}
		try{
			b = undertest.readcheque(roundtest);
		}
		catch(Exception e)
		{
			fail(e.toString());
		}
		if (!areEqual(testchek,b))
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
