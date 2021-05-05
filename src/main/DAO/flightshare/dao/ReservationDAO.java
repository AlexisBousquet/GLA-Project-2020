package DAO.flightshare.dao;

public interface ReservationDAO {
	
	Reservation getReservationInfo(long id_res);
	
	void acceptReservation(long id_res);
	
	void refuseReservation(long id_res);
}
