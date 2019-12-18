package bank;
import javax.swing.JOptionPane;
public class JuniorAccount extends Account{
	private static final long serialVersionUID = 1L;

	public void Withdraw() {//This method is used to withdraw for Junior Account
		String withdraw = JOptionPane.showInputDialog("How much money do you want to withdraw: ");
				int withdrawValue = Integer.parseInt(withdraw);
		  if(withdrawValue>=0)
		  {
				if(balance>=withdrawValue) {
					balance -= withdrawValue;
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Successful withdraw!","¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					
				}	
				else {
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(null, "Too much money so you can't!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					
				}
		}		
		 else{
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "Invaild input!", "Wrong!", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			return ;
		}
	}

}
