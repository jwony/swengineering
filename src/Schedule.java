import java.io.Serializable;

public class Schedule implements Serializable
{
	private String timeText, titleText, memoText;
	// private static int scheduleCount=0;
	
	public Schedule(String time, String title, String memo)
	{
		this.timeText = time;
		this.titleText = title;
		this.memoText = memo;
		// scheduleCount++;
	}
	
	public String getTime()
	{
		return timeText;
	}
	
	public void setTime(String time)
	{
		this.timeText = time;
	}
	
	public String getTitle()
	{
		return titleText;
	}
	
	public void setTitle(String title)
	{
		this.titleText = title;
	}
	
	public String getMemo() 
	{
		return memoText;
	}
	
	public void setMemo(String memo)
	{
		this.memoText = memo;
	}
	
	public String toString() {
		String scheduleList;
		scheduleList = timeText + "      " + titleText + "     "; 
		return scheduleList;
	}
}
