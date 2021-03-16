package com.example.datanucleus.dao;


public interface FlightDAO {
	
	//PUT
	void add_flight(String a_dep,String a_arr,String date_dep,int duration,int price,int remainingseats,long idpilot);
	
	//POST
	void modify_flight_remainingseats(long id,int seats,String passangers);
	
	//GET
	void search_flight(String a_dep,String date_dep,int places);

}
