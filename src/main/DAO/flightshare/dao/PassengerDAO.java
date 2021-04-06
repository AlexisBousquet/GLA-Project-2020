package DAO.flightshare.dao;

public interface PassengerDAO {

	
	Reponse add_new_Passenger(Passenger p);
	Reponse login_passenger(String username,String password);
}
