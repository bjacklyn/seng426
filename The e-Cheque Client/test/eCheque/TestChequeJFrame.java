package eCheque;

import static org.junit.Assert.*;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import org.junit.BeforeClass;
import org.junit.Test;
import eCheque.TestUtils;
import eCheque.ChequeJFrame;
import eCheque.EChequeRegisteration;

public class TestChequeJFrame {
	
	static EChequeRegisteration r;
	static String walletLocation = System.getProperty("user.dir") + "\\src\\eChequeTest\\resources";
	static String myCheques = walletLocation + "\\My Cheques";
	ChequeJFrame cjf;
	
	// Pads a password based on the scheme used in the eCheque system
	private static String makePassword(char password[]) {
		
        String passTemp="";
        if(password.length >=8 && password.length <16){
        
            for(int i=0; i<password.length; i++){
                passTemp+=password[i];
            }
        
            if(password.length < 16){
            int pad = 16 - password.length;
                for(int i=0; i<pad; i++){
                   passTemp+=password[i];
                }  
            } 
        }
        return passTemp;
	}
	
	@BeforeClass 
	public static void setUpUser() {
		// This should match the user wallet that has been created in eChequeTest resources
		r = new EChequeRegisteration();
		r.setAccountNumber("1");
		r.setBankAddress("localhost");
		r.setBankName("bank");
		r.setClientName("test");
		r.setEWalletLoaction(walletLocation);
		r.setPasword(makePassword("test1234".toCharArray()).hashCode());
		r.setUsername("test".hashCode());
	}
	
	public void enterChequeValues(String payto, String amount, String year, String month, String day) {
		
		JTextField inputPayTo = (JTextField)TestUtils.getChildNamed(cjf, "jTPayTo");
		JTextField inputAmount = (JTextField)TestUtils.getChildNamed(cjf, "jTAmount");
		JTextField inputYear = (JTextField)TestUtils.getChildNamed(cjf, "jTYear");
		JTextField inputMonth = (JTextField)TestUtils.getChildNamed(cjf, "jTMonth");
		JTextField inputDay = (JTextField)TestUtils.getChildNamed(cjf, "jTDay");
		
		inputPayTo.setText(payto);
		inputAmount.setText(amount);
		inputYear.setText(year);
		inputMonth.setText(month);
		inputDay.setText(day);
	}
	
	public void clickButtonByName(String name) {
		JMenuItem item = (JMenuItem)TestUtils.getChildNamed(cjf, name);		
		item.doClick();
	}
	
	public void initializeChequeJFrame() {
		cjf = new ChequeJFrame(r);
		cjf.setVisible(true);
	}
	
	@Test
	public void testValidChequeCreation(){
		
		int prev = new File(myCheques).listFiles().length;
		
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Password must be entered manually
		// Some popup dialogs need to be clicked manually
		// Source code must be modified later for this 
		
		int aft =  new File(myCheques).listFiles().length;

		assertTrue(aft > prev);
		
	}
	
	@Test
	public void testPayToOrderOfBlank() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testPayToOrderOfLong() {
		
		String a = "";
		for (int i = 0; i < 100; i++) {
			a += "x";
		}
		
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues(a, "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testPayToOrderOfSpecialCharacters() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("@$%&@$&%@$%@*(&#%{}", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testPayToOrderOfValid() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testAmountOfMoneyNotANumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "asdf", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testAmountOfMoneyNegative() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "-10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testAmountOfMoneyGreaterThanMaxInt() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "100000000000000000000000000000000000000000000000000000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testAmountOfMoneyZero() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "0", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testAmountOfMoneyValid() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testYearNotANumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "asdf", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testYearNegativeNumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "-2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testYearGreaterThanMaxInt() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "100000000000000000000000000000000000000000000000000000", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testYearValid() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testMonthNotANumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "asdf", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testMonthNegativeNumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "-1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testMonthZero() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "0", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testMonthGreaterThanTwelve() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "15", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testMonthValid() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testDayNotANumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "asdf");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testDayNegativeNumber() {
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "-1");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
	}

	@Test
	public void testDayUpperBoundaries() {
		// feb leap year
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2012", "2", "29");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now) 
		
		// feb not leap year
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "2", "30");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now)
		
		// 30 day month
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "4", "31");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now)
		
		// 31 day month
		initializeChequeJFrame();
		clickButtonByName("jMNewCheque");
		enterChequeValues("Bob", "10000", "2014", "1", "32");
		clickButtonByName("jMSaveCheque");
		
		// Check that error popup appears (not possible as of right now)
	}

}
