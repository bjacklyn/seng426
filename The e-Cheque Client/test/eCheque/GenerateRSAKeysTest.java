package eCheque;

import java.security.*;

import javax.crypto.Cipher;

import eCheque.RSAGenerator;
import junit.framework.TestCase;

public class GenerateRSAKeysTest extends TestCase {
	
	private String testcrypt = "TestyTest";
	RSAGenerator generator;
	KeyPair testpair;
	
	protected void setUp() throws Exception {
		super.setUp();
		generator = new RSAGenerator();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRSAGenerator() {
	}

	public void testGenerateRSAKeys() {
		try 
		{
			testpair = generator.GenerateRSAKeys();
		}
		catch (NoSuchAlgorithmException e)
		{
			fail("No Such algorithm- "+ e.getMessage());
		}
		
		byte[] cipherText = null;
		byte[] plainText = null;
	    try 
	    {
	    	
	    	//Encrypt test phrase...
	    	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    	cipher.init(Cipher.ENCRYPT_MODE, testpair.getPublic());
	    	cipherText = cipher.doFinal(testcrypt.getBytes());
	     
	    	//Decrypt test phrase...
	    	cipher.init(Cipher.DECRYPT_MODE, testpair.getPrivate());
	    	plainText = cipher.doFinal(cipherText);

	    }
	     catch (Exception e) {
	      fail("Exception!\n");
	    }
	    
	    //Compare round-tripped test phrase and original...
	    String pText = new String(plainText);
	    if (testcrypt.equals(pText))
	    {
	    	return;
	    }
	    else
	    {
	    	fail("Cipher is incorrect.: " + testcrypt + " vs " + pText + "\n");
	    }
	}

}
