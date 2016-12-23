import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.Vector;

public class FirstScreen extends PersonalAssistantSystem {
	private Vector <UserAccount> userAccountVector = new Vector <UserAccount>();		
	private JTextField userNameInput, userIdInput, userPasswordInput;
	private JButton buttonLogin, buttonRegister;
	
	public static void main(String[] args) {
		FirstScreen setFirstScreen = new FirstScreen();
		setFirstScreen.firstScreenPanel();
	}
	
	public void firstScreenPanel() {	
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(4,2));
		userNameInput = new JTextField(20);
		userIdInput = new JTextField(20);
		userPasswordInput = new JTextField(20);
		buttonLogin = new JButton("LOGIN");
		buttonRegister = new JButton("REGISTER");
		loginPanel.add(new JLabel("NAME"));
		loginPanel.add(userNameInput);
		loginPanel.add(new JLabel("ID"));
                loginPanel.add(userIdInput);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(userPassword);			     
		loginPanel.add(buttonLogin);
		loginPanel.add(buttonRegister);			     
		buttonLogin.addActionListener(this);
		buttonRegister.addActionListener(this);			     
		add(loginPanel);	
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {		
		Object source = e.getSource();	
		PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();
		if(source == buttonLogin){
		    	String loginId = userIdInput.getText();
 			String loginPassword = userPasswordInput.getText();
 			for(int index = 0 ;index <= userAccountVector.size(); index++){
 	                    UserAccount user = new UserAccount();
 	                    user = userAccountVector.elementAt(index);
 	            
 	                    if(loginId.equals(user.userId) && loginPassword.equals(user.userPassword)){
 	           	       mainScreen.mainPanel();		
 	                    }	
 	                System.out.println("로그인 실패하셨습니다!");
 	                userNameInput.setText("");
 	                userIdInput.setText("");
 	                userPasswordInput.setText("");
 		  	}
                 		 
 		}		 
 		
 		else if (source == buttonRegister){
 			String userName, userId, userPassword;
 			userName = userNameInput.getText();
 			userId = userIdInput.getText();
 			userPassword = userPasswordInput.getText();
 	                UserAccount user = new UserAccount(userName, userId, userPassword);
 	                userAccountVector.addElement(user);
 	  		userNameInput.setText("");
 			userIdInput.setText("");
 			userPasswordInput.setText("");
 		}
 	}
}	
