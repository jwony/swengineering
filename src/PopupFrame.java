import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.*;

public class PopupFrame extends JFrame
{
	String [] days = {"��","��","ȭ","��","��","��","��"};
	JPanel panelMemo, panelSchedule, panelButton;
	JEditorPane textSchedule, textMemo;
	JScrollPane scrollSchedule, scrollMemo;
	Calendar calendar;
	String loginId;
	PopupFrame(){};
	
	PopupFrame(int y, int m, int d, int d2, String id)
	{
		loginId=id;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setDate(y,m,d);
		setTitle(y+"�� "+m+"�� "+d+"�� "+days[d2-1]+"����");
		
		ScheduleInit();
		ButtonInit();
		
		add(panelSchedule);
		add(panelButton);
		
		setSize(500,350);
		setVisible(true);
	}

	void ScheduleInit()
	{
		panelSchedule = new JPanel();
		panelSchedule.setLayout(new BorderLayout());
		
		JLabel labelSchedule = new JLabel("������",JLabel.CENTER);
		panelSchedule.add(labelSchedule,BorderLayout.NORTH);
		
		textSchedule = new JEditorPane();
		textSchedule.setPreferredSize(new Dimension(200,350));
		
		scrollSchedule = new JScrollPane(textSchedule);
		
		panelSchedule.add(scrollSchedule,BorderLayout.CENTER);
		panelSchedule.setLocation(40,40);
		panelSchedule.setSize(400,200);
		panelSchedule.setBackground(Color.MAGENTA);
		panelSchedule.setVisible(true);
	}
	
	void ButtonInit()
	{
		panelButton = new JPanel();
		
		JButton okButton = new JButton("SAVE");
		okButton.addActionListener(new PopupActionListener());
		panelButton.add(okButton);
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new PopupActionListener());
		panelButton.add(noButton);
		
		panelButton.setSize(200,100);
		panelButton.setLocation(260,250);
		panelButton.setVisible(true);
	}
	
	void setDate(int y,int m,int d)
	{
		calendar=Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, y);
		calendar.set(Calendar.MONTH, m-1);
		calendar.set(Calendar.DAY_OF_MONTH, d);
	}
	
	void CloseFrame()
	{
		dispose();
	}
	
	void saveData(String data1,String data2)
	{
		if(!data1.equals(""))
			Schedule.v.add(new Cel(data1, calendar, loginId));

		if(!data2.equals(""))
			Schedule.v2.add(new Cel(data2, calendar, loginId));
		
		Schedule.PrintCalAll();
		Schedule.SaveData();
		// Schedule.myD.RefreshData();
	}
	

	class PopupActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();
			
			if(button.getText().equals("SAVE"))
			{
				if(!textSchedule.getText().equals(""))
				{
					StringTokenizer st = new StringTokenizer(textSchedule.getText(),"\n\r");
				
					while(st.hasMoreTokens())
						saveData(st.nextToken(),"");
					}
				
				CloseFrame();
			}
			else if(button.getText().equals("CANCEL"))
				CloseFrame();
		}
	}
}
