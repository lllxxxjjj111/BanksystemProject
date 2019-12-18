package bank;
import javax.swing.*;
import java.io.*;

public abstract class Account implements Serializable{

	protected String ID;
	protected String PIN;
	protected String name;
	protected String address;
	protected String type;

	protected int balance = 0;			
	protected int unclear = 0;
	
	protected int Year;
	protected int Month;
	protected int Day;
	protected boolean state = true; //Suspend State
	
	public void setID(String ID) {//Account number
		this.ID = ID;
	}
	public String getID() {
		return ID;
	}
	
    public void setPIN(String PIN) {//Password
		this.PIN = PIN;
	}
	public String getPIN() {
		return PIN;
	}

	public void setName(String name) {//User name
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
    public void setAddress(String address) {//User address
		this.address = address;
	}
	public String getAddress() {
		return address;
	}

	public void setType(String type) {//Account type
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public void setBalance(int balance) {//Account balance
		this.balance = balance;
	}
	public int getBalance() {
		return balance;
	}

	public void setUnclear(int unclear) {//Unclear funds 
		this.unclear = unclear;
	}
	public int getUnclear() {
		return unclear;
	}
	
	public void setYear(int Year) {//birth of year
		this.Year = Year;
	}
	public int getYear() {
		return Year;
	}

	public void setMonth(int Month) {//birth of month
		this.Month = Month;
	}
	public int getMonth() {
		return Month;
	}

	public void setDay(int Day) {//birth of day
		this.Day = Day;
	}
	public int getDay() {
		return Day;
	}

	public void setState(boolean flag) {//whether this account is in Suspend State or not
		state = flag;
	}
	public boolean getState() {
		return state;
	}

	public void Deposit(){//To deposit cleared funds
		String depositvalue = JOptionPane.showInputDialog(null,"Please input how much you want to deposit: \n","Deposit", JOptionPane.PLAIN_MESSAGE);
		int deposit = Integer.parseInt(depositvalue);
			if(deposit>=0) 
			{				
				balance += deposit;
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Successful deposit!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);			
			}
			else if(deposit<0||depositvalue.isEmpty())
			{
			  Object[] options = {"OK"};
			  JOptionPane.showOptionDialog(null, "Invalid input!","Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		    }
	}

	public void DepositCheque() {
		String  depositC = JOptionPane.showInputDialog("Please input how much you want to deposit to Cheque: ");
		int depositCvalue = Integer.parseInt(depositC);
			if(depositCvalue>=0)  
			{				
				unclear += depositCvalue;
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Successful deposit to cheque!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			}
	       else{
			    Object[] options = {"OK"};
			    JOptionPane.showOptionDialog(null, "Invalid input!","Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		    }
	}
	
	public void Suspend(){//suspend state				
		state = false;
	}

	public void Restart(){//over suspend and restart this account 				
		state = true;
	}
	abstract public void Withdraw(); 	
}
