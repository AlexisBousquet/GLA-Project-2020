package webservice;

import javax.jdo.JDOHelper;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.flightshare.dao.Reponse;
import DAO.flightshare.dao.Reservation;
import DAO.flightshare.dao.ReservationDaoImpl;

@Path("/reservation")
public class ReservationRessources {
	
	@GET
	@Path("/getReservationInfo/{id_reservation}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation getReservationInfo(@PathParam("id_reservation")long id) {
		ReservationDaoImpl rdi=new ReservationDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		return rdi.getReservationInfo(id);
	}
	
	@PUT
	@Path("/refuseReservation/{id_reservation}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reponse ReservationRefuse(@PathParam("id_reservation")long id) {
		ReservationDaoImpl rdi=new ReservationDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		rdi.refuseReservation(id);
		return new Reponse(0,"Reservation refusée");
	}
	
	@PUT
	@Path("/confirmReservation/{id_reservation}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reponse ReservationAccept(@PathParam("id_reservation")long id) {
		ReservationDaoImpl rdi=new ReservationDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		rdi.acceptReservation(id);
		return new Reponse(0,"Reservation acceptée");
	}
	
}
