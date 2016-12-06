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
		// 예정 : 날짜 더블클릭 시 입력모드.  버튼에 액션리스너 달고, 데이터 관리 방법 모색해야됨. 
				//		 한번 클릭 시 보기모드. 외관 완성. 데이터 구현되면 그에 따른 출력만 해주면 될듯.
				//       전체적인 인터페이스가 그렇게 보기 좋지는 않음.. 좋은 방법이 없는가.
				//       공휴일 및 ~~의날 관리하는 메소드 만들어서 달력 그릴때 체크..? 일단 이건 보류. 
				//       아무튼 1순위로 GUI 만들고, 그 다음 이벤트 리스너 연결. 최종으로 입출력 기능넣는방향으로.
				//----------------------------------달력 초기화-------------------------------------------------------
				calendar = new GregorianCalendar();	// 세계의 대부분의 지역에서 사용되는 표준적인 달력 시스템을 제공 
				todayCalendar = Calendar.getInstance();	// 현재 컴퓨터 시간을 바탕으로 날짜 및 시간 정보를 가져옴
		        year = todayCalendar.get(Calendar.YEAR);		// 현재 시각의 년도
		        month = todayCalendar.get(Calendar.MONTH)+1;// 현재 시각의 월, 1월의 값이 0
		        date = todayCalendar.get(Calendar.DAY_OF_MONTH);	// 현재 시각의 일
		        day = todayCalendar.get(Calendar.DAY_OF_WEEK);	// 현재 시각의 요일
		        memodayCalendar = Calendar.getInstance();		// 사용자가 클릭한 날짜에 대한 정보, 초기에는 현재 날짜로 한다.
		        memodayCalendar.clear();
		        getDay(year,month,date);
		        //----------------------------------변수 초기화-------------------------------------------------------
				//labelYear = new JLabel();
				//labelMonth = new JLabel();
				//labelDate = new JLabel();
				//labelDay = new JLabel();
				labelCalendar = new JLabel("",JLabel.CENTER);
				loginId=id;
				//--------------------------------Frame 및 ContentPane 설정------------------------------------------        
				setTitle("Schedule");	// 프레임 제목
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창 종료 버튼 클릭시 쓰레드 종료
				
				Container contantPane = getContentPane();	// 프레임 안의 콘텐츠팬을 알아낸다.
				contantPane.setLayout(null);				// 패널의 위치를 임의로 지정하기 위해 레이아웃을 null로 주었다.
				contantPane.setBackground(Color.WHITE);
				
				headPanelInit();
				calendarPanelInit();
				memoPanelInit();
				datePanelInit();
				schedulePanelInit();
				selectPanelInit();

				contantPane.add(headPanel);		// 상단부 패널을 콘텐츠팬에 붙임
				contantPane.add(calendarPanel);	// 달력 패널을 콘텐츠팬에 붙임
				contantPane.add(memoPanel);		// 메모 패널을 콘텐츠팬에 붙임
				contantPane.add(selectPanel);	// 메모 / 일정등록 선택하는 라디오 패널을 콘텐츠팬에 붙임
				contantPane.add(datePanel);		// 날짜 패널을 콘텐츠팬에 붙임
				contantPane.add(schedulePanel);	// 일정 관리 패널을 콘텐츠팬에 붙임

				
				setSize(800,600);	// 프레임 사이즈
				setVisible(true);	// 프레임 보이기
	}
	
	public void headPanelInit()
	{
		JLabel yearLabel, monthLabel;
		
		headPanel = new JPanel();			// 다이어리 상단부의 패널 생성
		headPanel.setSize(400,50);					// 패널 사이즈 가로 100, 세로 100
		
		Font font1=new Font("고딕",Font.BOLD,30);		// 폰트 설정
		Font font2=new Font("고딕",Font.BOLD,25);
        
		headPanel.add(textYear = new JTextField(""+year,JTextField.CENTER));	// 년도 표시할 텍스트필드 생성
        headPanel.add(yearLabel = new JLabel("년"));
        headPanel.add(textMonth = new JTextField(""+month,JTextField.CENTER));	// 월 표시할 텍스트필드 생성
        headPanel.add(monthLabel = new JLabel("월"));
        
        yearLabel.setFont(font2);
        monthLabel.setFont(font2);
        
        textYear.setFont(font1);	// 폰트 적용
        textMonth.setFont(font1); // 폰트 적용
        textYear.setHorizontalAlignment(JTextField.RIGHT);
        textMonth.setHorizontalAlignment(JTextField.RIGHT);
        textYear.addActionListener(new MyActionListener());
        textMonth.addActionListener(new MyActionListener());
        textYear.setPreferredSize(new Dimension(100,50));	// 년도 표시할 텍스트필드 크기 설정
        textMonth.setPreferredSize(new Dimension(100,50));	// 월 표시할 텍스트필드 크기 설정
        headPanel.setBackground(Color.GRAY);
		headPanel.setLocation(30,80);				// 상단부 패널 위치 설정
	}
	
	public void calendarPanelInit()
	{
		calendarPanel = new JPanel();			// 달력이 표기될 패널 생성
		calendarPanel.setSize(400,400);				// 패널 사이즈 가로 400 세로 400
		calendarPanel.setLayout(new GridLayout(7,7));	// 패널 레이아웃을 그리드 레이아웃으로 7x7로 나눔
		drawCalendar();
		calendarPanel.setLocation(30,150);			// 달력 패널 위치 설정
	}
	
	public void datePanelInit()
	{
		datePanel = new JPanel();	// 일정관리 패널 생성
		datePanel.setSize(300,30);	// 크기 설정
		datePanel.setLayout(new BorderLayout());
		setDay();
		//datePanel.add(labelYear);
		//datePanel.add(labelMonth);
		//datePanel.add(labelDate);
		//datePanel.add(labelDay);
		datePanel.add(labelCalendar,BorderLayout.CENTER);
		datePanel.setBackground(Color.YELLOW);
		datePanel.setLocation(450,120);	// 위치 설정
	}
	
	public void memoPanelInit()
	{
		memoPanel = new JPanel();	// 메모패널 생성
		memoPanel.setSize(300,400);	// 크기 설정
		memoPanel.setLayout(new BorderLayout());
		JLabel memoLabel = new JLabel("MEMO",JLabel.CENTER);
		textMemo = new JTextArea();	// 텍스트필드 생성
		textMemo.setEditable(false);
		textMemo.setPreferredSize(new Dimension(200,350));	// 텍스트필드의 크기 설정
		scrollMemo = new JScrollPane(textMemo);
		memoPanel.add(memoLabel,BorderLayout.NORTH);
		memoPanel.add(scrollMemo,BorderLayout.CENTER);		// 텍스트필드를 메모패널에 붙이기
		memoPanel.setBackground(Color.MAGENTA);
		memoPanel.setLocation(450,150);	// 메모패널 위치 설정
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
		Font ff=new Font("고딕",Font.PLAIN,15);
		JButton buttonContact,buttonGoToday;
		buttonContact = new JButton();
		buttonGoToday = new JButton();
		BevelBorder b = new BevelBorder(BevelBorder.RAISED,Color.green,Color.red,Color.blue,Color.DARK_GRAY);
		
		selectPanel = new JPanel();		// 패널 생성
		selectPanel.setSize(300,40);	// 패널 크기
		//selectPanel.setBackground(Color.GRAY);	// 패널 배경
		selectPanel.setBackground(Color.ORANGE);
		ButtonGroup selectGroup = new ButtonGroup();	// 버튼 그룹 생성
		radioSelect = new JRadioButton[2];	// 라디오 버튼 2개 생성
		radioSelect[0] = new JRadioButton("메모");	
		radioSelect[0].setBackground(Color.ORANGE);
		radioSelect[0].addItemListener(new MyItemListener());	// 아이템 리스너 추가
		radioSelect[1] = new JRadioButton("스케쥴");
		radioSelect[1].setBackground(Color.ORANGE);
		radioSelect[1].addItemListener(new MyItemListener());
		radioSelect[1].doClick();	// 버튼 클릭 된 상태로 변경
		selectPanel.add(radioSelect[1]);
		selectPanel.add(radioSelect[0]);	// 패널에 라디오 버튼 추가
		selectGroup.add(radioSelect[1]);
		selectGroup.add(radioSelect[0]);	// 버튼 그룹에 라디오 버튼 추가, 둘 중 하나만 선택가능.
		frameManager.createJButton(selectPanel, buttonContact, "주소록", 50, 50, 80, 10, ff);
		frameManager.createJButton(selectPanel, buttonGoToday, "오늘로", 50, 50, 160, 10, ff);
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
				//System.out.println("오늘로 갑시다!");
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
		selectPanel.setLocation(450,80);	// 패널 위치 설정
	}
	
	public void setCalendar()
	{
    	calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,(month-1));
        calendar.set(Calendar.DATE,1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        /*
         * get 및 set 를 위한 필드치로, 요일을 나타냅니다.
         * 이 필드의 값은,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
         * ,THURSDAY,FRIDAY, 및 SATURDAY 가 됩니다.
         * get()메소드의 의해 요일이 숫자로 반환
         */

        int j=0;
        int hopping=0;
        
        calendarButton[0].setForeground(new Color(255,0,0));//일요일 "일"
        calendarButton[6].setForeground(new Color(0,0,255));//토요일 "토"
        
        for(int i=calendar.getFirstDayOfWeek();i<dayOfWeek;i++)
        	j++;
        /*
         * 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해
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
            		calendarButton[i+6+hopping].setForeground(new Color(255,0,0)); //일요일
            	
            	if((i+hopping)%7==0)
            		calendarButton[i+6+hopping].setForeground(new Color(0,0,255)); //토요일
            }
            
            /*
             * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고
             * 인덱스가 0부터 시작이니 -1을 해준 값으로 연산을 해주고
             * 버튼의 색깔을 변경해준다.
             */
            
            calendarButton[i+6+hopping].setText((i)+"");
               // 년/월이 오늘 날짜와 동일하면, 이번 달 달력이니까 오늘 날짜 버튼 배경 색 변경.
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
	//calendarPanel에 버튼 붙이기
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
               //일수가 찍히지 않은 나머지 버튼을 비활성화 시킨다.
		}//end for
	}//end hideInit()
	
	public int getDay(int year, int month, int date)	// 해당 날짜의 요일 값을 반환해주는 메소드
	{
		int a;
		memodayCalendar.set(Calendar.YEAR, year);	// 달력 정보를 갱신한다.
		memodayCalendar.set(Calendar.MONTH, month-1);
		memodayCalendar.set(Calendar.DAY_OF_MONTH, date);
		a = memodayCalendar.get(Calendar.DAY_OF_WEEK);	// 해당 날짜가 어느 요일인지 받아서
		return a;		// 요일 int값 리턴
	}
	
	public void setDay()	// 날짜 표기 Label의 내용을 바꾸어주는 메소드
	{
		//labelYear.setText(year+"년");
		//labelMonth.setText(month+"월");
		//labelDate.setText(date+"일");
		//labelDay.setText(days[day-1]+"요일");
		labelCalendar.setText(year+"년 "+month+"월 "+date+"일 "+days[day-1]+"요일");
	}
	
	public void drawCalendar()
	{
		calendarPanel.removeAll();	// 달력을 모두 지우고
        gridInit();			// 달력 패널 버튼 초기화		// 출처 : http://donkey612.blog.me/70067118479
        setCalendar();			// 달력 계산				// 출처 : http://donkey612.blog.me/70067118479
        hideInit();			// 버튼 비활성화			// 출처 : http://donkey612.blog.me/70067118479
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

	class MyActionListener implements ActionListener	// 텍스트 필드 액션 리스너
	{
		public void actionPerformed(ActionEvent e)
		{
			JTextField textField = (JTextField)e.getSource();	// 텍스트 필드의 이벤트 발생지를 찾아서
			
			if((textField == textYear)||(textField == textMonth))	// 년도 또는 월이 입력이 되었다면
			{
				int y = new Integer(textYear.getText());	// 그 값을 읽어
				int m = new Integer(textMonth.getText());	// 그 값을 읽어서

				if((y>=1990 && y<=2030)&&(m>=1 && m<=12))		// 년도와 월의 조건에 알맞으면
				{
					year = y;	// 년도 변경
					month = m;	// 월 값 변경
					drawCalendar();	// 새로 그려줌
				}
			}
		}
	}
	
	class MyButtonActionListener implements ActionListener	// 달력 버튼 액션 리스너
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton actionButton = (JButton)e.getSource();		// 이벤트 발생 버튼을 찾음
			
			for(int i = 0; i < 49; i++)
			{
				if(actionButton == calendarButton[i] && !calendarButton[i].getText().equals(""))	// 버튼 확인
				{
					int d = new Integer(calendarButton[i].getText());	// 버튼 값(몇일인지) 받음
					date = d;	// 날짜 변경
					day = getDay(year,month,date);	// 날짜에 따른 요일을 받음
					setDay();	// 날짜 표기 Label 내용을 바꿔줌
					refreshData();
					
					if(actionButton.getBackground()==Color.YELLOW)	// 클릭한 버튼의 배경이 노란색이면, 한번 클릭되있다는 의미
					{
						popupframe = new popupFrame(year, month, date, day, loginId);	// 팝업띄워주고
						actionButton.setBackground(Color.WHITE);					// 다시 배경 없에줌.
						
						if((year==todayCalendar.get(Calendar.YEAR))&&(month==(todayCalendar.get(Calendar.MONTH)+1)))
							calendarButton[todayNumber].setBackground(Color.CYAN);
					}
					else	// 클릭한 버튼이 처음 클릭된거라면
						actionButton.setBackground(Color.YELLOW);	// 배경색 변경
					
					
					for(int j = 0; j < 49; j++)	// 클릭한 버튼 외의 버튼은 다시 배경색을 없에줌.
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

	class MyItemListener implements ItemListener	// 아이템 리스너
	{
		public void itemStateChanged(ItemEvent e)
		{	// 아이템의 상태가 변경되었을때 반응
			if(radioSelect[0].isSelected())	// 라디오 버튼 0번이 선택되었다면
			{
				schedulePanel.setVisible(false);						// 메모 패널을 수정하기 위해 false
				memoPanel.setVisible(true);								// 메모 패널 보이기
				refreshData();
			}
			else if(radioSelect[1].isSelected())
			{
				memoPanel.setVisible(false);						// 일정 등록 패널을 관리하기 위해 false
				schedulePanel.setVisible(true);						// 일정 패널 보이기
				refreshData();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		// scheduleFrame DF = new scheduleFrame();
	}
}
