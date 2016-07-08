package addressbook;

import static org.junit.Assert.*;
import addressbook.Contact;
import addressbook.Contact.Builder;
import addressbook.MatchableString;
import addressbook.PhoneNumber;

import org.junit.Test;

public class ContactTest {
	
	private Contact contact;
	
	@Test(expected=IllegalArgumentException.class)
	public final void testFieldCount() {
		Builder contact = new Contact.Builder();
		contact.build();
	}

	@Test
	public final void testGetName() {
		contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		assertEquals("MaryEileen Fagan", contact.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetName_twoNames() {
		Builder contactBuilder = new Contact.Builder().withName("MaryEileen Fagan");
		contactBuilder.withName("Zadie Smith");
	}
	
	@Test
	public final void testGetEmailAddress() {
		contact = new Contact.Builder().withEmail("mf3323@nyu.edu").build();
		assertEquals("mf3323@nyu.edu", contact.getEmailAddress());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetEmailAddress_twoEmails(){
		Builder contact = new Contact.Builder().withEmail("mf3323@nyu.edu");
		contact.withEmail("hello@penn.edu");
	}

	@Test
	public final void testGetPhoneNumber() {
		contact = new Contact.Builder().withPhoneNumber("21599999999").build();
		assertEquals("21599999999", contact.getPhoneNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetPhoneNumber_twoNumbers(){
		Builder contactBuilder = new Contact.Builder().withPhoneNumber("2222345332");
		contactBuilder.withPhoneNumber("2348234");
	}

	@Test
	public final void testGetAddress() {
		contact = new Contact.Builder().withAddress("9999 wellington way").build();
		assertEquals("9999 wellington way", contact.getAddress());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetAddress_twoAddresses(){
		Builder contactBuilder = new Contact.Builder().withAddress("osage avenue");
		contactBuilder.withAddress("Washington Square Park");
	}

	@Test
	public final void testGetNote() {
		contact = new Contact.Builder().withNote("person I hate").build();
		assertEquals("person I hate", contact.getNote());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testGetNote_twoNotes(){
		Builder contactBuilder = new Contact.Builder().withNote("person I hate");
		contactBuilder.withNote("person I think is okay");
	}

	@Test
	public final void testToString() {
		contact = new Contact.Builder().withName("Kelly").withEmail("sss@gmail.com").
				withPhoneNumber("62364").
				withAddress("Bobst Library").
				withNote("classmate").build();
		String expected = "Kelly" + "\n" + "sss@gmail.com" + "\n" + 
				"62364" + "\n" + "Bobst Library" + "\n"  + "classmate" + "\n";
		assertEquals(expected, contact.toString());
	}
	
	@Test
	public final void testToStringNullsWithName() {
		contact = new Contact.Builder().withName("Rachel").build();
		String expected = "Rachel";
		assertEquals(expected, contact.toString());
	}
	@Test
	public final void testToStringNullsWithoutName() {
		contact = new Contact.Builder().withEmail("marcel@gmail.com").build();
		String expected = "marcel@gmail.com";
		assertEquals(expected, contact.toString());
	}
	
	@Test
	public final void testMatch(){
		contact = new Contact.Builder().withEmail("marcel@gmail.com").build();
		assertTrue(contact.match(AddressBook.ContactAttribute.EMAIL, ".edu"));
		assertFalse(contact.match(AddressBook.ContactAttribute.EMAIL, "nyu"));
	}
	
	public class MatchableStringTest{
		
	}

	

}
