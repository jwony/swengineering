import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import java.util.Vector;

public class PopupFrame extends JFrame
{
	private Vector<Schedule> scheduleData;
	String [] days = {"일","월","화","수","목","금","토"};
	JPanel schedulePanel, buttonPanel;
	JTextField timeText, titleText;
	JTextArea memoText;
	JScrollPane scrollMemo;
	Calendar calendar;
	// String loginId;
	
	// private String time, title, memo;
	// private static int scheduleCount=0;
	
	PopupFrame(){};
	
	PopupFrame(int y, int m, int d, int d2)
	{
		// loginId=id;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setDate(y,m,d);
		setTitle(y+"년 "+m+"월 "+d+"일 "+days[d2-1]+"요일");
		
		ScheduleInit();
		ButtonInit();
		
		add(schedulePanel);
		add(buttonPanel);
		
		setSize(500,350);
		setVisible(true);
	}

	void ScheduleInit()
	{
		schedulePanel = new JPanel();
		schedulePanel.setLayout(new BorderLayout());
		
		JLabel labelSchedule = new JLabel("스케쥴",JLabel.CENTER);
		schedulePanel.add(labelSchedule,BorderLayout.NORTH);
		
		JLabel timeLabel = new JLabel("시간");
		JLabel titleLabel = new JLabel("제목");
		JLabel memoLabel = new JLabel("메모");
		
		timeText = new JTextField(10);
		titleText = new JTextField(20);
		memoText = new JTextArea();
		memoText.setPreferredSize(new Dimension(200,350));
		
		JPanel timePanel = new JPanel();
		timePanel.add(timeLabel, BorderLayout.WEST);
		timePanel.add(timeText, BorderLayout.EAST);
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.add(titleText, BorderLayout.EAST);
		
		JPanel memoPanel = new JPanel();
		memoPanel.add(memoLabel, BorderLayout.NORTH);
		memoPanel.add(memoText, BorderLayout.SOUTH);
		
		
		
		scrollMemo = new JScrollPane(memoText);
		
		schedulePanel.add(timePanel,BorderLayout.CENTER);
		schedulePanel.add(titlePanel,BorderLayout.CENTER);
		schedulePanel.add(memoPanel,BorderLayout.SOUTH);
		
		schedulePanel.setLocation(40,40);
		schedulePanel.setSize(400,200);
		schedulePanel.setBackground(Color.MAGENTA);
		schedulePanel.setVisible(true);
	}
	
	void ButtonInit()
	{
		buttonPanel = new JPanel();
		
		JButton okButton = new JButton("SAVE");
		okButton.addActionListener(new PopupActionListener());
		buttonPanel.add(okButton);
		
		JButton noButton = new JButton("CANCEL");
		noButton.addActionListener(new PopupActionListener());
		buttonPanel.add(noButton);
		
		buttonPanel.setSize(200,100);
		buttonPanel.setLocation(260,250);
		buttonPanel.setVisible(true);
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
	
	void saveData()
	{
		Schedule sc = new Schedule(timeText.getText(), titleText.getText(), memoText.getText());
		scheduleData.addElement(sc);
	}
	
	class PopupActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();
			
			if(button.getText().equals("SAVE"))
			{
				saveData();
				CloseFrame();
			}
			else if(button.getText().equals("CANCEL"))
				CloseFrame();
		}
	}
}
