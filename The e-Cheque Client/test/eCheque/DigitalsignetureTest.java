package eCheque;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.Signature;
import java.util.Arrays;

import org.junit.Test;

import eCheque.Digitalsigneture;
import eCheque.RSAGenerator;

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
        
        Digitalsigneture ds = new Digitalsigneture();
        byte[] actualSignature = ds.signeture(message, RSAKeys.getPrivate());
        
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
        
        Digitalsigneture ds = new Digitalsigneture();
        
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
        
        Digitalsigneture ds = new Digitalsigneture();
        
        assertFalse(ds.verifySignature(signature, message, RSAKeys.getPublic()));
	}
}
