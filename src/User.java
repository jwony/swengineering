import java.io.Serializable;

public class User implements Serializable {

	private String userName;
	private String userPhoneNumber;
	private String userAddress;
	private static int userCount=0;
	
	public User(String name, String phoneNumber, String address) {
		this.userName = name;
		this.userPhoneNumber = phoneNumber;
		this.userAddress = address;
		userCount++;
	}
	
	public String getName() {
		return userName;
	}
	
	public void setName(String name) {
		this.userName = name;
	}
	
	public String getPhoneNumber() {
		return userPhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.userPhoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return userAddress;
	}
	
	public void setAddress(String address) {
		this.userAddress = address;
	}
	
	public static int getUserCount() {
		return userCount;
	}
	
	public String toString() {
		String inputContent;
		inputContent = userName + "     " + userPhoneNumber + "     " + userAddress; 
		return inputContent;
	}
}