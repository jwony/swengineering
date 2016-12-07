import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PhoneBookManagement extends PersonalAssistantSystem implements ListSelectionListener {
	private Vector<User> collection;
	private JPanel phoneBookPanel, listPanel;
	private JButton btnAdd, btnModify, btnDelete;
	private DefaultListModel listModel;
	private JList stdList;
	
	public void phoneBookPanel() {
		phoneBookPanel = new JPanel();
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(this);
		listPanel = new JPanel();
		//stdList = new JList(listModel);
		//stdList.setPreferredSize(new Dimension(200, 400));
		//stdList.addListSelectionListener(this);
		//JScrollPane spList = new JScrollPane(stdList);		
		//listPanel.add(spList);	
		phoneBookPanel.add(btnAdd);
		phoneBookPanel.add(listPanel);		
		btnModify = new JButton("MODIFY");		
		btnDelete = new JButton("DELETE");
		btnModify.addActionListener(this);
		btnDelete.addActionListener(this);
		phoneBookPanel.add(btnModify);
		phoneBookPanel.add(btnDelete);
		add(phoneBookPanel);
		setTitle("PhoneBook Management");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}

	public void valueChanged(ListSelectionEvent e) {
				
	}	
}
