package src.assignment1.test;

import java.util.ArrayList;

import src.assignment1.addressbook.*;

public class tester {
  
  public static void main(String[] args) {
	// TODO Auto-generated method stub
    AddressBook book = new AddressBook();
    Contact.Builder contact = new Contact.Builder().setEmailAddress("wesleypainter@gmail.com");
    book.add(contact.build());
    book.exportContacts("./contacts_sample.txt");
    book.importContact("./contacts_sample.txt");
    ArrayList<Contact> results = book.search("wes");
    for (Contact contact1 : results) {
      System.out.println(contact1.getEmailAddress());
    }
    System.out.println(book.remove(contact.build()));
    System.out.println(results.size());
  }

}
