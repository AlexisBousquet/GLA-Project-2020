package DAO.flightshare.dao;



public interface PilotDAO {
	

	
	Reponse add_new_Pilot(Pilot p);
	Reponse login_Pilot(String username,String password);
	Pilot getPilotById(long id);
	Reponse modifyPilot(long id,Pilot p);
	
}
