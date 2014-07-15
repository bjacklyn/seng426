package eCheque;

import static org.junit.Assert.assertEquals;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import eCheque.DigitalCertificate;
import eCheque.RSAGenerator;

public class DigitalCertificateTest {
	
	private static String TEST_INPUT_STRING = "test";
	
	@Test
	public void testHolderName() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setHolderName(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getHolderName());
	}
	
	@Test
	public void testSubject() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setSubject(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getSubject());
	}
	
	@Test
	public void testIssuer() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setIssuer(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getIssuer());
	}
	
	@Test
	public void testSerialNumber() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setSerialNumber(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getSerialNumber());
	}
	
	@Test
	public void testValidFrom() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setValidFrom(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getValidFrom());
	}
	
	@Test
	public void testValidTo() {
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setValidTo(TEST_INPUT_STRING);
		assertEquals(TEST_INPUT_STRING,digitalCertificate.getValidTo());
	}
	
	@Test
	public void testPublicKey() throws NoSuchAlgorithmException {
		RSAGenerator keyGen = new RSAGenerator();
		KeyPair RSAKeys = keyGen.GenerateRSAKeys();
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setPublicKey(RSAKeys.getPublic());
		assertEquals(RSAKeys.getPublic(),digitalCertificate.getpublicKey());
	}
	
	@Test
	public void testIssuerSignature() {
		byte [] byteArray = {1};
		DigitalCertificate digitalCertificate = new DigitalCertificate();
		digitalCertificate.setIssuerSignature(byteArray);
		assertEquals(byteArray,digitalCertificate.getIssuerSignature());
	}

}
