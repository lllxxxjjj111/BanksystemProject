package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserGUI {	
	private JFrame accountpage = new JFrame("Account Interface");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();

	private JLabel name = new JLabel("Name:");
	private JLabel accountId = new JLabel("Account ID:");
	private JLabel accountType = new JLabel("Account Type:");
	private JLabel balance = new JLabel("Balance:");
	private JLabel unclear = new JLabel("Unclear:");
	private JLabel overdrawlimit = new JLabel("Overdraw Limit:");
	private JLabel debt = new JLabel("Debt");
	
	private JLabel nameText = new JLabel();
	private JLabel accountidText = new JLabel();
	private JLabel accounttypeText = new JLabel();
	private JLabel balanceText = new JLabel();
	private JLabel unclearText = new JLabel();
	private JLabel overdrawlimitText = new JLabel();
	private JLabel debtText = new JLabel();
	
	private JButton withdraw = new JButton("Withdraw");
	private JButton deposit = new JButton("Deposit");
	private JButton depositC = new JButton("Deposit\nCheque");
	private JButton suspend = new JButton("Suspend");
	private JButton close = new JButton("Delete\nAccount");
	private JButton reserve = new JButton("Reservation");
	private int index = 0;//which account

	public void buildFrame(ArrayList<Account> acc, int index) {		
		this.index =index;
		clearFund(acc);
		button(acc);
		
		nameText.setText(acc.get(index).getName());
		accountidText.setText(acc.get(index).getID());
		accounttypeText.setText(acc.get(index).getType());
		balanceText.setText(acc.get(index).getBalance()+"");
		unclearText.setText(acc.get(index).getUnclear()+"");		
		
	     panel1.add(name);	  
	     panel1.add(nameText);	     
	     panel1.add(accountId);	     
	     panel1.add(accountidText);	    
	     panel1.add(accountType);	     
	     panel1.add(accounttypeText);	     
	     panel1.add(balance);
	     panel1.add(balanceText);	     
	     panel1.add(unclear);
	     panel1.add(unclearText);
	     panel1.setLayout(new GridLayout(0,4));
	     	 
	     panel2.add(withdraw);  
	     panel2.add(deposit);
	     panel2.add(depositC);
	     panel2.add(suspend);
	     panel2.add(close);
	     panel2.setLayout(new GridLayout(0,3));

		if(acc.get(index).getType().equals("Current Account")) //current account can have a debt
		{
			overdrawlimitText.setText(((CurrentAccount) acc.get(index)).getOverdrawlimit()+"");
			debtText.setText(((CurrentAccount) acc.get(index)).getDebt()+"");			
		    panel1.add(overdrawlimit);
		    panel1.add(overdrawlimitText);
		    panel1.add(debt);
		    panel1.add(debtText);
		}
		if(acc.get(index).getType().equals("Saver Account"))//save account must have a reservation before withdraw
		{
			((SaverAccount) acc.get(index)).setFlag(true);
		    panel2.add(reserve);	
		}
		accountpage.getContentPane().add(BorderLayout.NORTH,panel1);
		accountpage.getContentPane().add(BorderLayout.SOUTH,panel2);
		accountpage.setBounds(350, 160, 500, 200);
		accountpage.setVisible(true);
	}

	private void button(ArrayList<Account> acc) {
		
		withdraw.addActionListener(new ActionListener() {//jump to Withdraw GUI if press button
			public void actionPerformed(ActionEvent ae) {
				WithdrawonGUI(acc.get(index));				
			}
		});
		
		deposit.addActionListener(new ActionListener() {//deposit money to balance
			public void actionPerformed(ActionEvent ae) {
				acc.get(index).Deposit();
				balanceText.setText(acc.get(index).getBalance()+"");
				if(acc.get(index).getType().equals("Current Account")) {
					debtText.setText(((CurrentAccount) acc.get(index)).getDebt()+"");
				}
			}
		});
		suspend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				acc.get(index).setState(false);
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Your account is suspended!", "¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				accountpage.dispose();
			}
		});
		
		depositC.addActionListener(new ActionListener() {//Deposit money to unclear funds. This funds will be cleared until next login.
			public void actionPerformed(ActionEvent ae) {
				acc.get(index).DepositCheque();
				unclearText.setText(acc.get(index).getUnclear()+"");
			}
		});
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				closeAccount(acc);
			}
		});
		
		reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				String reservation = JOptionPane.showInputDialog("How much money do you want to reserve: ");
						int reserveValue = Integer.parseInt(reservation);
						if(reserveValue>acc.get(index).getBalance()){
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Too much to take! It is not enough!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
						}
						else {
							((SaverAccount) acc.get(index)).setReserve(reserveValue);
							Object[] options = {"OK"};
							JOptionPane.showOptionDialog(null, "Successful! You can get the money $"+((SaverAccount) acc.get(index)).getReserve()+" after 1 day!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
							((SaverAccount) acc.get(index)).setFlag(false);
						}					
					}
				});}
	
  public void  WithdrawonGUI(Account acc){
	  
		    JFrame frame = new JFrame("Withdraw");
			JPanel panel = new JPanel();
			JLabel welcome = new JLabel("Please input your Account ID and your Account Password to indentify.");
			JButton submit = new JButton("Submit");
			JButton exit = new JButton("Exit");
			JLabel id = new JLabel("Account ID:");
			JLabel password = new JLabel("Account Password:");
            JTextField inputId = new JTextField(10);
			JPasswordField inputPassword = new JPasswordField(11);
			
			welcome.setBounds(50, 10, 450, 100);
		    welcome.setFont(new Font("Arial", Font.BOLD, 11)); 
	        panel.add(welcome);			
			panel.setLayout(null);
			id.setBounds(50, 150, 100, 50);
		    id.setFont(new Font("Arial", Font.BOLD, 11));
		    panel.add(id);
		    inputId.setBounds(200,150, 250, 50);
		    panel.add(inputId);	        
		    password.setBounds(50, 200, 100, 50);
		    password.setFont(new Font("Arial", Font.BOLD, 10));
		    panel.add(password);		        
		    inputPassword.setBounds(200,200, 250, 50);
		    panel.add(inputPassword);		          
		    submit.setBounds(50,250, 100, 50);
		    submit.setFont(new Font("Arial", Font.BOLD, 11));
		    panel.add(submit);		        
		    exit.setBounds(350,250, 100, 50);
		    exit.setFont(new Font("Arial", Font.BOLD, 11));
		    panel.add(exit);	
		    frame.setBounds(370, 180, 500, 350);
		    frame.setContentPane(panel);
			frame.setVisible(true);
			
			exit.addActionListener(new ActionListener() {    //exit this GUI
	            public void actionPerformed(ActionEvent ae) {
	                frame.dispose();
	            }
	        });	 	
	        submit.addActionListener(new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent ae) {
	                if(inputId.getText().equals(acc.getID())&&inputPassword.getText().equals(acc.getPIN())){//input information is correct
	                    frame.dispose();
	                    acc.Withdraw();//jump to withdraw GUI
	                    balanceText.setText(acc.getBalance()+"");
	    				if(acc.getType().equals("Current Account")) {
	    					debtText.setText(((CurrentAccount) acc).getDebt()+"");
	    				}
	                }
	                else{
	                    Object[] options = {"OK"};
	                    JOptionPane.showOptionDialog(null, "May be your message is wrong, check it!", "Wrong", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
	                    frame.dispose();
	                }	                
	            }
	        });      
	               
	    }
	private void closeAccount(ArrayList<Account> acc) {
		Object[] options1 = {"Yes", "No"};
		int choose = JOptionPane.showOptionDialog(null, "Are you sure you want to delete your account?", "!!!", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);
		if(choose == 0){		
			 if(acc.get(index).getBalance()==0&&acc.get(index).getUnclear()==0)
			{
				if(acc.get(index).getType().equals("Current Account"))//current account must consider debt
				{
					if(((CurrentAccount) acc.get(index)).getDebt()==0) 
					{
						acc.remove(index);//delete this account from array list
						Object[] options = {"OK"};
						JOptionPane.showOptionDialog(null, "Successful delete!", "¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						accountpage.dispose();
					}
					else 
					{
						Object[] options = {"OK"};
						JOptionPane.showOptionDialog(null, "You still have the debt so you can't do this!", "!!!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);				
					}
				}
				else //not current account, do not consider debt
				{
					acc.remove(index);
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Successful delete!", "¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			
					accountpage.dispose();
				}
			}
			 else if(acc.get(index).getBalance()!=0||acc.get(index).getUnclear()!=0) {
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "You still have money in this account!", "!!!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			}
		}
	}
	private void clearFund(ArrayList<Account> acc) {
		acc.get(index).setBalance(acc.get(index).getBalance()+acc.get(index).getUnclear());
		acc.get(index).setUnclear(0);
		if(acc.get(index).getType().equals("Current Account")) {
			if(((CurrentAccount) acc.get(index)).getDebt()>0&&acc.get(index).getBalance()>0) {
				if(acc.get(index).getBalance() < ((CurrentAccount) acc.get(index)).getDebt()){//pay the debt
					((CurrentAccount) acc.get(index)).setDebt(((CurrentAccount) acc.get(index)).getDebt()-acc.get(index).getBalance());
					acc.get(index).setBalance(0);
				}
				else {
					acc.get(index).setBalance(acc.get(index).getBalance()-((CurrentAccount) acc.get(index)).getDebt());
					((CurrentAccount) acc.get(index)).setDebt(0);
				}
			}
		}
	}
	
	
}