package DAO.flightshare.dao;



public interface PilotDAO {
	

	
	Reponse add_new_Pilot(Pilot p);
	Reponse login_Pilot(String username,String password);
	
}
