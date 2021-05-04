package DAO.flightshare.dao;

import java.util.List;

import org.joda.time.DateTime;

public interface FlightDAO {
	
	
	Reponse add_flight(Flight flight,long id_pilot);
	
	
	void book_flight(long id,long id_passengers,int seats);
	
	
	List<Flight> search_flight(String a_dep,DateTime date_dep,DateTime date_dep2,int seats);
	
	
	Flight getFlightInfo(long id);

}
