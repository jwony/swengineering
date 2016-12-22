import java.awt.event.*;
import javax.swing.*;

public class PersonalAssistantSystem extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JButton accountButton, phoneBookButton, scheduleButton;		
	
	public void mainPanel() {
		mainPanel = new JPanel();
		accountButton = new JButton("Account");
		phoneBookButton = new JButton("PhoneBook");
		scheduleButton = new JButton("Schedule");
		accountButton.addActionListener(this);
		phoneBookButton.addActionListener(this);
		scheduleButton.addActionListener(this);
		mainPanel.add(accountButton);
		mainPanel.add(phoneBookButton);
		mainPanel.add(scheduleButton);
		add(mainPanel);
		setTitle("S/E Project");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent event) {		
		Object source = event.getSource();
		PhoneBookManagement phoneBookScreen = new PhoneBookManagement();	
		if(source == accountButton){	
			
		}
		if(source == phoneBookButton){	
			phoneBookScreen.phoneBookPanel();
		}
		if(source == scheduleButton){	
			
		}
	}
}
