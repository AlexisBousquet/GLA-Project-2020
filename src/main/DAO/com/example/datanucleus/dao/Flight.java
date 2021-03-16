package com.example.datanucleus.dao;

import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Flight {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected String id;
	
	protected String arrival;
	protected String departure;
	protected LocalDateTime date;
	protected int Duration;
	protected int price;
	protected int nbremainningseats;
	protected String informations;
	protected Pilot pilot;
	protected LinkedList<Passenger> passengers;
	
	
	
	
	public Flight(String id, String arrival, String departure, LocalDateTime date, int duration, int price,
			int nbremainningseats, Pilot pilot) {
		this.id = id;
		this.arrival = arrival;
		this.departure = departure;
		this.date = date;
		Duration = duration;
		this.price = price;
		this.nbremainningseats = nbremainningseats;
		this.pilot = pilot;
		passengers=new LinkedList<Passenger>();
	}
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNbremainningseats() {
		return nbremainningseats;
	}
	public void setNbremainningseats(int nbremainningseats) {
		this.nbremainningseats = nbremainningseats;
	}
	public Pilot getPilot() {
		return pilot;
	}
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	public LinkedList<Passenger> getPassenger() {
		return passengers;
	}
	public void setPassenger(LinkedList<Passenger> passenger) {
		this.passengers = passenger;
	}
	public String getInformations() {
		return informations;
	}
	public void setInformations(String informations) {
		this.informations = informations;
	}




	@Override
	public String toString() {
		return "Flight [id=" + id + ", arrival=" + arrival + ", departure=" + departure + ", date=" + date
				+ ", Duration=" + Duration + ", price=" + price + ", nbremainningseats=" + nbremainningseats
				+ ", informations=" + informations + ", pilot=" + pilot + ", passengers=" + passengers + "]";
	}
	
}
