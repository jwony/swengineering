import java.awt.event.ActionEvent;
import javax.swing.*;

public class FirstScreen extends PersonalAssistantSystem {
	private JPanel loginPanel;
	private JLabel id, pw;		
	private JTextField txtId, txtPw;
	private JButton btnLogin;
	
	public static void main(String[] args) {
		FirstScreen setFirstScreen = new FirstScreen();
		setFirstScreen.firstScreenPanel();
	}
	
	public void firstScreenPanel() {	
		loginPanel = new JPanel();
		id = new JLabel("ID");	
		pw = new JLabel("PASSWORD");
		txtId = new JTextField(20);
		txtPw = new JTextField(20);
		btnLogin = new JButton("LOGIN");
		loginPanel.add(id);
		loginPanel.add(pw);
		loginPanel.add(txtId);
		loginPanel.add(txtPw);	
		loginPanel.add(btnLogin);
		btnLogin.addActionListener(this);
		add(loginPanel);	
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {		
		Object source = e.getSource();	
		PersonalAssistantSystem mainScreen = new PersonalAssistantSystem();
		if(source == btnLogin){
			mainScreen.mainPanel();			
		}		
	}
}
