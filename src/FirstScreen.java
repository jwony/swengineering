import java.awt.event.ActionEvent;
import javax.swing.*;

public class FirstScreen extends PersonalAssistantSystem {
	private JPanel loginPanel;
	private JLabel id, pw;		
	private JTextField userId, userPassword;
	private JButton loginButton;
	
	public static void main(String[] args) {
		FirstScreen setFirstScreen = new FirstScreen();
		setFirstScreen.firstScreenPanel();
	}
	
	public void firstScreenPanel() {	
		loginPanel = new JPanel();
		id = new JLabel("ID");	
		pw = new JLabel("PASSWORD");
		userId = new JTextField(20);
		userPassword = new JTextField(20);
		loginButton = new JButton("LOGIN");
		loginPanel.add(id);
		loginPanel.add(pw);
		loginPanel.add(userId);
		loginPanel.add(userPassword);	
		loginPanel.add(loginButton);
		loginButton.addActionListener(this);
		add(loginPanel);	
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent event) {		
		Object source = event.getSource();	
		PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();
		if(source == loginButton){
			mainScreen.mainPanel();			
		}		
	}
}
