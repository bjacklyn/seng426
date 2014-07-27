package eCheque;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import org.junit.Test;

public class DigitalCertificateIOTest {
	
	private String digitalCertificateFile = "test\\eCheque\\resources\\DigitalCertificateIOTestFile";
	
	private boolean checkDigitalCertificateEquals(DigitalCertificate first, DigitalCertificate second) {
		
		// check if same reference
		if (first == second)
			return true;
		
		// must check if all fields are equal
		else {
			if (!first.getHolderName().equals(second.getHolderName()))
				return false;
			if (!first.getIssuer().equals(second.getIssuer()))
				return false;
			if (!Arrays.equals(first.getIssuerSignature(), second.getIssuerSignature()))
				return false;
			if (!first.getpublicKey().equals(second.getpublicKey()))
				return false;
			if (!first.getSerialNumber().equals(second.getSerialNumber()))
				return false;
			if (!first.getSubject().equals(second.getSubject()))
				return false;
			if (!first.getValidFrom().equals(second.getValidFrom()))
				return false;
			if (!first.getValidTo().equals(second.getValidTo()))
				return false;
		}
		
		// they are equal
		return true;
	}

	@Test
	public void testSaveAndReadCorrectly() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException {
		
		// create a digital certificate
		DigitalCertificate actual = new DigitalCertificate();
		actual.setHolderName("name");
		actual.setIssuer("issuer");
		actual.setIssuerSignature("signature".getBytes());
		actual.setPublicKey(KeyPairGenerator.getInstance("DSA", "SUN").generateKeyPair().getPublic());
		actual.setSerialNumber("123");
		actual.setSubject("subject");
		actual.setValidFrom("from");
		actual.setValidTo("to");
		
		// save the digital certificate to disk
		DigitalCertificateIO.saveDC(actual, digitalCertificateFile);
		
		// read the digital certificate from disk
		DigitalCertificate expected = DigitalCertificateIO.readDigitalCertificate(digitalCertificateFile);
		
		// check whether they are equal after serializing/deserializing to disk
		assertTrue(checkDigitalCertificateEquals(actual, expected));
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testInvalidFilePath() throws IOException {
		String invalidFilePath = "sguds;;ghud4532:\\invaliddrivepath";
		
		DigitalCertificate cert = new DigitalCertificate();
		cert.setHolderName("name");
		
		// save the digital certificate to disk
		DigitalCertificateIO.saveDC(cert, digitalCertificateFile);
	}
	
	@Test(expected=UTFDataFormatException.class)
	public void testinvalidEChequeOnDisk() throws ClassNotFoundException, IOException {
		String invalidEChequeOnDisk = "test\\eCheque\\resources\\InvalidDigitalCertificate";
		
		DigitalCertificateIO dcIO = new DigitalCertificateIO();
		
		// save the digital certificate to disk
		DigitalCertificate cert = dcIO.readDigitalCertificate(invalidEChequeOnDisk);
	}
}
