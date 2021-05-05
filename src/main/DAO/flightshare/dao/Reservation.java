package DAO.flightshare.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", passenger_id=" + passenger_id + ", flight_id=" + flight_id + ", seats="
				+ seats + ", status=" + status + "]";
	}

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	protected long id;
	
	long passenger_id;
	long flight_id;
	int seats;
	int status;
	

	public Reservation( long passenger_id, long flight_id, int seats, int status) {
		super();
		this.passenger_id = passenger_id;
		this.flight_id = flight_id;
		this.seats = seats;
		this.status = status;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(long passenger_id) {
		this.passenger_id = passenger_id;
	}

	public long getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(long flight_id) {
		this.flight_id = flight_id;
	}


	
}
