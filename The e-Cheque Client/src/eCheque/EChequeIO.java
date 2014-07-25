/*
 * savecheque.java
 *
 * Created on March 4, 2007, 9:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eCheque;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Basel
 */
public class EChequeIO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	class HashCheque implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ECheque cheque;
		int hash;
		
		public HashCheque(ECheque cheque, int hash)
		{
			this.cheque = cheque;
			this.hash = hash;
		}
	}

   public void savecheque(ECheque obj,String filename)//(ECheque x,String filebath)
   throws IOException {
       
	   HashCheque map = new HashCheque(obj,obj.lazyHash());
       ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(filename)));//new File(filename))
      
       out.writeObject(map);//(xj);
       
       out.close();
        
   }
   public ECheque readcheque(String filename) throws IOException, ClassNotFoundException {
       
       ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File(filename)));//new File(filename))
       
       HashCheque input = (HashCheque)in.readObject();
       
       in.close();
       if(input.cheque.lazyHash()!=input.hash)
       {
    	   throw new IOException("Invalid ECheque File. Stored Echeque does not correspond with its stored hash.");
       }
       return input.cheque;
       
    }
    
    
    /** Creates a new instance of savecheque */
   public EChequeIO() {
   
   }
    
 }
