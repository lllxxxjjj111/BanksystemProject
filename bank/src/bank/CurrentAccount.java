package bank;
import javax.swing.JOptionPane;

public class CurrentAccount extends Account{
	private static final long serialVersionUID = 1L;
	private int overdrawlimit = 5000;
	private int debt = 0;	
	public void setOverdrawlimit(int overdrawlimit) {
		this.overdrawlimit = overdrawlimit;
	}
	public int getOverdrawlimit() {
		return overdrawlimit;
	}
	
	public void setDebt(int debt) {
		this.debt = debt;
	}
	public int getDebt() {
		return debt;
	}		
	public void Deposit(){	//This method is used to deposit for Current Account.	
		String depositvalue = JOptionPane.showInputDialog("Please input how much money do you want to deposit: ");
				int deposit = Integer.parseInt(depositvalue);
				if(deposit>=0)
				{
					if(debt == 0) 
				   {
					balance += deposit;	
				   }
				    else 
				   {
				    	if(deposit > debt) {
							balance = deposit - debt;
							debt = 0;
						}
						else {
							debt -= deposit;
						}

				    }
				   Object[] options = {"OK"};
				   JOptionPane.showOptionDialog(null, "Successful deposit!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			    }
		     else
	         {
			    Object[] options = {"OK"};
			    JOptionPane.showOptionDialog(null, "Input Invalid!","Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);	
	         }			      
}
	
	public void Withdraw() {//This method is to withdraw from Current Account and do not need reservation, can take some money within a limit.        
		String withdraw = JOptionPane.showInputDialog("How much money do you want to withdraw: ");
				int withdrawValue = Integer.parseInt(withdraw);
				if(withdrawValue>=0) 
				{
				 if(overdrawlimit>= withdrawValue + debt - balance) 
				 {
						if(withdrawValue > balance) 
						{							
							withdrawValue -= balance;
							balance = 0;
							debt += withdrawValue;
							
						}	
						else 
						{
							balance -= withdrawValue;
						}
						Object[] options = {"OK"};
						JOptionPane.showOptionDialog(null, "Successful withdraw!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				 }
				 else 
				 {
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Too much money to take! Wrong!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);					
				 }
			   }
       else{
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Invalid input!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);}
		   }
	}
