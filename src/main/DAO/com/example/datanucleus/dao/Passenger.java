package com.example.datanucleus.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Passenger {
	

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected long id;
	
	protected String name;
	protected String surname;
	protected String password;
	protected String mail;
	
	public Passenger(String name, String surname, String mail,String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.password=password;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", surname=" + surname + ", mail=" + mail + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
