package application.models;

import java.io.Serializable;

/**
 * This class represents a natural person.
 * 
 * @author Federico Canali
 *
 */
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the person.
	 */
	private int id;
	/**
	 * Represents the name of the person.
	 */
	private String name;
	/**
	 * Represents the surname of the person.
	 */
	private String surname;
	/**
	 * Represents the address of the person.
	 */
	private String address;
	/**
	 * Represents the fiscal code of the person.
	 */
	private String fiscalCode;
	/**
	 * Represents the credentials of the person.
	 */
	private Credentials credentials;
	
	/**
	 * Default constructor. 
	 */
	public Person() {}

	/**
	 * @param id is the id of the person.
	 * @param name is the name of the person.
	 * @param surname is the surname of the person.
	 * @param address is the address of the person.
	 * @param fiscalCode is the fiscal code of the person.
	 * @param credentials are the credentials of the person.
	 */
	public Person(int id, String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.fiscalCode = fiscalCode;
		this.credentials = credentials;
	}
	
	/**
	 * @param name is the name of the person.
	 * @param surname is the surname of the person.
	 * @param address is the address of the person.
	 * @param fiscalCode is the fiscal code of the person.
	 * @param credentials are the credentials of the person.
	 */
	public Person(String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.fiscalCode = fiscalCode;
		this.credentials = credentials;
	}

	/**
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the fiscalCode
	 */
	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * @param fiscalCode the fiscalCode to set
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + " , name = " + name
				+ " , surname = " + surname + " , address = " +
				address + " , fiscalCode = " + fiscalCode + " , "
				+ credentials + " ]";
	}

}
