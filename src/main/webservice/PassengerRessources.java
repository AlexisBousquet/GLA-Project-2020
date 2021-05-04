package webservice;

import javax.jdo.JDOHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.flightshare.dao.Passenger;

import DAO.flightshare.dao.PassengerDaoImpl;
import DAO.flightshare.dao.Reponse;



@Path("/passenger")
public class PassengerRessources{

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signUp")
	public Reponse add_new_Passenger(Passenger p) {
		PassengerDaoImpl rdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r=null;
		r=rdi.add_new_Passenger(p);

		return r;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{mail}/{password}")
	public Reponse login_passenger(@PathParam("mail")String mail,@PathParam("password")String password) {
		PassengerDaoImpl rdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r= rdi.login_passenger(mail, password);
		return r ;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPassengerInformation/{passenger_id}")
	public Passenger getPassengerInformation(@PathParam("passenger_id")long passenger_id) {
		
		PassengerDaoImpl pdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Passenger p=pdi.getPassengerById(passenger_id);
		System.out.println(p);
		
		return p;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{passenger_id}")
	public Reponse modifyPilot(@PathParam("passenger_id")long pilot_id,Passenger p) {
		PassengerDaoImpl pdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		return pdi.modifyPassenger(pilot_id, p);
	}
	
}
