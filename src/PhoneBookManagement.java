import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PhoneBookManagement extends PersonalAssistantSystem {
	private Vector<User> collection;
	private JPanel phoneBookPanel, listPanel, listViewPanel, addUserPanel, contentPanel, labelPanel, textPanel, buttonPanel;
	private JButton deleteButton, saveButton, cancelButton;
	private JLabel nameLabel, phoneNumberLabel, addressLabel;
	private JTextField phoneBookName, phoneBookNumber, phoneBookAddress;
	private DefaultListModel listModel;
	private JList userList;
	
	public void phoneBookFrame() {
		collection = new Vector<User>();
		phoneBookPanel = new JPanel(new BorderLayout());
		addUserPanel = new JPanel(new BorderLayout());		
		contentPanel = new JPanel(new BorderLayout());		
		labelPanel = new JPanel(new GridLayout(3, 1, 0, 30));
		nameLabel = new JLabel("이름");
		phoneNumberLabel = new JLabel("번호");
		addressLabel = new JLabel("주소");
		textPanel = new JPanel(new GridLayout(3, 1, 0, 30));
		phoneBookName = new JTextField(20);
		phoneBookNumber = new JTextField(20);
		phoneBookAddress = new JTextField(50);
		buttonPanel = new JPanel();
		saveButton = new JButton("저장");
		saveButton.addActionListener(this);
		cancelButton = new JButton("취소");
		cancelButton.addActionListener(this);				
		labelPanel.add(nameLabel);
		labelPanel.add(phoneNumberLabel);
		labelPanel.add(addressLabel);
		textPanel.add(phoneBookName);
		textPanel.add(phoneBookNumber);
		textPanel.add(phoneBookAddress);
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		addUserPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.add(labelPanel, BorderLayout.WEST);
		contentPanel.add(textPanel, BorderLayout.CENTER);
		addUserPanel.add(buttonPanel, BorderLayout.PAGE_END);
		listPanel = new JPanel();
		listViewPanel = new JPanel();
		listModel = new DefaultListModel();
		userList = new JList(listModel);
		userList.setPreferredSize(new Dimension(500, 400));
		JScrollPane userScrollList = new JScrollPane(userList);
		listViewPanel.add(userScrollList);
		listPanel.add(listViewPanel);	
		deleteButton = new JButton("삭제");
		deleteButton.addActionListener(this);	
		listPanel.add(deleteButton);
		add(phoneBookPanel);
		phoneBookPanel.add(addUserPanel, BorderLayout.NORTH);
		phoneBookPanel.add(listPanel, BorderLayout.SOUTH);
		setTitle("PhoneBook Management");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent event){
		Object source = event.getSource();
		if(source == saveButton) {
			saveUserInformation();
		}	
		if(source == cancelButton) {
			formReset();			
		}
		if(source == deleteButton) {			
			deletePhoneBook();
		}
	}
	
	public void saveUserInformation() {
		String inputName = phoneBookName.getText();
		String inputPhoneNumber = phoneBookNumber.getText();
		String inputAddress = phoneBookAddress.getText();		
		if (inputName.equals("")|inputPhoneNumber.equals("")|inputAddress.equals("")) {
			JOptionPane.showMessageDialog(this, "이름/번호/주소가 모두 입력되지 않았습니다.");
		}
		else
			addPhoneBook(inputName, inputPhoneNumber, inputAddress);		
	}
	
	public void addPhoneBook(String inputName, String inputPhoneNumber, String inputAddress) {
		User user = new User(inputName, inputPhoneNumber, inputAddress);
		collection.addElement(user);
		listModel.addElement(user.toString());
		formReset();
	}
		
	public String addPhoneBookNameTest(String inputName, String inputPhoneNumber, String inputAddress) {
		User user = new User(inputName, inputPhoneNumber, inputAddress);
		return user.getName();		
	}
	
	public String addPhoneBookNumberTest(String inputName, String inputPhoneNumber, String inputAddress) {
		User user = new User(inputName, inputPhoneNumber, inputAddress);
		return user.getPhoneNumber();		
	}
	
	public String addPhoneBookAddressTest(String inputName, String inputPhoneNumber, String inputAddress) {
		User user = new User(inputName, inputPhoneNumber, inputAddress);
		return user.getAddress();		
	}

	public void deletePhoneBook() {
		int selectedUser = userList.getSelectedIndex();				
		listModel.removeElementAt(selectedUser);
		collection.removeElementAt(selectedUser);			
	}
	
	void formReset() {
		phoneBookName.setText("");
		phoneBookNumber.setText("");
		phoneBookAddress.setText("");
	}
}
