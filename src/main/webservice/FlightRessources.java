package webservice;



import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.FlightDaoImpl;
import DAO.flightshare.dao.PilotDaoImpl;




@Path("/flight")
public class FlightRessources{
	
	@PUT
	@Path("/add/{a_dep}/{a_arr}/{date_dep}/{duration}/{price}/{remainingseats}/{id_pilot}")
	public void add_flight(@PathParam("a_dep")String a_dep,@PathParam("a_arr")String a_arr,@PathParam("date_dep")String date_dep,@PathParam("duration")int duration,@PathParam("price")int price,@PathParam("remainingseats")int remainingseats,@PathParam("id_pilot")long idpilot) {
		
		
	}
	
	@POST
	@Path("/change_seat/{flight_id}/{seats}/{passengers}")
	public void modify_flight_remainingseats(@PathParam("flight_id")long id,@PathParam("seats")int seats,@PathParam("passengers")String passangers) {
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchFull/{a_dep}/{date_dep1}/{date_dep2}")
	public List<Flight> search_flight(@PathParam("a_dep")String a_dep,@PathParam("date_dep1")String date_dep,@PathParam("date_dep2")String date_dep2) {
		FlightDaoImpl fdi=new FlightDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		DateTime dt=DateTime.parse(date_dep);
		DateTime dt2=DateTime.parse(date_dep2);
		List<Flight> lf=new LinkedList<Flight>();
		List<Flight> retour=fdi.search_flight(a_dep,dt,dt2);
		System.out.println(retour.size());		
		return retour;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void test(String p) {
		System.out.println(p);
	}

}