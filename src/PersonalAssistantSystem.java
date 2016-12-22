import java.awt.event.*;
import javax.swing.*;


public class PersonalAssistantSystem extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JButton btnAccount, btnPhoneBook, btnSchedule;	
//	Comparator comparator;		
	
	public void mainPanel() {
		mainPanel = new JPanel();
		btnAccount = new JButton("Account");
		btnPhoneBook = new JButton("PhoneBook");
		btnSchedule = new JButton("Schedule");
		btnAccount.addActionListener(this);
		btnPhoneBook.addActionListener(this);
		btnSchedule.addActionListener(this);
		mainPanel.add(btnAccount);
		mainPanel.add(btnPhoneBook);
		mainPanel.add(btnSchedule);
		add(mainPanel);
		setTitle("S/E Project");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {		
		Object source = e.getSource();
		PhoneBookManagement phoneBookScreen = new PhoneBookManagement();	
		if(source == btnPhoneBook){	
			phoneBookScreen.phoneBookPanel();
		}				
	}
}
