package swengineering;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.*;

public class ContactFrame extends JFrame
{
	static String fileSrc="h:\\��ü\\��������Ʈ\\CList.txt";
	static String loginId;
	static HashMap<String,ContactInfo> c = new HashMap<String,ContactInfo>(); 
	
	JLabel labelName, labelAddress, labelEmail, labelPhone, labelFind, labelTitle;
	JTextField textName, textAddress, textEmail, textPhone, textFind;
	JButton buttonFind, buttonSave, buttonRemove;
	
	ContactFrame(String id)
	{
		loginId = id;
		File fn = new File(fileSrc);
		
		if(!fn.exists())
		{
			try{
				FileWriter fw = new FileWriter(fn);
				fw.close();
			}
			catch(Exception e){
				e.printStackTrace();	// ���� �������� �˰���� ��
				System.out.println("���� ����� ����");
			}
		}
		
		loadData();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		
		Font font1=new Font("���",Font.BOLD,30);
		Font font2=new Font("���",Font.PLAIN,17);
		Font font3=new Font("���",Font.PLAIN,14);
		
		setTitle("�ּҷ�");
		setSize(250,300);
		setLayout(null);
		
		labelTitle = new JLabel();
		labelName = new JLabel();
		labelAddress = new JLabel();
		labelEmail = new JLabel();
		labelPhone = new JLabel();
		labelFind = new JLabel();
		textName = new JTextField();
		textAddress = new JTextField();
		textEmail = new JTextField();
		textPhone = new JTextField();
		textFind = new JTextField();
		buttonFind = new JButton();
		buttonSave = new JButton();
		buttonRemove = new JButton();
		
		FrameManager.CreateJLabel(contentPane, labelTitle, "�ּҷ�", 100, 50, 80, 10, font1);
		FrameManager.CreateJLabel(contentPane, labelFind, "�̸� �˻�", 80, 20, 15, 65, font2);
		FrameManager.CreateJLabel(contentPane, labelName, "�̸�", 80, 20, 35, 105, font2);
		FrameManager.CreateJLabel(contentPane, labelAddress, "�ּ�", 80, 20, 35, 130, font2);
		FrameManager.CreateJLabel(contentPane, labelEmail, "�̸���", 80, 20, 20, 155, font2);
		FrameManager.CreateJLabel(contentPane, labelPhone, "��ȭ��ȣ", 80, 20, 10, 180, font2);
		FrameManager.CreateJTextField(contentPane, textFind, "", 80, 20, 85, 65, font2);
		FrameManager.CreateJTextField(contentPane, textName, "", 110, 20, 85, 105, font2);
		FrameManager.CreateJTextField(contentPane, textAddress, "", 110, 20, 85, 130, font2);
		FrameManager.CreateJTextField(contentPane, textEmail, "", 110, 20, 85, 155, font2);
		FrameManager.CreateJTextField(contentPane, textPhone, "", 110, 20, 85, 180, font2);
		FrameManager.CreateJButton(contentPane, buttonFind, "ã��", 55, 20, 170, 65, font3);
		FrameManager.CreateJButton(contentPane, buttonSave, "����", 70, 20, 40, 220, font2);
		FrameManager.CreateJButton(contentPane, buttonRemove, "����", 70, 20, 130, 220, font2);
		//textName.setEnabled(false);
		//textAddress.setEnabled(false);
		//textEmail.setEnabled(false);
		//txtPhone.setEnabled(false);
		
		buttonFind.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ContactInfo tmp = c.get(loginId+textFind.getText());
				
				if(tmp != null)
				{
					//System.out.println(tmp.getId());
					//System.out.println(loginId);
					//if(tmp.getId().equals(loginId))
					textName.setText(tmp.GetName());
					textAddress.setText(tmp.GetAddress());
					textEmail.setText(tmp.GetEmail());
					textPhone.setText(tmp.GetPhone());
				}
				else if(tmp == null)
					JOptionPane.showMessageDialog(null, "�˻� ����� �����ϴ�.", "�˸�", JOptionPane.OK_OPTION);
			}
		});
		
		buttonSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ContactInfo tmp = c.get(loginId+textName.getText());
				
				if(tmp == null)
				{
					String name = textName.getText();
					String add = textAddress.getText();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					
					c.put(loginId+name, new ContactInfo(name,add,email,phone,loginId));
					//System.out.println("������ �߰� �Ϸ�");
					JOptionPane.showMessageDialog(null, "�ּҷ� �߰� �Ϸ�!", "�˸�", JOptionPane.OK_OPTION);
				}
				else if(tmp != null)
				{
					String name = textName.getText();
					String add = textAddress.getText();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					
					tmp.SetName(name);
					tmp.SetAddress(add);
					tmp.SetEmail(email);
					tmp.SetPhone(phone);
					//System.out.println("������ ���� �Ϸ�");
					JOptionPane.showMessageDialog(null, "�ּҷ� ���� �Ϸ�!", "�˸�", JOptionPane.OK_OPTION);
				}
				
				textName.setText("");
				textAddress.setText("");
				textEmail.setText("");
				textPhone.setText("");
				saveData();
			}
		});
		
		buttonRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?","���",JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION)
				{
					c.remove(loginId+textName.getText());
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.", "�˸�", JOptionPane.OK_OPTION);
				}
				
				textName.setText("");
				textAddress.setText("");
				textEmail.setText("");
				textPhone.setText("");
				//System.out.println("������ ���� �Ϸ�");
				saveData();
			}
		});
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		
		
		int screenX=(int)(screen.getWidth() / 2 - this.getWidth() / 2);
		int screenY=(int)(screen.getHeight() / 2 - this.getHeight() / 2);
		
		setLocation(screenX,screenY);
		setVisible(true);
	}
	
	static void loadData()
	{
		FileReader fr = null;
		BufferedReader br = null;
		
		String id = null,name = null,address = null,email = null,phone = null,r = null;
		
		try
		{
			fr = new FileReader(fileSrc);
			br = new BufferedReader(fr);
			while((r=br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(r,",");
				
				while(st.hasMoreTokens())
				{
					name = st.nextToken();
					address = st.nextToken();
					email = st.nextToken();
					phone = st.nextToken();
					id = st.nextToken();
				} 
				
				ContactFrame.c.put(id+name,new ContactInfo(name,address,email,phone,id));	// ��ü�� ����� ����
			}
			fr.close();	// ���� �ݱ�
		}
	
		catch(Exception e)
		{
			e.printStackTrace();	// ���� �������� �˰���� ��
			System.out.println("���� ����� ����");
		}		
	}
	
	static void saveData()
	{
		FileWriter fw = null; 
		BufferedWriter bw = null;
		String str;
		
		try
		{
			fw = new FileWriter(fileSrc);
			bw = new BufferedWriter(fw);
			
			Set<String> keys = ContactFrame.c.keySet();
			Iterator<String> it = keys.iterator();
			
			while(it.hasNext())
			{
				ContactInfo temp = ContactFrame.c.get(it.next());
				String name = temp.GetName();
				String address = temp.GetAddress();
				String email = temp.GetEmail();
				String phone = temp.GetPhone();
				String id = temp.GetId();
				str = name+","+address+","+email+","+phone+","+id+"\r\n";
				bw.write(str);
			}
			
			bw.close();
			fw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("����� ����");
		}
	}
}
