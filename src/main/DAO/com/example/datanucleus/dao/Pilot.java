package com.example.datanucleus.dao;

import java.util.LinkedList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pilot {
	
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected String id;
	
	protected String name;
	protected String surname;
	protected int experience;
	protected String qualification;
	protected String mail;
	protected int number_of_flight_hours;
	protected LinkedList<Flight> list_of_flights;
	
	public Pilot(String name, String surname, int experience, String qualification, String mail,
			int number_of_flight_hours) {
		super();
		this.name = name;
		this.surname = surname;
		this.experience = experience;
		this.qualification = qualification;
		this.mail = mail;
		this.number_of_flight_hours = number_of_flight_hours;
		list_of_flights = new LinkedList<Flight>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
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
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + ", surname=" + surname + ", experience=" + experience
				+ ", qualification=" + qualification + ", mail=" + mail + ", number_of_flight_hours="
				+ number_of_flight_hours + ", list_of_flights=" + list_of_flights + "]";
	}
	
}

