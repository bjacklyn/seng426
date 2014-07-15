package eCheque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class AESCryptTest {
	
	private String sameBlockSizeInputLocation = "test\\eCheque\\resources\\testCryptWithSameBlockSize.cry";
	private String differentBlockSizeInputLocation = "test\\eCheque\\resources\\testCryptWithDifferentBlockSize.cry";

	private String sameBlockSizeOutputLocation = "test\\eCheque\\resources\\testCryptWithSameBlockSize.sec";
	private String differentBlockSizeOutputLocation = "test\\eCheque\\resources\\testCryptWithDifferentBlockSize.sec";
	
	
	/**
	 * Generate two keys using GenerateRandomAESKey to make sure the keys are truly random
	 */
	@Test
	public void testGenerateRandomAESKey() throws Exception {
		AESCrypt aescTest = new AESCrypt();
       
		SecretKey key1 = aescTest.GenerateRandomAESKey();
		SecretKey key2 = aescTest.GenerateRandomAESKey();
		
        if (Arrays.equals(key1.getEncoded(), key2.getEncoded())) {
        	fail();
        }
	}

	/**
	 * Test initializeCipher with mode = 0
	 */
	@Test
	public void testInitializeCipherWithMode0() throws Exception {
		AESCrypt aescTest = new AESCrypt();
		
		SecretKey key = aescTest.GenerateRandomAESKey();
		
		Cipher actualCipherObj = aescTest.initializeCipher(key, 0);
		Cipher expectedCipherObj = Cipher.getInstance("AES");
		expectedCipherObj.init(Cipher.ENCRYPT_MODE, key);
		
		assertEquals(expectedCipherObj.getAlgorithm(), actualCipherObj.getAlgorithm());	
	}
	
	/**
	 * Test initializeCipher with mode = 1
	 */
	@Test
	public void testInitializeCipherWithMode1() throws Exception {
		AESCrypt aescTest = new AESCrypt();
		
		SecretKey key = aescTest.GenerateRandomAESKey();
		
		Cipher actualCipherObj = aescTest.initializeCipher(key, 1);
		Cipher expectedCipherObj = Cipher.getInstance("AES");
		expectedCipherObj.init(Cipher.DECRYPT_MODE,key);
		
		assertEquals(expectedCipherObj.getAlgorithm(), actualCipherObj.getAlgorithm());
	}
	
	
	
	/**
	 * Test initializeCipher with mode = 2
	 * @throws Exception
	 */
	@Test
	public void testInitializeCipherWithMode2() throws Exception {
		AESCrypt aescTest = new AESCrypt();
		
		SecretKey key = aescTest.GenerateRandomAESKey();
		
		Cipher actualCipherObj = aescTest.initializeCipher(key, 2);
		Cipher expectedCipherObj = Cipher.getInstance("RSA");
		expectedCipherObj.init(Cipher.WRAP_MODE,key);
		
		assertEquals(expectedCipherObj.getAlgorithm(), actualCipherObj.getAlgorithm());
	}
	
	/**
	 * Test initializeCipher with mode = 3
	 * @throws Exception
	 */
	@Test
	public void testInitializeCipherWithMode3() throws Exception {
		AESCrypt aescTest = new AESCrypt();
		
		SecretKey key = aescTest.GenerateRandomAESKey();
		
		Cipher actualCipherObj = aescTest.initializeCipher(key, 3);
		Cipher expectedCipherObj = Cipher.getInstance("RSA");
		expectedCipherObj.init(Cipher.UNWRAP_MODE, key);
		
		assertEquals(expectedCipherObj.getAlgorithm(), actualCipherObj.getAlgorithm());
	}
	
	/**
	 * Test initializeCipher with mode = -1
	 * @throws Exception
	 */
	@Test
	public void testInitializeCipherWithModeNegative1() throws Exception {
		AESCrypt aescTest = new AESCrypt();
		
		SecretKey key = aescTest.GenerateRandomAESKey();
		
		Cipher actualCipherObj = aescTest.initializeCipher(key, -1);
		Cipher expectedCipherObj = Cipher.getInstance("RSA");
		expectedCipherObj.init(Cipher.UNWRAP_MODE, key);
		
		assertEquals(expectedCipherObj.getAlgorithm(), actualCipherObj.getAlgorithm());
	}

	/**
	 * Test encryption with same block size and different block sizes
	 */
	@Test
	public void testCrypt() throws Exception {
		// Testing with same block size
		AESCrypt aescTest = new AESCrypt();
		SecretKey key = aescTest.GenerateRandomAESKey();
		Cipher actualCipherObj = aescTest.initializeCipher(key, 0);
		
		InputStream in = new FileInputStream(sameBlockSizeInputLocation);
		OutputStream out = new FileOutputStream(sameBlockSizeOutputLocation);
		try {
			aescTest.crypt(in, out, actualCipherObj);
		} finally {
			in.close();
			out.close();
		}
        
        File f = new File(sameBlockSizeOutputLocation);
        if (f.exists()) {
        	assertTrue(true);
        } else {
        	assertFalse(false);
        }
        
        // Test with different block size
        in = new FileInputStream(differentBlockSizeInputLocation);
        out = new FileOutputStream(differentBlockSizeOutputLocation);
        
        try {
        	aescTest.crypt(in, out, actualCipherObj);
        } finally {
	        in.close();
	        out.close();
        }
        
        File f2 = new File(differentBlockSizeOutputLocation);
        if (f2.exists()) {
        	assertTrue(true);
        } else {
        	assertFalse(false);
        }
	}

	/**
	 * Test initialzeAESKeyByPassword by using the same password
	 */
	@Test
	public void testInilizeAESKeyByPasswordWithSamePassword() {
		String password = "password";
		byte[] passwordBytes = password.getBytes();
		
        AESCrypt aescTest = new AESCrypt();
        SecretKeySpec actualAesKey = aescTest.inilizeAESKeyByPassword(password);
		
        SecretKeySpec expectedAesKey =new SecretKeySpec(passwordBytes, "AES");
        
        assertEquals(expectedAesKey, actualAesKey);
	}
	
	/**
	 * Test initialzeAESKeyByPassword by using the different password
	 */
	@Test
	public void testInilizeAESKeyByPasswordWithDifferentPassword() {
		String password = "password";
		String password2 = "different";
		
        AESCrypt aescTest = new AESCrypt();
        SecretKeySpec actualAesKey = aescTest.inilizeAESKeyByPassword(password2);
		
		byte[] KeyData = password.getBytes();
        SecretKeySpec expectedAesKey =new SecretKeySpec(KeyData,"AES");
        
        assertFalse(Arrays.equals(expectedAesKey.getEncoded(),actualAesKey.getEncoded()));
	}
}
