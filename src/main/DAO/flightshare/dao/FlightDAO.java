package DAO.flightshare.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.joda.time.DateTime;

public interface FlightDAO {
	
	
	void add_flight(Flight flight);
	
	
	void modify_flight_remainingseats(long id,int seats,String passengers);
	
	
	List<Flight> search_flight(String a_dep,DateTime date_dep,DateTime date_dep2);

}
