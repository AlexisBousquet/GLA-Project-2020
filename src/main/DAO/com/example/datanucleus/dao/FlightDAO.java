package com.example.datanucleus.dao;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightDAO {
	
	
	void add_flight(Flight flight);
	
	
	void modify_flight_remainingseats(long id,int seats,String passengers);
	
	
	List<Flight> search_flight(String a_dep,LocalDateTime date_dep,int places);

}
