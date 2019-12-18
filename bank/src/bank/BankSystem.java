package bank;

import java.io.*;
import java.util.*;

public class BankSystem implements Serializable{    //to serialized object
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Account> account = new ArrayList<Account> ();//store input information
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		BankSystem bank = new BankSystem();
		bank.readWrite();		
		@SuppressWarnings("unused")
		HomeGUI homepage = new HomeGUI(bank.account);
		
	}

	@SuppressWarnings("unchecked")
	private void readWrite() throws IOException, ClassNotFoundException
	{
			FileInputStream fiel_in = new FileInputStream("User.txt");  // create a write channel
			ObjectInputStream object_In = new ObjectInputStream(fiel_in);       // create an object to read in
			Object readArray = object_In.readObject();                     // reads an Object and returns an Object of type Object
			
			account = (ArrayList<Account>) readArray;
			object_In.close();                                             // close the read resource
	}
	
}


