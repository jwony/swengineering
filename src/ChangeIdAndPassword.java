import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class ChangeIdAndPassword extends PersonalAssistantSystem implements ActionListener{
	private Vector <UserAccount> userAccountVector = new Vector <UserAccount>();
	private JPanel checkAccountPanel, changeAccountPanel;
	private JButton buttonCheckAccount, buttonChangeAccout;
	private JTextField originalUserId, newUserId, originalUserPassword, newUserPassword;
	
	public void changeAccountFrame(){
  		setTitle("Account Change");
  		setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        checkAccountPanel = new JPanel();
        checkAccountPanel.setLayout(new GridLayout(5,2));   
	    checkAccountPanel.add(new JLabel("Original Id"));
	    originalUserId = new JTextField(20);
	    checkAccountPanel.add(originalUserId);
	    checkAccountPanel.add(new JLabel("New Id"));
	    newUserId = new JTextField(20);
	    checkAccountPanel.add(newUserId);
	    checkAccountPanel.add(new JLabel("Original Password"));
	    originalUserPassword = new JTextField(20);
	    checkAccountPanel.add(originalUserPassword);
	    checkAccountPanel.add(new JLabel("New Password"));
	    newUserPassword = new JTextField(20);	
	    checkAccountPanel.add(newUserPassword);
	    buttonCheckAccount = new JButton("check!");
	    buttonCheckAccount.addActionListener(this);
	    checkAccountPanel.add(buttonCheckAccount);
        add(checkAccountPanel);
	        
	}
	public void actionPerformed(ActionEvent e){
		 Object source = e.getSource();
		 PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();
		 
		 if(source == buttonCheckAccount){
			
			String userName = "";
		 	String userId = newUserId.getText();
			String userPassword = newUserPassword.getText();
			UserAccount user = new UserAccount(userName, userId, userPassword); 			
 			userAccountVector.addElement(user);
	        System.out.println("¼º°ø!");
	        mainScreen.mainPanel();
	              
	 
            }
        }
	 }


