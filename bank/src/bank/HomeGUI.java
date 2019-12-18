package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
public class HomeGUI {	
	private JFrame homePage = new JFrame("Bank System");
	private JPanel panel = new JPanel();
	private JLabel welcome = new JLabel("Welcome to Banking System! Please select your operation.");
	private JButton login = new JButton("Login");
	private JButton register = new JButton("Open Account");
	private JButton exit = new JButton("Exit");
	public HomeGUI(ArrayList<Account> account) {
		frameBuild(account);
	}	
	private void frameBuild(ArrayList<Account> account) {		
		login.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent event) {
				LoginGUI loginGUI = new LoginGUI();          //press the login button to jump to the login page
				loginGUI.frameBuild(account);
			}
		});				
		register.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent event) {
				OpenAccountGUI registerGUI = new OpenAccountGUI();   //press the open account button to jump to the register page
				registerGUI.buildFrame(account);
			}
		});				
		exit.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				ExitGUI exit = new ExitGUI();                   //press the exit button to exit the program
				try {
					exit.exitSystem(account);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		panel.add(login);
		panel.add(register);
		panel.add(exit);

		homePage.getContentPane().add(BorderLayout.NORTH,welcome);
        homePage.getContentPane().add(BorderLayout.SOUTH,panel);
		homePage.setSize(400, 100);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePage.setVisible(true);		
	}

}


