package sw_engineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class scheduleFrame extends JFrame
{
	String [] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	Calendar todayCalendar, calendar, memodayCalendar;
	int year, month, date, today, day, memoday=0;
	JButton[] calendarButton = new JButton[49];
	JButton buttonBefore, buttonAfter;
	JPanel calendarPanel, headPanel, memoPanel, datePanel, selectPanel, schedulePanel;
    JTextField textMonth, textYear;
    JTextArea textMemo, textSchedule;
    JScrollPane scrollMemo, scrollSchedule;
    JRadioButton [] radioSelect; 
	JLabel labelYear, labelMonth, labelDate, labelDay, labelCalendar;
	popupFrame popupframe ;
	String loginId;
	int todayNumber;
	
	scheduleFrame(String id)
	{
		// ���� : ��¥ ����Ŭ�� �� �Է¸��.  ��ư�� �׼Ǹ����� �ް�, ������ ���� ��� ����ؾߵ�. 
				//		 �ѹ� Ŭ�� �� ������. �ܰ� �ϼ�. ������ �����Ǹ� �׿� ���� ��¸� ���ָ� �ɵ�.
				//       ��ü���� �������̽��� �׷��� ���� ������ ����.. ���� ����� ���°�.
				//       ������ �� ~~�ǳ� �����ϴ� �޼ҵ� ���� �޷� �׸��� üũ..? �ϴ� �̰� ����. 
				//       �ƹ�ư 1������ GUI �����, �� ���� �̺�Ʈ ������ ����. �������� ����� ��ɳִ¹�������.
				//----------------------------------�޷� �ʱ�ȭ-------------------------------------------------------
				calendar = new GregorianCalendar();	// ������ ��κ��� �������� ���Ǵ� ǥ������ �޷� �ý����� ���� 
				todayCalendar = Calendar.getInstance();	// ���� ��ǻ�� �ð��� �������� ��¥ �� �ð� ������ ������
		        year = todayCalendar.get(Calendar.YEAR);		// ���� �ð��� �⵵
		        month = todayCalendar.get(Calendar.MONTH)+1;// ���� �ð��� ��, 1���� ���� 0
		        date = todayCalendar.get(Calendar.DAY_OF_MONTH);	// ���� �ð��� ��
		        day = todayCalendar.get(Calendar.DAY_OF_WEEK);	// ���� �ð��� ����
		        memodayCalendar = Calendar.getInstance();		// ����ڰ� Ŭ���� ��¥�� ���� ����, �ʱ⿡�� ���� ��¥�� �Ѵ�.
		        memodayCalendar.clear();
		        getDay(year,month,date);
		        //----------------------------------���� �ʱ�ȭ-------------------------------------------------------
				//labelYear = new JLabel();
				//labelMonth = new JLabel();
				//labelDate = new JLabel();
				//labelDay = new JLabel();
				labelCalendar = new JLabel("",JLabel.CENTER);
				loginId=id;
				//--------------------------------Frame �� ContentPane ����------------------------------------------        
				setTitle("Schedule");	// ������ ����
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// â ���� ��ư Ŭ���� ������ ����
				
				Container contantPane = getContentPane();	// ������ ���� ���������� �˾Ƴ���.
				contantPane.setLayout(null);				// �г��� ��ġ�� ���Ƿ� �����ϱ� ���� ���̾ƿ��� null�� �־���.
				contantPane.setBackground(Color.WHITE);
				
				headPanelInit();
				calendarPanelInit();
				memoPanelInit();
				datePanelInit();
				schedulePanelInit();
				selectPanelInit();

				contantPane.add(headPanel);		// ��ܺ� �г��� �������ҿ� ����
				contantPane.add(calendarPanel);	// �޷� �г��� �������ҿ� ����
				contantPane.add(memoPanel);		// �޸� �г��� �������ҿ� ����
				contantPane.add(selectPanel);	// �޸� / ������� �����ϴ� ���� �г��� �������ҿ� ����
				contantPane.add(datePanel);		// ��¥ �г��� �������ҿ� ����
				contantPane.add(schedulePanel);	// ���� ���� �г��� �������ҿ� ����

				
				setSize(800,600);	// ������ ������
				setVisible(true);	// ������ ���̱�
	}
	
	public void headPanelInit()
	{
		JLabel yearLabel, monthLabel;
		
		headPanel = new JPanel();			// ���̾ ��ܺ��� �г� ����
		headPanel.setSize(400,50);					// �г� ������ ���� 100, ���� 100
		
		Font font1=new Font("���",Font.BOLD,30);		// ��Ʈ ����
		Font font2=new Font("���",Font.BOLD,25);
        
		headPanel.add(textYear = new JTextField(""+year,JTextField.CENTER));	// �⵵ ǥ���� �ؽ�Ʈ�ʵ� ����
        headPanel.add(yearLabel = new JLabel("��"));
        headPanel.add(textMonth = new JTextField(""+month,JTextField.CENTER));	// �� ǥ���� �ؽ�Ʈ�ʵ� ����
        headPanel.add(monthLabel = new JLabel("��"));
        
        yearLabel.setFont(font2);
        monthLabel.setFont(font2);
        
        textYear.setFont(font1);	// ��Ʈ ����
        textMonth.setFont(font1); // ��Ʈ ����
        textYear.setHorizontalAlignment(JTextField.RIGHT);
        textMonth.setHorizontalAlignment(JTextField.RIGHT);
        textYear.addActionListener(new MyActionListener());
        textMonth.addActionListener(new MyActionListener());
        textYear.setPreferredSize(new Dimension(100,50));	// �⵵ ǥ���� �ؽ�Ʈ�ʵ� ũ�� ����
        textMonth.setPreferredSize(new Dimension(100,50));	// �� ǥ���� �ؽ�Ʈ�ʵ� ũ�� ����
        headPanel.setBackground(Color.GRAY);
		headPanel.setLocation(30,80);				// ��ܺ� �г� ��ġ ����
	}
	
	public void calendarPanelInit()
	{
		calendarPanel = new JPanel();			// �޷��� ǥ��� �г� ����
		calendarPanel.setSize(400,400);				// �г� ������ ���� 400 ���� 400
		calendarPanel.setLayout(new GridLayout(7,7));	// �г� ���̾ƿ��� �׸��� ���̾ƿ����� 7x7�� ����
		drawCalendar();
		calendarPanel.setLocation(30,150);			// �޷� �г� ��ġ ����
	}
	
	public void datePanelInit()
	{
		datePanel = new JPanel();	// �������� �г� ����
		datePanel.setSize(300,30);	// ũ�� ����
		datePanel.setLayout(new BorderLayout());
		setDay();
		//datePanel.add(labelYear);
		//datePanel.add(labelMonth);
		//datePanel.add(labelDate);
		//datePanel.add(labelDay);
		datePanel.add(labelCalendar,BorderLayout.CENTER);
		datePanel.setBackground(Color.YELLOW);
		datePanel.setLocation(450,120);	// ��ġ ����
	}
	
	public void memoPanelInit()
	{
		memoPanel = new JPanel();	// �޸��г� ����
		memoPanel.setSize(300,400);	// ũ�� ����
		memoPanel.setLayout(new BorderLayout());
		JLabel memoLabel = new JLabel("MEMO",JLabel.CENTER);
		textMemo = new JTextArea();	// �ؽ�Ʈ�ʵ� ����
		textMemo.setEditable(false);
		textMemo.setPreferredSize(new Dimension(200,350));	// �ؽ�Ʈ�ʵ��� ũ�� ����
		scrollMemo = new JScrollPane(textMemo);
		memoPanel.add(memoLabel,BorderLayout.NORTH);
		memoPanel.add(scrollMemo,BorderLayout.CENTER);		// �ؽ�Ʈ�ʵ带 �޸��гο� ���̱�
		memoPanel.setBackground(Color.MAGENTA);
		memoPanel.setLocation(450,150);	// �޸��г� ��ġ ����
	}
	
	public void schedulePanelInit()
	{		
		schedulePanel = new JPanel();
		schedulePanel.setSize(300,400);
		schedulePanel.setLayout(new BorderLayout());
		JLabel scheduleLabel = new JLabel("SCHEDULE",JLabel.CENTER);
		textSchedule = new JTextArea();
		textSchedule.setEditable(false);
		textSchedule.setPreferredSize(new Dimension(200,350));
		scrollSchedule = new JScrollPane(textSchedule);
		schedulePanel.add(scheduleLabel,BorderLayout.NORTH);
		schedulePanel.add(scrollSchedule,BorderLayout.CENTER);
		schedulePanel.setBackground(Color.MAGENTA);
		//refreshData();
		schedulePanel.setLocation(450,150);
	}
	
	public void selectPanelInit()
	{
		Font ff=new Font("���",Font.PLAIN,15);
		JButton buttonContact,buttonGoToday;
		buttonContact = new JButton();
		buttonGoToday = new JButton();
		BevelBorder b = new BevelBorder(BevelBorder.RAISED,Color.green,Color.red,Color.blue,Color.DARK_GRAY);
		
		selectPanel = new JPanel();		// �г� ����
		selectPanel.setSize(300,40);	// �г� ũ��
		//selectPanel.setBackground(Color.GRAY);	// �г� ���
		selectPanel.setBackground(Color.ORANGE);
		ButtonGroup selectGroup = new ButtonGroup();	// ��ư �׷� ����
		radioSelect = new JRadioButton[2];	// ���� ��ư 2�� ����
		radioSelect[0] = new JRadioButton("�޸�");	
		radioSelect[0].setBackground(Color.ORANGE);
		radioSelect[0].addItemListener(new MyItemListener());	// ������ ������ �߰�
		radioSelect[1] = new JRadioButton("������");
		radioSelect[1].setBackground(Color.ORANGE);
		radioSelect[1].addItemListener(new MyItemListener());
		radioSelect[1].doClick();	// ��ư Ŭ�� �� ���·� ����
		selectPanel.add(radioSelect[1]);
		selectPanel.add(radioSelect[0]);	// �гο� ���� ��ư �߰�
		selectGroup.add(radioSelect[1]);
		selectGroup.add(radioSelect[0]);	// ��ư �׷쿡 ���� ��ư �߰�, �� �� �ϳ��� ���ð���.
		frameManager.createJButton(selectPanel, buttonContact, "�ּҷ�", 50, 50, 80, 10, ff);
		frameManager.createJButton(selectPanel, buttonGoToday, "���÷�", 50, 50, 160, 10, ff);
		buttonContact.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new contactFrame(loginId);
			}
		});
		buttonGoToday.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("���÷� ���ô�!");
				year = todayCalendar.get(Calendar.YEAR);
				month = todayCalendar.get(Calendar.MONTH)+1;
				date = todayCalendar.get(Calendar.DAY_OF_MONTH);
				//System.out.println(year+"  "+month+"  "+date);
				refreshData();
				setDay();
				drawCalendar();
				
				textYear.setText(year+"");
				textMonth.setText(month+"");
			}
		});
		selectPanel.setBorder(b);
		selectPanel.setLocation(450,80);	// �г� ��ġ ����
	}
	
	public void setCalendar()
	{
    	calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,(month-1));
        calendar.set(Calendar.DATE,1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        /*
         * get �� set �� ���� �ʵ�ġ��, ������ ��Ÿ���ϴ�.
         * �� �ʵ��� ����,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
         * ,THURSDAY,FRIDAY, �� SATURDAY �� �˴ϴ�.
         * get()�޼ҵ��� ���� ������ ���ڷ� ��ȯ
         */

        int j=0;
        int hopping=0;
        
        calendarButton[0].setForeground(new Color(255,0,0));//�Ͽ��� "��"
        calendarButton[6].setForeground(new Color(0,0,255));//����� "��"
        
        for(int i=calendar.getFirstDayOfWeek();i<dayOfWeek;i++)
        	j++;
        /*
         * �Ͽ��Ϻ��� �״��� ù���� ���ϱ��� ��ĭ���� �����ϱ� ����
         */
        hopping=j;
        
        for(int kk=0;kk<hopping;kk++)
        	calendarButton[kk+7].setText("");
        
        for(int i=calendar.getMinimum(Calendar.DAY_OF_MONTH); i<=calendar.getMaximum(Calendar.DAY_OF_MONTH); i++)
        {
        	calendar.set(Calendar.DATE,i);
            
        	if(calendar.get(Calendar.MONTH) !=month-1)
            	break;
            
            today=i;
            
            if(memoday==1)
            	calendarButton[i+6+hopping].setForeground(new Color(0,255,0));
            else
            {
            	calendarButton[i+6+hopping].setForeground(new Color(0,0,0));
            	
            	if((i+hopping-1)%7==0)
            		calendarButton[i+6+hopping].setForeground(new Color(255,0,0)); //�Ͽ���
            	
            	if((i+hopping)%7==0)
            		calendarButton[i+6+hopping].setForeground(new Color(0,0,255)); //�����
            }
            
            /*
             * ������ ���� �������� ����ؾ� �ϴ� ������ ���� ��ư�� ������ ���ϰ�
             * �ε����� 0���� �����̴� -1�� ���� ������ ������ ���ְ�
             * ��ư�� ������ �������ش�.
             */
            
            calendarButton[i+6+hopping].setText((i)+"");
               // ��/���� ���� ��¥�� �����ϸ�, �̹� �� �޷��̴ϱ� ���� ��¥ ��ư ��� �� ����.
            if((year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
            {
            	todayNumber=todayCalendar.get(Calendar.DAY_OF_MONTH)+6+hopping;
            	calendarButton[todayNumber].setBackground(Color.GRAY);
            }
            
            calendarButton[i+6+hopping].addActionListener(new MyButtonActionListener());
        } //for
    } //end setCalendar()
	
	public void gridInit()
	{
	//calendarPanel�� ��ư ���̱�
		for(int i = 0 ; i < days.length;i++)
		{
			calendarPanel.add(calendarButton[i] = new JButton(days[i]));
			calendarButton[i].setBackground(Color.CYAN);
		}
		
		for(int i = days.length; i < 49; i++)
		{
			calendarPanel.add(calendarButton[i] = new JButton(""));  
			calendarButton[i].setBackground(Color.WHITE);
        	//calBtn[i].addMouseListener(new MyMouseListener());
        	//calBtn[i].addActionListener(this);
		}             
	}
	
	public void hideInit()
	{
		for(int i = 0 ; i < calendarButton.length; i++)
		{
			if((calendarButton[i].getText()).equals(""))
				calendarButton[i].setEnabled(false);
               //�ϼ��� ������ ���� ������ ��ư�� ��Ȱ��ȭ ��Ų��.
		}//end for
	}//end hideInit()
	
	public int getDay(int year, int month, int date)	// �ش� ��¥�� ���� ���� ��ȯ���ִ� �޼ҵ�
	{
		int a;
		memodayCalendar.set(Calendar.YEAR, year);	// �޷� ������ �����Ѵ�.
		memodayCalendar.set(Calendar.MONTH, month-1);
		memodayCalendar.set(Calendar.DAY_OF_MONTH, date);
		a = memodayCalendar.get(Calendar.DAY_OF_WEEK);	// �ش� ��¥�� ��� �������� �޾Ƽ�
		return a;		// ���� int�� ����
	}
	
	public void setDay()	// ��¥ ǥ�� Label�� ������ �ٲپ��ִ� �޼ҵ�
	{
		//labelYear.setText(year+"��");
		//labelMonth.setText(month+"��");
		//labelDate.setText(date+"��");
		//labelDay.setText(days[day-1]+"����");
		labelCalendar.setText(year+"�� "+month+"�� "+date+"�� "+days[day-1]+"����");
	}
	
	public void drawCalendar()
	{
		calendarPanel.removeAll();	// �޷��� ��� �����
        gridInit();			// �޷� �г� ��ư �ʱ�ȭ		// ��ó : http://donkey612.blog.me/70067118479
        setCalendar();			// �޷� ���				// ��ó : http://donkey612.blog.me/70067118479
        hideInit();			// ��ư ��Ȱ��ȭ			// ��ó : http://donkey612.blog.me/70067118479
	}
	
	public void refreshData()
	{
		int i=0;
		if(radioSelect[1].isSelected())
		{
			textSchedule.setText("");
			while(i<Diary.v.size())
			{
				if(Diary.v.get(i).getDate().equals(memday)&&Diary.v.get(i).getId().equals(loginId))
					textSchedule.append(Diary.v.get(i).getNote()+"\n");
				
				i++;
			}
		}
		else if(radioSelect[0].isSelected())
		{
			textMemo.setText("");

			while(i<Diary.v2.size())
			{
				if(Diary.v2.get(i).getDate().equals(memday)&&Diary.v2.get(i).getId().equals(loginId))
					textMemo.append(Diary.v2.get(i).getNote()+"\n");
				
				i++;
			}
		}
	}

	class MyActionListener implements ActionListener	// �ؽ�Ʈ �ʵ� �׼� ������
	{
		public void actionPerformed(ActionEvent e)
		{
			JTextField textField = (JTextField)e.getSource();	// �ؽ�Ʈ �ʵ��� �̺�Ʈ �߻����� ã�Ƽ�
			
			if((textField == textYear)||(textField == textMonth))	// �⵵ �Ǵ� ���� �Է��� �Ǿ��ٸ�
			{
				int y = new Integer(textYear.getText());	// �� ���� �о�
				int m = new Integer(textMonth.getText());	// �� ���� �о

				if((y>=1990 && y<=2030)&&(m>=1 && m<=12))		// �⵵�� ���� ���ǿ� �˸�����
				{
					year = y;	// �⵵ ����
					month = m;	// �� �� ����
					drawCalendar();	// ���� �׷���
				}
			}
		}
	}
	
	class MyButtonActionListener implements ActionListener	// �޷� ��ư �׼� ������
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton actionButton = (JButton)e.getSource();		// �̺�Ʈ �߻� ��ư�� ã��
			
			for(int i = 0; i < 49; i++)
			{
				if(actionButton == calendarButton[i] && !calendarButton[i].getText().equals(""))	// ��ư Ȯ��
				{
					int d = new Integer(calendarButton[i].getText());	// ��ư ��(��������) ����
					date = d;	// ��¥ ����
					day = getDay(year,month,date);	// ��¥�� ���� ������ ����
					setDay();	// ��¥ ǥ�� Label ������ �ٲ���
					refreshData();
					
					if(actionButton.getBackground()==Color.YELLOW)	// Ŭ���� ��ư�� ����� ������̸�, �ѹ� Ŭ�����ִٴ� �ǹ�
					{
						popupframe = new popupFrame(year, month, date, day, loginId);	// �˾�����ְ�
						actionButton.setBackground(Color.WHITE);					// �ٽ� ��� ������.
						
						if((year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
							calendarButton[todayNumber].setBackground(Color.CYAN);
					}
					else	// Ŭ���� ��ư�� ó�� Ŭ���ȰŶ��
						actionButton.setBackground(Color.YELLOW);	// ���� ����
					
					
					for(int j = 0; j < 49; j++)	// Ŭ���� ��ư ���� ��ư�� �ٽ� ������ ������.
					{
						if((j!=i)&&(calendarButton[j].getBackground()==Color.YELLOW))
							calendarButton[j].setBackground(Color.WHITE);
						
						if((j==todayNumber)&&(j!=i)&&(year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
							calendarButton[j].setBackground(Color.CYAN);
					}
				}
			}
		}
	}

	class MyItemListener implements ItemListener	// ������ ������
	{
		public void itemStateChanged(ItemEvent e)
		{	// �������� ���°� ����Ǿ����� ����
			if(radioSelect[0].isSelected())	// ���� ��ư 0���� ���õǾ��ٸ�
			{
				schedulePanel.setVisible(false);						// �޸� �г��� �����ϱ� ���� false
				memoPanel.setVisible(true);								// �޸� �г� ���̱�
				refreshData();
			}
			else if(radioSelect[1].isSelected())
			{
				memoPanel.setVisible(false);						// ���� ��� �г��� �����ϱ� ���� false
				schedulePanel.setVisible(true);						// ���� �г� ���̱�
				refreshData();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		// scheduleFrame DF = new scheduleFrame();
	}
}
