package eCheque;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AESCryptTest.class, DigitalCertificateIOTest.class, DigitalCertificateTest.class, DigitalsignetureTest.class,
	EChequeIOTest.class, EChequeRegistrationTest.class, EChequeTest.class, GenerateRSAKeysTest.class,
	SendChequeJFrameTest.class, RegistrationJFrameTest.class, TestChequeJFrame.class })
public class AllTests {
	
}
