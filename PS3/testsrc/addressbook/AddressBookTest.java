package addressbook;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import addressbook.Contact;
import addressbook.AddressBook;
import java.util.Collections;
import org.junit.Test;

public class AddressBookTest {
	
	private AddressBook addressbook;

	@Before
	public void setUp(){
		addressbook = new AddressBook();
	}
	
	public final List<Contact> getContactList(AddressBook addressbook){
		List<Contact> contactList = Collections.synchronizedList(new ArrayList<Contact>());
		contactList.addAll(addressbook.search(AddressBook.ContactAttribute.EMAIL, ""));
		contactList.addAll(addressbook.search(AddressBook.ContactAttribute.NAME, "")); 
		contactList.addAll(addressbook.search(AddressBook.ContactAttribute.PHONE, "")); 
		contactList.addAll(addressbook.search(AddressBook.ContactAttribute.ADDRESS, "")); 
		contactList.addAll(addressbook.search(AddressBook.ContactAttribute.NOTE, "")); 
		HashSet<Contact> contactSet = new HashSet<Contact>(contactList);
		contactList.clear();
		contactList.addAll(contactSet);
		return contactList;
	}
	
	@Test
	public final void testGetContactList_oneContact(){
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		addressbook.addContact(contact);
		assertEquals(1, getContactList(addressbook).size());
	}
	
	@Test
	public final void testGetContactList_multipleContacts(){
		Contact contact1 = new Contact.Builder().withName("MaryEileen Fagan").build();
		Contact contact2 = new Contact.Builder().withName("Kelly").withEmail("12345").withNote("hello").build();
		Contact contact3 = new Contact.Builder().withNote("Kelly").withPhoneNumber("12345").withEmail("hello").build();
		Contact contact4 = new Contact.Builder().withName("Kelly").withEmail("12345").build();
		Contact contact5 = new Contact.Builder().withAddress("Hopper Way").withEmail("12345").withNote("hello").build();
		addressbook.addContact(contact1);
		addressbook.addContact(contact2);
		addressbook.addContact(contact3);
		addressbook.addContact(contact4);
		addressbook.addContact(contact5);
		assertEquals(5, getContactList(addressbook).size());
	}
	
	
	@Test
	public final void testAddressBook() {
		assertEquals(0, getContactList(addressbook).size());
	}

	@Test
	public final void testAddressBookString() {
		fail("Not yet implemented");
	}

	@Test
	public final void testAddContact() {
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		addressbook.addContact(contact);
		assertEquals(1, getContactList(addressbook).size());
	}

	@Test
	public final void testRemoveContact() {
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
		addressbook.addContact(contact);
		addressbook.removeContact(contact);
		assertEquals(0, getContactList(addressbook).size());
	}

	@Test
	public final void testSearch_Entry() {
		Contact contact = new Contact.Builder().withName("MaryEileen Fagan").withAddress("Rittenhouse Square").build();
		addressbook.addContact(contact);
		addressbook.search(AddressBook.ContactAttribute.EMAIL, "nyu.edu");
		assertEquals(1, addressbook.search(AddressBook.ContactAttribute.NAME, "MaryEileen").size());
	}

	@Test
	public final void testSave() {
		
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

	

}
