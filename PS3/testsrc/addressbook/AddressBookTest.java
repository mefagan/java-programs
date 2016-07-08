package addressbook;

import static org.junit.Assert.*;
import org.junit.Before;
import addressbook.Contact;
import addressbook.AddressBook;
import org.junit.Test;

public class AddressBookTest {
	
	private AddressBook addressbook;
	private Contact contact;
	
	
	@Before
	public void setUp(){
		addressbook = new AddressBook();
	}
	
	@Test
	public final void testAddressBook() {
		assertEquals(0, addressbook.getContactList().size());
	}

	@Test
	public final void testAddressBookString() {
		fail("Not yet implemented");
	}

	@Test
	public final void testAddContact() {
		contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		addressbook.addContact(contact);
		assertEquals(1, addressbook.getContactList().size());
	}

	@Test
	public final void testRemoveContact() {
		contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		addressbook.addContact(contact);
		addressbook.removeContact(contact);
		assertEquals(0, addressbook.getContactList().size());
	}

	@Test
	public final void testSearchOneEntry() {
		contact = new Contact.Builder().withName("MaryEileen Fagan").withAddress("Rittenhouse Square").build();
		addressbook.addContact(contact);
		//assertTrue(1, search(AddressBook.ContactAttribute.NAME, "MaryEileen").size());
	}

	@Test
	public final void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public final void testToString() {
		Contact contact1 = new Contact.Builder().withNote("person from the park").build();
		Contact contact2 = new Contact.Builder().withPhoneNumber("215").withAddress("Sesame Street").build();
		Contact contact3 = new Contact.Builder().withName("Miguel").build();
		Contact contact4 = new Contact.Builder().withName("Melanie").build();
		addressbook.addContact(contact1);
		addressbook.addContact(contact4);
		addressbook.addContact(contact3);
		addressbook.addContact(contact2);
		String expected =  "person from park" + "\n" + "Melanie" +"\n" + "Michel"
			    + "\n" + "215" + "\n" + "Sesame Street" +  "\n";
		assertEquals(expected, addressbook.toString());
	}

	/*@Test
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
	}*/

}
