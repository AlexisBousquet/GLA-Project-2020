package DAO.flightshare.dao;

import java.util.LinkedList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.joda.time.DateTime;




@PersistenceCapable
public class Flight {
	

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected long id;
	
	protected String arrival;//airport of arrival
	protected String departure;//airport of departure
	protected DateTime date;//time of depart
	protected int Duration;//duration of the flight
	protected int price;//price per passenger
	protected int nbremainningseats;
	protected String informations;//information about the aircraft and flight plan
	protected long pilot;
	protected LinkedList<Reservation> passengers;
	
	
	
	public Flight() {}

	public Flight(String departure, String arrival, DateTime date, int duration, int price,
			int nbremainningseats, String informations, long pilot) {
		this.arrival = arrival;
		this.departure = departure;
		this.date = date;
		Duration = duration;
		this.price = price;
		this.nbremainningseats = nbremainningseats;
		this.informations = informations;
		this.pilot = pilot;
		this.passengers = new LinkedList<Reservation>();
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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
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

	public void setNbremainningseats(int l) {
		this.nbremainningseats = l;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public long getPilot() {
		return pilot;
	}

	public void setPilot(long pilot) {
		this.pilot = pilot;
	}

	public LinkedList<Reservation> getPassengers() {
		return passengers;
	}

	public void setPassengers(LinkedList<Reservation> passengers) {
		this.passengers = passengers;
	}

	public long getId() {
		return id;
	}

	


	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", arrival=" + arrival + ", departure=" + departure + ", date=" + date
				+ ", Duration=" + Duration + ", price=" + price + ", nbremainningseats=" + nbremainningseats
				+ ", informations=" + informations + ", pilot=" + pilot + ", passengers=" + passengers + "]";
	}
	
	
	

	
}
