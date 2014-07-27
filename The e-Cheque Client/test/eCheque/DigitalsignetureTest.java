package eCheque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.KeyPair;
import java.security.Signature;
import java.util.Arrays;

import org.junit.Test;

public class DigitalsignetureTest {

	@Test
	public void testSigneture() throws Exception {
		//set up RSAKeys
		RSAGenerator keyGen = new RSAGenerator();
		KeyPair RSAKeys = keyGen.GenerateRSAKeys();
		String message = "Testing";
		
		Signature signmessage = Signature.getInstance("SHA1withRSA");
        signmessage.initSign(RSAKeys.getPrivate());
        signmessage.update(message.getBytes());
        byte[] expectedSignature = signmessage.sign();
        
        DigitalSignature ds = new DigitalSignature();
        byte[] actualSignature = ds.sign(message, RSAKeys.getPrivate());
        
        assertTrue(Arrays.equals(expectedSignature, actualSignature));
	}
	
	@Test
	public void testVerifySignatureWithRightSingature() throws Exception {
		//set up RSAKeys
		RSAGenerator keyGen = new RSAGenerator();
		KeyPair RSAKeys = keyGen.GenerateRSAKeys();
		String message = "Testing";
		
		Signature signmessage = Signature.getInstance("SHA1withRSA");
        signmessage.initSign(RSAKeys.getPrivate());
        signmessage.update(message.getBytes());
        byte[] signature = signmessage.sign();
        
        DigitalSignature ds = new DigitalSignature();
        
        assertTrue(ds.verifySignature(signature, message, RSAKeys.getPublic()));
	}
	
	@Test
	public void testVerifySignatureWithWrongSingature() throws Exception {
		//set up RSAKeys
		RSAGenerator keyGen = new RSAGenerator();
		KeyPair RSAKeys = keyGen.GenerateRSAKeys();
		String message = "Testing";
		String wrongMessage = "Worng";
		
		Signature signmessage = Signature.getInstance("SHA1withRSA");
        signmessage.initSign(RSAKeys.getPrivate());
        signmessage.update(wrongMessage.getBytes());
        byte[] signature = signmessage.sign();
        
        DigitalSignature ds = new DigitalSignature();
        
        assertFalse(ds.verifySignature(signature, message, RSAKeys.getPublic()));
	}
}
