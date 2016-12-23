import java.io.*;

public class UserAccount implements Serializable{
	public String userId, userPassword, userName;
	static int userCounts = 0;
	
	public UserAccount(){
	}
	
	public UserAccount(String userName, String userId, String userPassword){
		this.userName = userName;
		this.userId = userId;
		this.userPassword = userPassword;
	}
	
	public void changeUserAccount(String userName, String userId, String userPassword){
		this.userName = userName;
		this.userId = userId;
		this.userPassword = userPassword;	
	}
	
	public String getUserName(){
        return userName;
	}
	
	public String getUserId(){
        return userId;
	}
	
	public String getUserPassword(){
        return userPassword;
	}
	
	public void setUserName(String userName){
		this.userName= userName;
	}
	
	public void setUserId(String userId){
		this.userId= userId;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword= userPassword;
	}
	
	public static int getUserAccounts(){
		return userCounts;
	}
	
	public static void setUserAccounts(int userCounts){
		UserAccount.userCounts = userCounts;
	}
	
	

}
