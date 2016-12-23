import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AccountManagement extends PersonalAssistantSystem implements ActionListener{
	private JPanel buttonPanel;
	private JButton changeIdAndPassword, logOut;

	public void accountFrame(){
		setTitle("Account Management");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,1));		
               changeIdAndPassword = new JButton("Change Id And Password");
               changeIdAndPassword.addActionListener(this);
               logOut = new JButton("logOut");
               logOut.addActionListener(this);
       
               buttonPanel.add(changeIdAndPassword);
               buttonPanel.add(logOut);
               add(buttonPanel);
	}
	
    public void actionPerformed(ActionEvent e){
    	Object source = e.getSource();
    	ChangeIdAndPassword changeIdAndPasswordScreen = new ChangeIdAndPassword();
    	if(source == changeIdAndPassword){
            changeIdAndPasswordScreen.changeAccountFrame();
    		
    	}
    
    	else if(source == logOut){
            FirstScreen firstScreen = new FirstScreen();
            firstScreen.firstScreenPanel();
    	}
    }

	
}
