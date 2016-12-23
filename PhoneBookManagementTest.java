import static org.junit.Assert.*;

import org.junit.Test;

public class PhoneBookManagementTest {	
	String testName = "눈송이";
	String testPhoneNumber = "010-1111-2222";
	String testAddress = "서울시 용산구 청파동 숙명여자대학교";
	
	PhoneBookManagement phoneBookManagement = new PhoneBookManagement();	

	@Test
	public void userNameTest() {			
		assertEquals(phoneBookManagement.addPhoneBookNameTest(testName, testPhoneNumber, testAddress),testName);
	}
	
	@Test
	public void userPhoneNumberTest() {			
		assertEquals(phoneBookManagement.addPhoneBookNumberTest(testName, testPhoneNumber, testAddress),testPhoneNumber);
	}
	
	@Test
	public void userPhoneAddressTest() {			
		assertEquals(phoneBookManagement.addPhoneBookAddressTest(testName, testPhoneNumber, testAddress),testAddress);
	}
}
