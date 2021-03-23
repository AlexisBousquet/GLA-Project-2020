package com.example.datanucleus.dao;

public interface PassengerDAO {

	public class Reponse{
		long id;
		String error;
		
		public Reponse(long idd,String erreur) {
			id=idd;
			error=erreur;
		}
		
	}
	
	void add_new_Passenger(Passenger p);
	Reponse login_passenger(String username,String password);
}
