/*
 * BankServer.java
 *
 * Created on June 10, 2007, 1:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eCheque;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Saad
 */
public class BankServer implements Runnable {
	private ServerSocket serverSocket;

	/** Creates a new instance of BankSever */
	public BankServer() throws IOException {
		serverSocket = new ServerSocket(EChequeUtil.SERVER_PORT);
	}

	public void run() {
		try {
			while (true) {

				Socket incoming = serverSocket.accept();
				Runnable chequeServer = new EchequeServer(incoming);
				Thread bankThreading = new Thread(chequeServer);
				bankThreading.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
