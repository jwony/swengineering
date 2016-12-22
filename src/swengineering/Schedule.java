package swengineering;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Schedule extends JFrame 
{
	static int i = 0;
	static String[] fileSrc = new String[2];
	static Vector<Cel> v = new Vector<Cel>();
	static Vector<Cel> v2 = new Vector<Cel>();
	//static HashMap<String,>
	static String loginId;
	static ScheduleFrame myD;
	
	Schedule(String id)
	{
		loginId = id;
		fileSrc[0] = "c:\\��ü\\��������Ʈ\\DList.txt";
		fileSrc[1] = "c:\\��ü\\��������Ʈ\\MList.txt";
		myD = new ScheduleFrame();
		
		for(int i = 0; i < 2; i++)
		{
			File f = new File(fileSrc[i]);
			
			if(!f.exists())
			{
				try{
					FileWriter fw = new FileWriter(fileSrc[i]);
					fw.close();
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("���� ����� ����");
				}
			}
		}
	
		LoadData();
		PrintCalAll();
	}
	
	//public static void main(String[] args)	// ���̾ ���� Ŭ����
	//{

		//while(i<v.size())		// ���Ϳ� ����� ��� ���� ���.
		//{
		//	printCal(v.get(i++));
		//}
		//inputData(v);	// ������ �߰��Ѵ�.
		//saveData(v);	// ���Ϳ� �ִ� ��� ������ ���Ͽ� �����Ѵ�.
	//}
	
	void inputData(Vector<Cel> v)
	{
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.print("���� �Է�(yyyy/mm/dd) : ");
			StringTokenizer st = new StringTokenizer(sc.nextLine(),"/");
			System.out.print("���� �Է� : ");
			String nt = sc.next();
			
			Integer integerYear = new Integer(st.nextToken());
			Integer integerMonth = new Integer(st.nextToken());
			Integer integerDate = new Integer(st.nextToken());
			
			Calendar cal = Calendar.getInstance();
			
			cal.clear();
			cal.set(integerYear, integerMonth-1, integerDate); 
			
			v.add(new Cel(nt,cal,loginId));
			
			System.out.println("��� �Է��Ϸ��� �ƹ�Ű�� ��������. �Է� ����� ^Z�Դϴ�.");
		}while(sc.hasNext());
	}
	
	//static void saveData(Vector<Cel> v)	// �ۼ��Ǿ��ִ� ��� ������ �����ϴ� �޼ҵ�
	static void SaveData()
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		String str;
		
		int j=0;
		
		try
		{
			fw = new FileWriter(fileSrc[0]);
			bw = new BufferedWriter(fw);
			
			//Iterator it = v.iterator(); // Iterator ������ �����...
			while(j<v.size())	// ���Ϳ� ����� �����͸� �ѹ����� ������ ���ڿ��� ����� ����.
			{
				str=v.get(j).getNote()+","+v.get(j).getDate().get(Calendar.YEAR)+","+v.get(j).getDate().get(Calendar.MONTH)+","+v.get(j).getDate().get(Calendar.DAY_OF_MONTH)+","+v.get(j).getId()+"\r\n";
				bw.write(str);
				j++;
			}
			
			bw.close();	// ���� �ݱ�.
			fw.close();
			
			j=0;
			
			fw = new FileWriter(fileSrc[1]);
			bw = new BufferedWriter(fw);
			
			//Iterator it = v.iterator();
			
			while(j<v2.size())
			{
				str = v2.get(j).getNote()+","+v2.get(j).getDate().get(Calendar.YEAR)+","+v2.get(j).getDate().get(Calendar.MONTH)+","+v2.get(j).getDate().get(Calendar.DAY_OF_MONTH)+","+v2.get(j).getId()+"\r\n";
				bw.write(str);
				j++;
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
	
	static void LoadData()
	{
		FileReader fr = null;
		BufferedReader br = null;
		
		String r,nt = null;
		Integer integerDate = null;
		Integer integerYear = null;
		Integer integerMonth = null;
		String id = null;
		//Integer integerIndex = null;
		try
		{
			fr = new FileReader(fileSrc[0]);
			br = new BufferedReader(fr);
		
			while((r = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(r,",");
				
				while(st.hasMoreTokens())
				{
					nt = st.nextToken();
					integerYear = new Integer(st.nextToken());
					integerMonth = new Integer(st.nextToken());
					integerDate = new Integer(st.nextToken());
					id = st.nextToken();
					//I_index = new Integer(st.nextToken());
				}
				
				Calendar cal = Calendar.getInstance();
				
				cal.clear();
				cal.set(integerYear, integerMonth, integerDate);
				
				v.add(new Cel(nt,cal,id));
			}
			fr.close();
			
			fr = new FileReader(fileSrc[1]);
			br = new BufferedReader(fr);
			
			while((r = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(r,",");
				
				while(st.hasMoreTokens())
				{
					nt = st.nextToken();
					
					integerYear = new Integer(st.nextToken());
					integerMonth = new Integer(st.nextToken());
					integerDate = new Integer(st.nextToken());
					id = st.nextToken();
					//integerIndex = new Integer(st.nextToken());
				}
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(integerYear, integerMonth, integerDate);
				
				v2.add(new Cel(nt,cal,id));
			}
			
			fr.close();
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("���� ����� ����");
		}		
	}
	static void PrintCalAll()
	{	
		int i = 0;
	
		while(i < v.size())
			PrintCal(v.get(i++));

		i = 0;
	
		while(i < v2.size())
			PrintCal(v2.get(i++));
	}
	
	static void PrintCal(Cel c)
	{
		int year = c.getDate().get(Calendar.YEAR);
		int month = c.getDate().get(Calendar.MONTH)+1;
		int date = c.getDate().get(Calendar.DAY_OF_MONTH);
		
		String day = null;
		
		switch(c.getDate().get(Calendar.DAY_OF_WEEK))
		{
			case Calendar.SUNDAY: day="��";
			break;
			case Calendar.MONDAY: day="��";
			break;
			case Calendar.TUESDAY: day="ȭ";
			break;
			case Calendar.WEDNESDAY: day="��";
			break;
			case Calendar.THURSDAY: day="��";
			break;
			case Calendar.FRIDAY: day="��";
			break;
			case Calendar.SATURDAY: day="��";
			break;
		}
	
		System.out.println("date : "+year+"/"+month+"/"+date+"/"+day) ;
		System.out.println("text : "+c.getNote());
	}
}

class Cel
{
	private String note;
	private Calendar date;
	private String id;

	Cel(String note, Calendar date, String id)
	{
		this.note = note;
		this.date = date;
		this.id = id;
	}
	
	public void setNote(String note){this.note=note;}
	public void setDate(Calendar date){this.date=date;}
	public void setId(String id){this.id=id;}
	public String getNote(){return note;}
	public Calendar getDate(){return date;}
	public String getId(){return id;}
}