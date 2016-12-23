import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import java.util.*;

public class ScheduleFrame extends JFrame
{
	String [] days = {"일", "월", "화", "수", "목", "금", "토"};
	Calendar todayCalendar, calendar, memodayCalendar;
	int year, month, date, today, day, memoday=0;
	JButton[] calendarButton = new JButton[49];
	JPanel calendarPanel, headPanel, datePanel, selectPanel, schedulePanel;
    JTextField textMonth, textYear;
    JTextArea textSchedule;
    JScrollPane scrollSchedule;
	JLabel labelYear, labelMonth, labelDate, labelDay, labelCalendar;
<<<<<<< HEAD:src/ScheduleFrame.java
	PopupFrame popupframe;
=======
>>>>>>> origin/master:src/ScheduleFrame.java
	String loginId;
	int todayNumber;
	
	public void scheduleFrame()
	{
		calendar = new GregorianCalendar();
		todayCalendar = Calendar.getInstance();
		year = todayCalendar.get(Calendar.YEAR);
		month = todayCalendar.get(Calendar.MONTH)+1;
		date = todayCalendar.get(Calendar.DAY_OF_MONTH);
		day = todayCalendar.get(Calendar.DAY_OF_WEEK);
		memodayCalendar = Calendar.getInstance();
		getDay(year,month,date);
	
		labelCalendar = new JLabel("",JLabel.CENTER);
<<<<<<< HEAD:src/ScheduleFrame.java
		// loginId=User.username;
=======
		loginId = User.getName();
>>>>>>> origin/master:src/ScheduleFrame.java
		
		setTitle("Schedule");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
<<<<<<< HEAD:src/ScheduleFrame.java
		Container contantPane = getContentPane();
		contantPane.setLayout(null);
=======
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
>>>>>>> origin/master:src/ScheduleFrame.java
				
		HeadPanelInit();
		CalendarPanelInit();
		SelectPanelInit();
		DatePanelInit();
		SchedulePanelInit();
				
		contentPane.add(headPanel);
		contentPane.add(calendarPanel);
		contentPane.add(selectPanel);
		contentPane.add(datePanel);
		contentPane.add(schedulePanel);

		setSize(800,600);
		setVisible(true);
	}
	
	public void HeadPanelInit()
	{
		JLabel yearLabel, monthLabel;
		
		headPanel = new JPanel();
		headPanel.setSize(400,50);
		
		Font font1=new Font("고딕",Font.BOLD,30);
		Font font2=new Font("고딕",Font.BOLD,25);
        
		headPanel.add(textYear = new JTextField(""+year,JTextField.CENTER));	// 년도 표시할 텍스트필드 생성
        headPanel.add(yearLabel = new JLabel("년"));
        headPanel.add(textMonth = new JTextField(""+month,JTextField.CENTER));	// 월 표시할 텍스트필드 생성
        headPanel.add(monthLabel = new JLabel("월"));
        
        yearLabel.setFont(font2);
        monthLabel.setFont(font2);
        
        textYear.setFont(font1);
        textMonth.setFont(font1);
        textYear.setHorizontalAlignment(JTextField.RIGHT);
        textMonth.setHorizontalAlignment(JTextField.RIGHT);
        textYear.addActionListener(new MyActionListener());
        textMonth.addActionListener(new MyActionListener());
        textYear.setPreferredSize(new Dimension(100,50));
        textMonth.setPreferredSize(new Dimension(100,50));
       
<<<<<<< HEAD:src/ScheduleFrame.java
		headPanel.setLocation(30,80);
=======
		headPanel.setLocation(30,30);
>>>>>>> origin/master:src/ScheduleFrame.java
	}
	
	public void CalendarPanelInit()
	{
		calendarPanel = new JPanel();
		calendarPanel.setSize(400,400);
		calendarPanel.setLayout(new GridLayout(7,7));
		DrawCalendar();
		calendarPanel.setLocation(30,100);
	}
	
	public void SelectPanelInit()
	{
		Font ff=new Font("고딕",Font.PLAIN,15);
		JButton buttonAdd, buttonGoToday;
		
		buttonAdd = new JButton();
		buttonGoToday = new JButton();
		
		LineBorder b = new LineBorder(Color.DARK_GRAY,1);
		
		selectPanel = new JPanel();
		selectPanel.setSize(300,40);
		selectPanel.setBorder(b);
		selectPanel.setLocation(450,30);
				
		FrameManager.CreateJButton(selectPanel, buttonAdd, "Add Scehdule", 40, 40, 100, 8, ff);
		FrameManager.CreateJButton(selectPanel, buttonGoToday, "Go Today", 40, 40, 140, 8, ff);

		buttonAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				year = memodayCalendar.get(Calendar.YEAR);
				month = memodayCalendar.get(Calendar.MONTH)+1;
				date = memodayCalendar.get(Calendar.DAY_OF_MONTH);
				
				PopupFrame pf = new PopupFrame(year, month, date, day, loginId);
			}
		});
		
		buttonGoToday.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SetDay();
				DrawCalendar();
				
				textYear.setText(year+"");
				textMonth.setText(month+"");
			}
		});
	}
	
	public void SelectPanelInit()
	{
		Font ff=new Font("고딕",Font.PLAIN,15);
		JButton buttonGoToday;
		buttonGoToday = new JButton();
		LineBorder b = new LineBorder(Color.DARK_GRAY,1);
		
		selectPanel = new JPanel();
		selectPanel.setSize(300,40);
		
		ButtonGroup selectGroup = new ButtonGroup();
		
		radioSelect = new JRadioButton[2];
		radioSelect[0] = new JRadioButton("메모");	
		radioSelect[0].addItemListener(new MyItemListener());
		radioSelect[1] = new JRadioButton("스케쥴");
		radioSelect[1].addItemListener(new MyItemListener());
		radioSelect[1].doClick();
		
		selectPanel.add(radioSelect[1]);
		selectPanel.add(radioSelect[0]);
		
		selectGroup.add(radioSelect[1]);
		selectGroup.add(radioSelect[0]);
		
		FrameManager.CreateJButton(selectPanel, buttonGoToday, "Go Today!", 40, 40, 100, 8, ff);
				
		buttonGoToday.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("");
				year = todayCalendar.get(Calendar.YEAR);
				month = todayCalendar.get(Calendar.MONTH)+1;
				date = todayCalendar.get(Calendar.DAY_OF_MONTH);
				//System.out.println(year+"  "+month+"  "+date);
				RefreshData();
				SetDay();
				DrawCalendar();
				
				textYear.setText(year+"");
				textMonth.setText(month+"");
			}
		});
		
		selectPanel.setBorder(b);
		selectPanel.setLocation(450,80);
	}
	
	public void DatePanelInit()
	{
		datePanel = new JPanel();
		datePanel.setSize(300,30);
		datePanel.setLayout(new BorderLayout());
		
		LineBorder b = new LineBorder(Color.DARK_GRAY,1);
		SetDay();

		datePanel.add(labelCalendar,BorderLayout.CENTER);
		datePanel.setBackground(Color.PINK);
<<<<<<< HEAD:src/ScheduleFrame.java
		datePanel.setLocation(450,120);	// 위치 설정
		datePanel.setBorder(b);
	}
	
	public void MemoPanelInit()
	{
		memoPanel = new JPanel();
		memoPanel.setSize(300,400);
		memoPanel.setLayout(new BorderLayout());
		LineBorder b = new LineBorder(Color.DARK_GRAY,1);
		
		JLabel memoLabel = new JLabel("MEMO",JLabel.CENTER);
		
		textMemo = new JTextArea();
		textMemo.setEditable(false);
		textMemo.setPreferredSize(new Dimension(200,350));
		
		scrollMemo = new JScrollPane(textMemo);
		
		memoPanel.add(memoLabel,BorderLayout.NORTH);
		memoPanel.add(scrollMemo,BorderLayout.CENTER);
		memoPanel.setBackground(Color.YELLOW);
		memoPanel.setLocation(450,150);
		memoPanel.setBorder(b);
=======
		datePanel.setLocation(450,70);
		datePanel.setBorder(b);
>>>>>>> origin/master:src/ScheduleFrame.java
	}
	
	public void SchedulePanelInit()
	{		
		schedulePanel = new JPanel();
		schedulePanel.setSize(300,400);
		schedulePanel.setLayout(new BorderLayout());
		LineBorder b = new LineBorder(Color.DARK_GRAY,1);
		
		JLabel scheduleLabel = new JLabel("SCHEDULE",JLabel.CENTER);
		
		textSchedule = new JTextArea();
		textSchedule.setEditable(false);
		textSchedule.setPreferredSize(new Dimension(200,350));
		
		scrollSchedule = new JScrollPane(textSchedule);
		
		schedulePanel.add(scheduleLabel,BorderLayout.NORTH);
		schedulePanel.add(scrollSchedule,BorderLayout.CENTER);
		schedulePanel.setBackground(Color.MAGENTA);
<<<<<<< HEAD:src/ScheduleFrame.java
		schedulePanel.setLocation(450,150);
=======
		schedulePanel.setLocation(450,100);
>>>>>>> origin/master:src/ScheduleFrame.java
		schedulePanel.setBorder(b);
	}
	
	public void SetCalendar()
	{
    	calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,(month-1));
        calendar.set(Calendar.DATE,1);
        
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int j=0;
        int hopping=0;
        
        calendarButton[0].setForeground(new Color(255,0,0));
        calendarButton[6].setForeground(new Color(0,0,255));
        
        for(int i=calendar.getFirstDayOfWeek();i<dayOfWeek;i++)
        	j++;
       
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
            		calendarButton[i+6+hopping].setForeground(new Color(255,0,0)); //일요일
            	
            	if((i+hopping)%7==0)
            		calendarButton[i+6+hopping].setForeground(new Color(0,0,255)); //토요일
            }
            
            calendarButton[i+6+hopping].setText((i)+"");

            if((year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
            {
            	todayNumber=todayCalendar.get(Calendar.DAY_OF_MONTH)+6+hopping;
            	calendarButton[todayNumber].setBackground(Color.GRAY);
            }
            
            calendarButton[i+6+hopping].addActionListener(new MyButtonActionListener());
        }
    }
	
	public void GridInit()
	{
		for(int i = 0 ; i < days.length;i++)
		{
			calendarPanel.add(calendarButton[i] = new JButton(days[i]));
			calendarButton[i].setBackground(Color.CYAN);
		}
		
		for(int i = days.length; i < 49; i++)
		{
			calendarPanel.add(calendarButton[i] = new JButton(""));  
			calendarButton[i].setBackground(Color.WHITE);
        	//calButton[i].addMouseListener(new MyMouseListener());
        	//calButton[i].addActionListener(this);
		}             
	}
	
	public void HideInit()
	{
		for(int i = 0 ; i < calendarButton.length; i++)
		{
			if((calendarButton[i].getText()).equals(""))
				calendarButton[i].setEnabled(false);
		}
	}
	
	public int getDay(int year, int month, int date)
	{
		int a;
		
		memodayCalendar.set(Calendar.YEAR, year);
		memodayCalendar.set(Calendar.MONTH, month-1);
		memodayCalendar.set(Calendar.DAY_OF_MONTH, date);
		
		a = memodayCalendar.get(Calendar.DAY_OF_WEEK);
		
		return a;
	}
	
	public void SetDay()
	{
		labelCalendar.setText(year+"년 "+month+"월 "+date+"일 "+days[day-1]+"요일");
	}
	
	public void DrawCalendar()
	{
		calendarPanel.removeAll();
        GridInit();
        SetCalendar();
        HideInit();
	}

	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JTextField textField = (JTextField)e.getSource();
			
			if((textField == textYear)||(textField == textMonth))
			{
				int y = new Integer(textYear.getText());
				int m = new Integer(textMonth.getText());

				if((y>=1990 && y<=2030)&&(m>=1 && m<=12))
				{
					year = y;
					month = m;
					DrawCalendar();
				}
			}
		}
	}
	
	class MyButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton actionButton = (JButton)e.getSource();
			
			for(int i = 0; i < 49; i++)
			{
				if(actionButton == calendarButton[i] && !calendarButton[i].getText().equals(""))
				{
					int d = new Integer(calendarButton[i].getText());
					
					date = d;
					day = getDay(year,month,date);
					
					SetDay();
					
					if(actionButton.getBackground()==Color.YELLOW)
					{
<<<<<<< HEAD:src/ScheduleFrame.java
						popupframe = new PopupFrame(year, month, date, day);
=======
>>>>>>> origin/master:src/ScheduleFrame.java
						actionButton.setBackground(Color.WHITE);
						
						if((year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
							calendarButton[todayNumber].setBackground(Color.CYAN);
					}
					else
						actionButton.setBackground(Color.YELLOW);
					
					
					for(int j = 0; j < 49; j++)
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
<<<<<<< HEAD:src/ScheduleFrame.java

	class MyItemListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(radioSelect[0].isSelected())
			{
				schedulePanel.setVisible(false);
				memoPanel.setVisible(true);
				RefreshData();
			}
			else if(radioSelect[1].isSelected())
			{
				memoPanel.setVisible(false);
				schedulePanel.setVisible(true);
				RefreshData();
			}
		}
	}
=======
>>>>>>> origin/master:src/ScheduleFrame.java
}