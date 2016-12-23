import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;

public class FirstScreen extends PersonalAssistantSystem {
	private JPanel loginPanel;
	private JLabel id, pw;	
	private Vector <UserAccount> userAccountVector = new Vector <UserAccount>();		
	private JTextField userNameInput, userIdInput, userPasswordInput;
	private JButton loginButton, registerButton;
	
	public static void main(String[] args) {
		FirstScreen setFirstScreen = new FirstScreen();
		setFirstScreen.firstScreenFrame();
	}
	
	public void firstScreenFrame() {	
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(4,2));
		userNameInput = new JTextField(20);
		userIdInput = new JTextField(20);
		userPasswordInput = new JTextField(20);
		loginButton = new JButton("LOGIN");
		registerButton = new JButton("REGISTER");
		loginPanel.add(new JLabel("NAME"));
		loginPanel.add(userNameInput);
		loginPanel.add(new JLabel("ID"));
                loginPanel.add(userIdInput);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(userPasswordInput);			     
		loginPanel.add(loginButton);
		loginPanel.add(registerButton);			     
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		add(loginPanel);	
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		setResizable(false);
	}
	
	public String login(String parameterLoginId, String parameterLoginPassword){
            String loginId = parameterLoginId;
            String loginPassword = parameterLoginPassword;
	    PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();

            for(int index = 0 ;index <= userAccountVector.size(); index++){
 	        UserAccount userObject = new UserAccount();
 		userObject = userAccountVector.elementAt(index);
 				
 		if(loginId.equals(userObject.userId) && loginPassword.equals(userObject.userPassword)){
 		    mainScreen.mainPanel();
 		    return ("Success");
 	        }
 				
 	    }   
	   return("Fail");
	}
	
	public void actionPerformed(ActionEvent event) {		
		Object source = event.getSource();	
		PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();

		if(source == loginButton){
			String parameterLoginId = userIdInput.getText();
 			String parameterLoginPassword = userPasswordInput.getText();
			String loginResult = login(parameterLoginId, parameterLoginPassword);
 		   	System.out.println(loginResult);	 
 		}		 
 		
 		else if (source == registerButton){
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


