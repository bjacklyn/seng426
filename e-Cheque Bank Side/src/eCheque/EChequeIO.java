/*
 * EChequeIO.java
 *
 * Created on March 4, 2007, 9:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eCheque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Basel
 */
public class EChequeIO {

	public void savecheque(ECheque obj, String filename)
			throws IOException {

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				new File(filename)));

		out.writeObject(obj);

		out.close();

	}

	public ECheque readcheque(String filename) throws IOException,
			ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				new File(filename)));
		ECheque cheq;

		cheq = (ECheque) in.readObject();
		in.close();

		return cheq;
	}

	/** Creates a new instance of savecheque */
	public EChequeIO() {

	}

}
