package swengineering;

public class ContactInfo
{
	private String name;
	private String address;
	private String email;
	private String phone;
	private String id;
	
	ContactInfo(String name,String address,String email, String phone, String id)
	{
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.id = id;
	}
	
	public void SetName(String name){this.name = name;}
	public void SetAddress(String address){this.address = address;}
	public void SetEmail(String email){this.email = email;}
	public void SetPhone(String phone){this.phone = phone;}
	public void SetId(String id){this.id = id;}
	public String GetName(){return name;}
	public String GetAddress(){return address;}
	public String GetEmail(){return email;}
	public String GetPhone(){return phone;}
	public String GetId(){return id;}
}
