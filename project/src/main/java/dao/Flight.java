package dao;
import java.util.Date;
import java.util.List;

public class Flight {
	
	String id;
	String arrival;
	String departure;
	Date date;
	int Duration;
	int price;
	int nbremainningseats;
	Pilot pilot;
	List<Passenger> passenger;

}
