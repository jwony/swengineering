import java.io.Serializable;

public class User implements Serializable {
	private String userName;
	private String userNumber;
	private String userAddress;
	private static int userCount=0;
	
	public User(String name, String phone, String address) {
		this.userName = name;
		this.userNumber = phone;
		this.userAddress = address;
		userCount++;
	}
	
	public String getName() {
		return userName;
	}
	public void setName(String name) {
		this.userName = name;
	}
	public String getPhone() {
		return userNumber;
	}
	public void setPhone(String phone) {
		this.userNumber = phone;
	}
	public String getAddress() {
		return userAddress;
	}
	public void setAddress(String address) {
		this.userAddress = address;
	}
	public static int getStudentCount() {
		return userCount;
	}
	

}