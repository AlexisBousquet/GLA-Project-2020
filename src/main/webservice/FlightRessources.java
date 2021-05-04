package webservice;



import java.util.List;

import javax.jdo.JDOHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.FlightDaoImpl;
import DAO.flightshare.dao.Reponse;




@Path("/flight")
public class FlightRessources{
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add/{pilot_id}")
	public Reponse add_flight(@PathParam("pilot_id")long id,Flight f) {
		FlightDaoImpl fdi=new FlightDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r=fdi.add_flight(f, id);
		return r;
	}
	
	@GET
	@Path("/bookFlight/{flight_id}/{passenger_id}/{seats}")
	public void book_flight(@PathParam("flight_id")long id,@PathParam("passenger_id")long p,@PathParam("seats")int seats) {
		FlightDaoImpl fdi=new FlightDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		fdi.book_flight(id, p, seats);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchFull/{a_dep}/{date_dep1}/{date_dep2}/{seats}")
	public List<Flight> search_flight(@PathParam("a_dep")String a_dep,@PathParam("date_dep1")String date_dep,@PathParam("date_dep2")String date_dep2,@PathParam("seats")int seats) {
		FlightDaoImpl fdi=new FlightDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		DateTime dt=DateTime.parse(date_dep);
		DateTime dt2=DateTime.parse(date_dep2);
		List<Flight> retour=fdi.search_flight(a_dep,dt,dt2,seats);
		System.out.println(retour.size());		
		return retour;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getFlightInformation/{flight_id}")
	public Flight getFlightInformation(@PathParam("flight_id")long id) {
		FlightDaoImpl fdi=new FlightDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		return fdi.getFlightInfo(id);
	}

}