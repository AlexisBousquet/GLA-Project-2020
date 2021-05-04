package DAO.flightshare.dao;

import java.util.LinkedList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pilot {
	
	

	public Pilot(String firstName, String lastName, String password, String experience, String qualification, String mail,
			int number_of_flight_hours) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.experience = experience;
		this.qualification = qualification;
		this.mail = mail;
		this.number_of_flight_hours = number_of_flight_hours;
		list_of_flights = new LinkedList<Flight>();
	}
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected long id;
	
	protected String firstName;
	protected String lastName;
	protected String password;
	protected String experience;
	protected String qualification;
	protected String mail;
	protected int number_of_flight_hours;
	@Persistent
	protected LinkedList<Flight> list_of_flights;
	
	

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getNumber_of_flight_hours() {
		return number_of_flight_hours;
	}
	public void setNumber_of_flight_hours(int number_of_flight_hours) {
		this.number_of_flight_hours = number_of_flight_hours;
	}
	public LinkedList<Flight> getList_of_flights() {
		return list_of_flights;
	}
	public void setList_of_flights(LinkedList<Flight> list_of_flights) {
		this.list_of_flights = list_of_flights;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String s;
		if(this.id==0) {
			s=null;
		}
		else s="Pilot [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", experience=" + experience + ", qualification=" + qualification + ", mail=" + mail
				+ ", number_of_flight_hours=" + number_of_flight_hours + ", list_of_flights=" + list_of_flights + "]";
		return s;
	}
	
	void copy(Pilot p) {
		this.experience=p.getExperience();
		this.firstName=p.getFirstName();
		this.lastName=p.getLastName();
		this.mail=p.getMail();
		this.number_of_flight_hours=p.getNumber_of_flight_hours();
		this.qualification=p.getQualification();
		this.password=p.getPassword();
		
	}

}

