import static org.junit.Assert.*;

import org.junit.Test;

public class PhoneBookManagementTest {	
	String testName = "������";
	String testPhoneNumber = "010-1111-2222";
	String testAddress = "����� ��걸 û�ĵ� �����ڴ��б�";
	
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
