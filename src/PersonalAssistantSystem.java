import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class PersonalAssistantSystem extends JFrame implements ActionListener {
	private JPanel mainPanel, selectMenuPanel;
	private JButton accountButton, phoneBookButton, scheduleButton;		
	
	public void mainPanel() {
		mainPanel = new JPanel(new BorderLayout());
		selectMenuPanel = new JPanel();
		accountButton = new JButton("Account");
		phoneBookButton = new JButton("PhoneBook");
		scheduleButton = new JButton("Schedule");
		accountButton.addActionListener(this);
		phoneBookButton.addActionListener(this);
		scheduleButton.addActionListener(this);
		selectMenuPanel.add(accountButton);
		selectMenuPanel.add(phoneBookButton);
		selectMenuPanel.add(scheduleButton);		
		mainPanel.add(selectMenuPanel, BorderLayout.CENTER);
		add(mainPanel);
		setTitle("S/E Project");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent event) {		
		Object source = event.getSource();
		//AccountManagement accountScreen = new AccountManagement();
		PhoneBookManagement phoneBookScreen = new PhoneBookManagement();
		ScheduleFrame scheduleScreen = new ScheduleFrame();

		if(source == accountButton){	
		//	accontScreen.accountFrame();
		}
		if(source == phoneBookButton){	
			phoneBookScreen.phoneBookFrame();
		}
		if(source == scheduleButton){	
			scheduleScreen.scheduleFrame();			
		}
	}
}
