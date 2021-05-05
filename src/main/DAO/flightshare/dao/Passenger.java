package DAO.flightshare.dao;

import java.util.LinkedList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Passenger {
	

	public LinkedList<Reservation> getList_of_reservations() {
		return list_of_reservations;
	}

	public void setList_of_reservations(LinkedList<Reservation> list_of_reservations) {
		this.list_of_reservations = list_of_reservations;
	}

	public Passenger(String firstName, String lastName, String password, String mail) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.password = password;
		this.mail = mail;
	}

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected long id;
	
	protected String firstName;
	protected String LastName;
	protected String password;
	protected String mail;
	protected LinkedList<Reservation> list_of_reservations;
	




	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", LastName=" + LastName + ", password=" + password
				+ ", mail=" + mail + ", list_of_reservations=" + list_of_reservations + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getId() {
		return id;
	}


	void copy(Passenger p) {
		this.firstName=p.getFirstName();
		this.LastName=p.getLastName();
		this.mail=p.getMail();
		this.password=p.getPassword();
	}
	

	
	
	
}
