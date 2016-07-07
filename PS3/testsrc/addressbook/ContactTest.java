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
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").withName("Zadie Smith").build();
	}

	@Test
	public final void testGetEmailAddress() {
		Contact contact = new Contact.Builder().withEmail("mf3323@nyu.edu").build();
		assertEquals("mf3323@nyu.edu", contact.getEmailAddress());
	}

	@Test
	public final void testGetPhoneNumber() {
		Contact contact = new Contact.Builder().withPhoneNumber("21599999999").build();
		assertEquals("21599999999", contact.getPhoneNumber());
	}

	@Test
	public final void testGetAddress() {
		Contact contact = new Contact.Builder().withAddress("9999 wellington way").build();
		assertEquals("9999 wellington way", contact.getAddress());
	}

	@Test
	public final void testGetNote() {
		Contact contact = new Contact.Builder().withNote("person I hate").build();
		assertEquals("person I hate", contact.getAddress());
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
