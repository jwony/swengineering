import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PhoneBookManagement extends PersonalAssistantSystem implements ListSelectionListener {
	private Vector<User> collection;
	private JPanel phoneBookPanel, listPanel, listViewPanel, addUserPanel, labelPanel, textPanel, contentPanel, buttonPanel;
	private JButton btnDelete, btnAdd, btnCancel;
	private JLabel nameLabel, phoneNumberLabel, addressLabel;
	private JTextField nameText, phoneNumberText, addressText;	
	private DefaultListModel listModel;
	private JList stdList;
	
	public void phoneBookPanel() {
		phoneBookPanel = new JPanel(new BorderLayout());
		addUserPanel = new JPanel(new BorderLayout());		
		contentPanel = new JPanel(new BorderLayout());
		
		labelPanel = new JPanel(new GridLayout(3,1,10,10));
		nameLabel = new JLabel("이름");
		phoneNumberLabel = new JLabel("번호");
		addressLabel = new JLabel("주소");
		
		textPanel = new JPanel(new GridLayout(3,1,50,50));		
		nameText = new JTextField(20);
		phoneNumberText = new JTextField(20);
		addressText = new JTextField(50);
		
		buttonPanel = new JPanel();
		btnAdd = new JButton("SAVE");
		btnAdd.addActionListener(this);
		btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(this);
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		labelPanel.add(nameLabel);		
		labelPanel.add(phoneNumberLabel);		
		labelPanel.add(addressLabel);
		textPanel.add(nameText);
		textPanel.add(phoneNumberText);
		textPanel.add(addressText);		
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnCancel);		
		addUserPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.add(labelPanel, BorderLayout.WEST);
		contentPanel.add(textPanel, BorderLayout.EAST);
		addUserPanel.add(buttonPanel, BorderLayout.PAGE_END);		
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
		
		listPanel = new JPanel();		
		listViewPanel = new JPanel();
		listModel = new DefaultListModel();
		stdList = new JList(listModel);
		stdList.setPreferredSize(new Dimension(200, 400));
		stdList.addListSelectionListener(this);
		JScrollPane spList = new JScrollPane(stdList);		
		listViewPanel.add(spList);	
		listPanel.add(listViewPanel);	
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(this);
		listPanel.add(btnDelete);
		listPanel.add(addUserPanel);
		add(phoneBookPanel);
		phoneBookPanel.add(addUserPanel, BorderLayout.NORTH);
		phoneBookPanel.add(listPanel, BorderLayout.SOUTH);
		
		setTitle("PhoneBook Management");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);		
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();				
		
		if(source == btnAdd){		
			User user = new User(nameText.getText(), phoneNumberText.getText(),addressText.getText());
			collection.addElement(user);
			
			// 리스트에 이름을 추가
			listModel.addElement(nameText.getText());
			//reset();
			
		}	
	}

	public void valueChanged(ListSelectionEvent e) {
				
	}	
}
