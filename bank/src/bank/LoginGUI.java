package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginGUI {
	
	private JFrame loginPage = new JFrame("Login Interface");
	JPanel panel = new JPanel();
	private JButton submit = new JButton("Submit");
	private JButton cancel = new JButton("Cancel");
	
	private JLabel welcome = new JLabel("Please input your Account ID and your Account Password (PIN).");
	private JLabel id = new JLabel("Account ID:");
	private JLabel password = new JLabel("Account Password:");
	
	private JTextField inputId = new JTextField(10);
	private JPasswordField inputPassword = new JPasswordField(10);
	
	public void frameBuild(ArrayList<Account> account) {
		
		button(account);
		
	    panel.setLayout(null);
	    welcome.setBounds(170, 10, 600, 100);
	    welcome.setFont(new Font("Arial", Font.BOLD, 15)); 
        panel.add(welcome);
        
	    id.setBounds(170, 120, 100, 50);
        id.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(id);

        inputId.setBounds(350,120, 350, 50);
        panel.add(inputId);
        
        password.setBounds(170, 200, 200, 50);
        password.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(password);
        
        inputPassword.setBounds(350,200, 350, 50);
        panel.add(inputPassword);
          
        submit.setBounds(170,300, 100, 80);
        submit.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(submit);
        
        cancel.setBounds(600,300, 100, 80);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(cancel);
		
        loginPage.setContentPane(panel);
        loginPage.setSize(900,600);
		loginPage.setVisible(true);		
	}	
	private void button(ArrayList<Account> account) {
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				submit(account);
			}
		});		

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				loginPage.dispose();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private void submit(ArrayList<Account> account) {
		int i=0;
		boolean state = false;                      //state is to judge whether this account is suspended or not
				
		for(i=0;i<account.size();i++){			
			if(account.get(i).getID().equals(inputId.getText()))
			{
				state=true;
				if(account.get(i).getPIN().equals(inputPassword.getText()))
				{
					if(account.get(i).getState()) {//all correct and not suspend
						UserGUI user = new UserGUI();
						user.buildFrame(account,i);
						loginPage.dispose();
					}
					else {
						Object[] options = {"Yes","No"};
						int choice=JOptionPane.showOptionDialog(null, "This account is suspended!\nDo you want to restart it ?", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
						if(choice==0) {//Restart this account
							account.get(i).setState(true);
							UserGUI user = new UserGUI();
							user.buildFrame(account,i);
							loginPage.dispose();
						}
					}
				}
				else {//password is wrong
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Maybe your password is wrong!\nPlease check it!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
				}
			}	
		}
		if(state!=true&&i==account.size()) {//seek for every account but not exit this one
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "This Account ID does not exit!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		}
	}

	
}
