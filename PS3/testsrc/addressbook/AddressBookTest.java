package addressbook;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import java.util.Collections;
import org.junit.Test;

import addressbook.Contact;
import addressbook.AddressBook;

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
  public final void testGetContactList_zeroContacts(){
    assertEquals(0, getContactList(addressbook).size());
  }

  @Test
  public final void testGetContactList_oneContact(){
    Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
    addressbook.addContact(contact);
    assertEquals(1, getContactList(addressbook).size());
  }

  @Test
  public final void testGetContactList_multipleContactsSize(){
    Contact contact1 = new Contact.Builder().withNote("my best friend").build();
    Contact contact2 = new Contact.Builder().withName("Kelly").withEmail("12345").withNote("hello").build();
    Contact contact3 = new Contact.Builder().withNote("Kelly").withPhoneNumber("12345").withEmail("hello").build();
    Contact contact4 = new Contact.Builder().withName("Kelly").withEmail("12345").build();
    Contact contact5 = new Contact.Builder().withAddress("Hopper Way").withEmail("12345").withNote("hello").build();
    addressbook.addContact(contact1);
    assertEquals(1, getContactList(addressbook).size());
    addressbook.addContact(contact2);
    assertEquals(2, getContactList(addressbook).size());
    addressbook.addContact(contact3);
    assertEquals(3, getContactList(addressbook).size());
    addressbook.addContact(contact4);
    assertEquals(4, getContactList(addressbook).size());
    addressbook.addContact(contact5);
    assertEquals(5, getContactList(addressbook).size());
  }
  
  @Test
  public final void testAddressBook() {
    assertEquals(0, getContactList(addressbook).size());
  }

  @Test
  public final void testAddressBookString() throws FileNotFoundException, IOException {
    new File("addressBookFile");
    Contact contact = new Contact.Builder().withEmail("kw@yahoo.com").withPhoneNumber("55534-4").
        withName("Kanye West").withNote("rapper").build();
    addressbook.addContact(contact);
    addressbook.save("addressBookFile");
    AddressBook addressbookFromFile = new AddressBook("addressBookFile");
    assertEquals(1, getContactList(addressbookFromFile).size());
  }

  @Test
  public final void testAddContact() {
    Contact contact = new Contact.Builder().withName("MaryEileen Fagan").build();
    addressbook.addContact(contact);
    assertEquals(1, getContactList(addressbook).size());
  }

  @Test
  public final void testRemoveContact() {
    Contact contact = new Contact.Builder().withName("Ziggy Stardust").build();
    addressbook.addContact(contact);
    assertEquals(1, getContactList(addressbook).size());
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
  public final void testSave() throws FileNotFoundException, IOException {
    File addressBookFile = new File("addressBookFile");
    Contact contact = new Contact.Builder().withEmail("kw@yahoo.com").withPhoneNumber("55534-4").
        withName("Kanye West").withNote("rapper").build();
    addressbook.addContact(contact);
    addressbook.save("addressBookFile");
    assertTrue(addressBookFile.exists());
  }
	
  @Test(expected = FileNotFoundException.class) 
  public final void testSave_fileNotFoundException() throws FileNotFoundException, IOException{
    Contact contact = new Contact.Builder().withEmail("kw@yahoo.com").withPhoneNumber("55534-4").
        withName("Kanye West").withNote("rapper").build();
    addressbook.addContact(contact);
    addressbook.save("nonExistentAddressBook");
  }

  @Test
  public final void testToString() {
    Contact contact1 = new Contact.Builder().withNote("person from the park").build();
    Contact contact2 = new Contact.Builder().withPhoneNumber("215").withAddress("Sesame Street").build();
    Contact contact3 = new Contact.Builder().withName("Miguel").build();
    Contact contact4 = new Contact.Builder().withName("Melanie").build();
    addressbook.addContact(contact1);
    addressbook.addContact(contact2);
    addressbook.addContact(contact3);
    addressbook.addContact(contact4);
    String expected =  "person from the park" + "\n" + "\n" + "215" + "\n" + "Sesame Street" + "\n" + "\n"
    + "Miguel" + "\n" + "\n" + "Melanie" + "\n" + "\n";
    assertEquals(expected, addressbook.toString());
  }
}