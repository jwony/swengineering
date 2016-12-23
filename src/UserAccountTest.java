import static org.junit.Assert.*;
import org.junit.Test;

public class UserAccountTest {

	@Test
	public void testUserAccountStringStringString() {
		UserAccount user = new UserAccount("Sarah", "sookmyung16", "sm2016");
	    assertEquals("Sarah",user.getUserName());
	    assertEquals("sookmyung16",user.getUserId());
	    assertEquals("sm2016",user.getUserPassword());
	}
	
	public void testLogin(){
		UserAccount user = new UserAccount("Sarah","sookmyung16","sm2016");
		assertEquals("success","sookmyung16","sm2016");
	}

}
