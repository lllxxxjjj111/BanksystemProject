package bank;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class OpenAccountGUI {
	private JFrame openPage = new JFrame("Open Account");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JButton submit = new JButton("Submit");
	private JButton exit = new JButton("Exit");
	
	private JLabel welcome = new JLabel(" Fill in all your informations please and then you can open a new account!");
	private JLabel welcomes = new JLabel(" Note:Only under 16, you can and only can open a Junior Account");
	private JLabel name = new JLabel("Name:");
	private JLabel address = new JLabel("Address:");
	private JLabel year = new JLabel("Year:");
	private JLabel month = new JLabel("Month:");
	private JLabel day = new JLabel("Day:");
	private String[] choice = {"Current Account","Junior Account","Saver Account"  };
	private JComboBox<String> accountSelect = new JComboBox<String>(choice);
	
	private JTextField nameText = new JTextField(10);
	private JTextField addressText = new JTextField(10);
	private JTextField yearText = new JTextField(5);
	private JTextField monthText = new JTextField(5);
	private JTextField dayText = new JTextField(5);
	
	private String Name;
	private String Address;
	String sYear;
	String sMonth;
	String sDay;
	private String Password;
	private String type;
	private int Year;
	private int Month;
	private int Day;	

	public void buildFrame(ArrayList<Account> acc) {
		button(acc);
        panel1.add(welcome);
        panel1.add(welcomes);
        panel1.add(accountSelect);
        panel1.setLayout(new GridLayout(3,1)); 
        
        panel2.add(name);
        panel2.add(nameText);
        panel2.add(address);
        panel2.add(addressText);
        
        panel3.add(year);
        panel3.add(yearText);         
        panel3.add(month);
        panel3.add(monthText);        
        panel3.add(day);
        panel3.add(dayText);       
        
        panel4.add(submit);
        panel4.add(exit);
        
        openPage.setLayout(new GridLayout(4,0));      	
		openPage.getContentPane().add(panel1);
		openPage.getContentPane().add(panel2);
		openPage.getContentPane().add(panel3);
		openPage.getContentPane().add(panel4);
		openPage.setBounds(350, 200, 450, 400);
		openPage.setVisible(true);
	}	
	private void button(ArrayList<Account> acc) {
		
		submit.addActionListener(new ActionListener() {//submit to check
			public void actionPerformed(ActionEvent ae) {
				submit(acc);
			}
		});
				
		exit.addActionListener(new ActionListener() {//close open account page
			public void actionPerformed(ActionEvent ae) {
				openPage.dispose();
			}
		});
	}
	private void submit(ArrayList<Account> acc) { //check whether the format is correct and whether text field is all filled, and open account.
		Name = nameText.getText();
		Address = addressText.getText();
		sYear = yearText.getText();
		sMonth = monthText.getText();
		sDay = dayText.getText();
				
		if(!Name.isEmpty()&&!Address.isEmpty()&&!sYear.isEmpty()&&!sMonth.isEmpty()&&!sDay.isEmpty()) 
		{
			if(correctFormat()==true) //format is true
			{
				int creditvalue=(int) ((Math.random()*8)+1);//random credit value to determine whether this user can open a new account
				if(creditvalue>=3) 
				{	
					establishAccount(acc);
				}
				else 
				{
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Your credit is not good!\n Credit Agency may help you!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					openPage.dispose();
				}
			}
		}
		else 
		{
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Some messages are not filled!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		}
	}

	private boolean correctFormat() {
		boolean flag = true;//judge the format
		int age = -1;		
		for(int i=0; i<Name.length(); i++) {//name must be capital letters or small letters
			if(!((Name.charAt(i)>='A'&&Name.charAt(i)<='Z')||(Name.charAt(i)>='a'&&Name.charAt(i)<='z'))) {
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Incorrect format!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
				nameText.setText("");
				flag = false;
				break;
			}
		}
		Calendar nowtime = Calendar.getInstance(); 	
		boolean birthday = true;
			Year = Integer.parseInt(sYear);
			Month = Integer.parseInt(sMonth);
			Day = Integer.parseInt(sDay);
			if(Day<1||Day>30) {//assume each month has 30 days
				flag = false;	
				birthday = false;
			}
			if(Month<1 || Month>12){	
				flag = false;
				birthday = false;
			}	
			if(Year>nowtime.get(Calendar.YEAR)||Year<1888) {
				flag = false;
				birthday = false;
			}	
			age = nowtime.get(Calendar.YEAR) - Year; //user's age 
			type = (String)accountSelect.getSelectedItem();
			if(age>=16) {
				if(type.equals("Junior Account")) {
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "You are older than 16, choose another account please", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					flag = false;
				}
			}
			else {
				if(!type.equals("Junior Account")) {
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "You can only choose Junior Account.", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					flag = false;
				}
			}
		
		if(!birthday) {
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Incorrect format of birthday! Try again!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			yearText.setText("");
			monthText.setText("");
			dayText.setText("");
		}	
		return flag;
	}
	private String createAccountID(ArrayList<Account> acc) {//create unique account ID.
		String accountID = null;
        int s=acc.size();
		int i=0; 
		while(true) {		
			boolean flag=true;
			switch (type) {
			case "Saver Account":
				i=100+s;
				accountID = "SA"+ i +(300000+(int)(Math.random()*100000))+""; break;
			case "Junior Account": 
				i=100+s;
				accountID = "JU"+ i +(300000+(int)(Math.random()*100000))+""; break;
			case "Current Account": 
				i=100+s;
				accountID = "CU"+ i +(300000+(int)(Math.random()*100000))+""; break;
			}
			for(Account temp: acc) {//duplicate
				if(temp.getID().equals(accountID)) {
					flag=false;
					break;
				}
			}
			s++;
			if(flag==true)
				break;			
		}
		return accountID;
	}
	private String createPIN(ArrayList<Account> acc) {//create unique PIN(password).
		String PIN=null;
		int s=acc.size();
	    int i=0; 
	    String val = "";  
        Random random = new Random();  
        for(int n = 0; n < 5; n++) 
        {                
           String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
           if( "char".equalsIgnoreCase(charOrNum) ) {  
               int temp = random.nextInt(2)% 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
           } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));
            }  
        }
		while(true) {
			boolean flag=true;
			switch (type) {
			case "Saver Account":
				i=100+s;
				String str1 = "" + i ;
			    PIN = "SA"+str1 + val +"";
			    break;
			case "Junior Account":
				i=100+s;
				String str2 = "" + i;
				PIN = "JU"+str2 + val +"";
				break;
			case "Current Account":
				i=100+s;
				String str3 = "" + i;
				PIN = "CU"+ str3 + val +"";
				break;
			}
			for(Account temp: acc) {//duplicate
				if(temp.getPIN().equals(PIN)) {
					flag=false;
					break;
				}
			}
			s++;
			if(flag==true)
				break;		
		}		
		return PIN;
	}	
	private void establishAccount(ArrayList<Account> acc) {//save new open account to ArrayList
		String ID = createAccountID(acc);
		Password = createPIN(acc); 		
		if(type.equals("Current Account")){
			CurrentAccount estAccount = new CurrentAccount();
			estAccount.setID(ID);
			estAccount.setPIN(Password);
			estAccount.setName(Name);
			estAccount.setType(type);
			estAccount.setAddress(Address);
			estAccount.setYear(Year);
			estAccount.setMonth(Month);
		    estAccount.setDay(Day);
			acc.add(estAccount);						
		}
		else if(type.equals("Junior Account")) {
			JuniorAccount estAccount = new JuniorAccount();
			estAccount.setID(ID);
			estAccount.setPIN(Password);
			estAccount.setName(Name);
			estAccount.setType(type);
			estAccount.setAddress(Address);
			estAccount.setYear(Year);
			estAccount.setMonth(Month);
			estAccount.setDay(Day);						
			acc.add(estAccount);
		}
		else {
			SaverAccount estAccount = new SaverAccount();			
			estAccount.setID(ID);
			estAccount.setPIN(Password);
			estAccount.setName(Name);
			estAccount.setType(type);
			estAccount.setAddress(Address);
			estAccount.setYear(Year);
			estAccount.setMonth(Month);
			estAccount.setDay(Day);						
			acc.add(estAccount);
		}		
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(null, "You have registered a new account successfully!\n Remember: Your AccountID is "+ ID +".\n Your PIN is "+ Password, "¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		openPage.dispose();
	}
}
	