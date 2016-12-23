import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AccountManagement extends PersonalAssistantSystem implements ActionListener{
	private JPanel buttonPanel;
	private JButton changeId, changePassword, logOut;

	public void accountFrame(){
		setTitle("Account Management");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,1));		
        changeId = new JButton("ID 변경");
        changeId.addActionListener(this);
        changePassword = new JButton("PASSWORD 변경");
        changePassword.addActionListener(this);
        logOut = new JButton("logOut");
        logOut.addActionListener(this);
       
        buttonPanel.add(changeId);
        buttonPanel.add(changePassword);
        buttonPanel.add(logOut);
        add(buttonPanel);
	}
	
    public void actionPerformed(ActionEvent e){
    	Object source = e.getSource();
    	
    	if(source == changeId){
    		
    	}
    	if(source == changePassword){
    		
    	}
    	else if(source == logOut){
            FirstScreen firstScreen = new FirstScreen();
            firstScreen.firstScreenPanel();
    	}
    }

	
}
