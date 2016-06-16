package addressbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Implements library to store and update an array list of the class Entry.
 * Includes functions to add, remove, and search for entries, and to read 
 * and write address book from and to file.
 * 
 * @author MaryEileen Fagan
 */
public class AddressBook {
private List<Entry> entryList;
/**
 * Creates a new array list of entries from class Entry called entryList.
 */
public AddressBook() {
    entryList = new ArrayList<Entry>();  
  }
/**
 * Adds an entry to the address book entryList.
 * 
 * @param instance of Enry class
 * @return true if the entry has been added, false otherwise
 */
  public boolean add(Entry entry) {
    return entryList.add(entry);
  }
/**
 * Removes an entry from the address book entryList.
 * 
 * @param instance of Entry class
 * @return true if entry has been removed, false otherwise
 */
  public boolean remove(Entry entry) {
    return entryList.remove(entry);
  }
/**
 * Creates a list of found entries.
 * Iterates through each instance of Entry in the entryList and 
 * checks if the entry contains the string word.
 * Adds each entry containing the string word to the list of found entries.
 * 
 * @param string that will be searched
 * @return list of Entry class
 */
  public List<Entry> search(String word) {
    List<Entry> found = new ArrayList<Entry>();
    for(Entry entry: entryList) {
      if(entry.contains(word)) {
        found.add(entry);    	  
      }
    }
    return found;
  }
/**
 * Written with guidance of website below.
 * http://beginnersbook.com/2014/01/how-to-write-to-file-in-java-using-bufferedwriter/
 * Creates a buffered writer and file writer and writes each entry in
 * entry list to buffered writer.
 * Catches any ioexception error or any error closing the buffered writer.
 * 
 * @param filename where the address book should be saved
 */
  public void save(String fileName) {
    BufferedWriter bw = null;
    try {
      File file = new File(fileName);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(fileName);
      bw = new BufferedWriter(fw);
      for (Entry entry: entryList) {
        bw.write(entry.toString());
        bw.newLine();
      }
    } catch(IOException ioe) {
    	ioe.printStackTrace();
      }
      finally  {
        try {
          if(bw!=null){
        	  bw.close();
          }
          }catch(Exception ex) {
              System.out.println("Error: could not close BufferedWriter");
          }
      }
  }
/**
 * Creates a new AddressBook, new Entry instance called contact, and null scanner. 
 * Creates a loop while there are still lines to be read in and scans 
 * each line in. Builds a new Entry instance and assigns it to the Entry contact. Closes
 * scanner.
 * Catches any ioexception error.
 * 
 * @param filename where the address book should be saved
 */
  public static void read(String fileName){
    AddressBook addressbook = new AddressBook();
    Entry contact;
    Scanner fin = null;
	try {
      FileReader fr = new FileReader(fileName);
      fin = new Scanner(fr); 
      while (fin.hasNextLine()){
        String name = fin.nextLine();
        String street = fin.nextLine();
        String city = fin.nextLine();
        String state = fin.nextLine();
        String zip = fin.nextLine();
        String country = fin.nextLine();
        String number = fin.nextLine();
        String email = fin.nextLine();
        String note = fin.nextLine();
        contact = new Entry.Builder(name).street(street).city(city).
              state(state).zip(zip).country(country).number(number).
              email(email).note(note).build();
          addressbook.add(contact);
		}
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        fin.close();
    }
  }
}