package bank;
import javax.swing.JOptionPane;
public class SaverAccount extends Account{
	private int reserve = 0;
	private boolean flag = false;
	
    public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public int getReserve() {
		return reserve;
	}

	public void setFlag(boolean flag) {//Reservation state:true is possible to withdraw
		this.flag = flag;
	}	
	public boolean getFlag() {
		return flag;
	}

	public void Withdraw() {//This method is used to withdraw for Saver Account. Before withdrawing, user must have a minimum days reservation
			if(reserve>0) 
			{
			if(flag == true) { //waiting is over
				balance -= reserve;				
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Successful reservation!", "¨r(¨s¨Œ¨t)¨q", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				flag = false;
				reserve = 0;
			}
			else {//waiting is beginning
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Wait 1 day please !", "Note", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			}	
		}
		else {
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null, "You must reserve first!", "Note", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);	
		}		
	}
}