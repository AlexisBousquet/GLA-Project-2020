package DAO.flightshare.dao;

import org.joda.time.DateTime;

public class ReservationReponse {
	
	long id_reservation;
	String a_dep;
	String a_arr;
	DateTime date;
	int seats;
	String name;
	String firstName;
	
	public ReservationReponse(long id_reservation, Passenger p,Flight f,int seats) {
		super();
		this.id_reservation = id_reservation;
		this.a_dep = f.getDeparture();
		this.a_arr = f.getArrival();
		this.date = f.getDate();
		this.seats = seats;
		this.name = p.getLastName();
		this.firstName = p.getFirstName();
	}

	@Override
	public String toString() {
		return "ReservationReponse [id_reservation=" + id_reservation + ", a_dep=" + a_dep + ", a_arr=" + a_arr
				+ ", date=" + date + ", seats=" + seats + ", name=" + name + ", firstName=" + firstName + "]";
	}

	public long getId_reservation() {
		return id_reservation;
	}

	public void setId_reservation(long id_reservation) {
		this.id_reservation = id_reservation;
	}

	public String getA_dep() {
		return a_dep;
	}

	public void setA_dep(String a_dep) {
		this.a_dep = a_dep;
	}

	public String getA_arr() {
		return a_arr;
	}

	public void setA_arr(String a_arr) {
		this.a_arr = a_arr;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	
}
