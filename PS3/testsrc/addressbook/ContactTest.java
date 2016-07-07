package addressbook;

import static org.junit.Assert.*;
import addressbook.Contact;
import addressbook.Contact.Builder;

import org.junit.Before;
import org.junit.Test;

public class ContactTest {
	
	@Test
	public final void testMatch() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetName() {
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		assertEquals("MaryEileen Fagan", contact.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetName_twoNames() {
		Builder contact = new Contact.Builder().withName("MaryEileen Fagan");
		contact.withName("Zadie Smith");
	}
	
	@Test
	public final void testGetEmailAddress() {
		Contact contact = new Contact.Builder().withEmail("mf3323@nyu.edu").build();
		assertEquals("mf3323@nyu.edu", contact.getEmailAddress());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetEmailAddress_twoEmails(){
		Builder contact = new Contact.Builder().withEmail("mf3323@nyu.edu");
		contact.withEmail("hello@penn.edu");
	}

	@Test
	public final void testGetPhoneNumber() {
		Contact contact = new Contact.Builder().withPhoneNumber("21599999999").build();
		assertEquals("21599999999", contact.getPhoneNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetPhoneNumber_twoNumbers(){
		Builder contact = new Contact.Builder().withPhoneNumber("2222345332");
		contact.withPhoneNumber("2348234");
	}

	@Test
	public final void testGetAddress() {
		Contact contact = new Contact.Builder().withAddress("9999 wellington way").build();
		assertEquals("9999 wellington way", contact.getAddress());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetAddress_twoAddresses(){
		Builder contact = new Contact.Builder().withAddress("osage avenue");
		contact.withAddress("Washington Square Park");
	}

	@Test
	public final void testGetNote() {
		Contact contact = new Contact.Builder().withNote("person I hate").build();
		assertEquals("person I hate", contact.getAddress());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetNote_twoNotes(){
		Builder contact = new Contact.Builder().withNote("person I hate");
		contact.withNote("person I think is okay");
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public final void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public final void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public final void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public final void testToString1() {
		fail("Not yet implemented");
	}

	@Test
	public final void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public final void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public final void testFinalize() {
		fail("Not yet implemented");
	}

}
