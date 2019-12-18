package bank;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ExitGUI implements Serializable{
	private static final long serialVersionUID = 1L;
	public void exitSystem(ArrayList<Account> acc) throws IOException {
		Object[] options = {"Yes", "No"};
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit System?", "Exit", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if(choice == 0){
				outputFile(acc);//store users' ID and password to be visible.
				FileOutputStream fiel_in = new FileOutputStream("User.txt");//write in User.txt
				ObjectOutputStream object_In = new ObjectOutputStream(fiel_in);
				object_In.writeObject(acc);
				object_In.close();
			System.exit(0);
		}
	}
	private void outputFile(ArrayList<Account> acc) throws FileNotFoundException {
		String string = new String("ID\t Password\tName\tAddress\tType\tState");
		File fileVisible = new File("UserMessage.txt");
            @SuppressWarnings("resource")
			PrintStream file = new PrintStream(new FileOutputStream(fileVisible));  
            file.println(string);// 
            for(int i=0; i<acc.size(); i++) {
            	 file.println(acc.get(i).getID()+"  "+acc.get(i).getPIN()+"  "+acc.get(i).getName()+"  "+acc.get(i).getAddress()+"  "+acc.get(i).getType()+"  "+acc.get(i).getState()); //store users' information to be visible.You can find here!
            }   
	}	
}
