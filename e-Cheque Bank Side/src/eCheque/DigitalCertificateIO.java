package eCheque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DigitalCertificateIO {

	public static void saveDC(DigitalCertificate a, String filePath)
			throws IOException {
		// To create a new file to store Digtial Certificate Object.
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				new File(filePath)));

		out.writeObject(a);

		out.close();
	}

	public static DigitalCertificate readDigitalCertificate(String filePath)
			throws IOException, ClassNotFoundException {
		ObjectInputStream In = new ObjectInputStream(new FileInputStream(
				new File(filePath)));
		DigitalCertificate DC;
		DC = (DigitalCertificate) In.readObject();

		In.close();
		return DC;

	}
}