package com.example.datanucleus.dao;

public interface PassengerDAO {

	
	//GET
	void add_new_Passenger(String name,String surname,String mail,String password);
	boolean login_passenger(String mail,String password);
}
