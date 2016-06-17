package PS1;

import java.lang.StringBuilder;
/**
 * @author maryeileenfagan
 *Implements a class to store basic information about a contact
 *that will then be stored in a list as an address book in the 
 *AddressBook class. Uses a builder pattern to create the class
 *since all parameters but one, the contact name, are required. 
 *Creates a contact entry of strings that include name, street, 
 *city, state, zip, country, number, e-mail, and a note.
 *Class was created by referencing the builder pattern example given
 *in the book "Effective Java" by Josh Bloch.
 */
public class Entry {
  private final String name;
  private final String street;
  private final String city;
  private final String state;
  private final String zip;
  private final String country;
  private final String number;
  private final String email;
  private final String note;  
  public static class Builder {
	/**
	 * Required parameter is the contact's name.
	 */
	private final String name;
	/**
	 * Optional parameters below are initialized to default values.
	 */
	private String street = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	private String country = "";
	private String number = "";
	private String email = "";
	private String note = "";   
	public Builder(String name) {
	  this.name = name;
	}
	public Builder street(String street) { 
      this.street = street;    
      return this;
    }
	public Builder city(String city) { 
	      this.city = city;    
	      return this;
	}
	public Builder state(String state) { 
	      this.state = state;    
	      return this;
	    }
	public Builder zip(String zip) { 
	      this.zip = zip;    
	      return this;
	    }
	public Builder country(String country) { 
	      this.country = country;    
	      return this;
	    }
	public Builder number(String number) { 
      this.number = number;    
      return this;
    }
	public Builder email(String email) { 
      this.email = email;    
      return this;
    }
	public Builder note(String note) { 
      this.note = note;    
      return this;
    }
	public Entry build() {
	  return new Entry(this);
	}
}
  private Entry(Builder builder) {
    name = builder.name;
	street = builder.street;
	city = builder.city;
	state = builder.state;
	zip = builder.zip;
	country = builder.country;
	number = builder.number;
	email = builder.email;
	note = builder.note;
  }
  @Override 
  public String toString() {
    StringBuilder result = new StringBuilder();
     result.append(name);
     result.append("\n");
     result.append(street);
     result.append("\n");
     result.append(city);
     result.append("\n");
     result.append(state);
     result.append("\n");
     result.append(zip); 
     result.append("\n");
     result.append(country);
     result.append("\n");
     result.append(number); 
     result.append("\n");
     result.append(email);
     result.append("\n");
     result.append(note); 
    return result.toString();
  }
  @Override
  public boolean equals(Object obj) {
    if (obj==this) {
      return true;
    }
    if (!(obj instanceof Entry)) {
      return false;
    }
    Entry entry = (Entry) obj;
    return entry.name.equalsIgnoreCase(this.name) 
        && entry.street.equalsIgnoreCase(street)
        && entry.city.equalsIgnoreCase(city)
        && entry.state.equalsIgnoreCase(state)
        && entry.zip.equalsIgnoreCase(zip)
        && entry.country.equalsIgnoreCase(country)
        && entry.number.equalsIgnoreCase(number)
        && entry.email.equalsIgnoreCase(email)
        && entry.note.equalsIgnoreCase(note);
  }
  @Override 
  public int hashCode() {
    int hash = 5;
    hash = 7 * hash + this.name.hashCode();
    hash = 7 * hash + this.street.hashCode();
    hash = 7 * hash + this.city.hashCode();
    hash = 7 * hash + this.state.hashCode();
    hash = 7 * hash + this.zip.hashCode();
    hash = 7 * hash + this.country.hashCode();
    hash = 7 * hash + this.number.hashCode();
    hash = 7 * hash + this.email.hashCode();
    hash = 7 * hash + this.note.hashCode();
    return hash;
  }
/**
 * Checks each parameter of the Entry instance to see if the
 * entry contains a string being searched for. Contains 
 * is used in AddressBook class to search for an entry and
 * may be used to find an entry that will be deleted. 
 * Makes all letters in string searched and each parameter
 * lower case so that search is not case sensitive.
 * 
 * @param string of word being searched
 * @return true if any parameter of Entry contains the string
 */
  public boolean contains(String searched) {
    return name.toLowerCase().contains(searched.toLowerCase()) 
        || street.toLowerCase().contains(searched.toLowerCase())
        || city.toLowerCase().contains(searched.toLowerCase())
        || state.toLowerCase().contains(searched.toLowerCase())
        || zip.toLowerCase().contains(searched.toLowerCase())
        || country.toLowerCase().contains(searched.toLowerCase())
        || number.toLowerCase().contains(searched.toLowerCase())
        || email.toLowerCase().contains(searched.toLowerCase())
        || note.toLowerCase().contains(searched.toLowerCase());
  }
}