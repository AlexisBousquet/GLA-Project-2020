package webservice;

import java.io.IOException;
import java.util.LinkedList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.Passenger;
import DAO.flightshare.dao.PassengerDaoImpl;
import DAO.flightshare.dao.Pilot;
import DAO.flightshare.dao.PilotDaoImpl;
import DAO.flightshare.dao.Reponse;

import java.io.IOException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/pilot")
public class PilotRessources{

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signUp")
	public Reponse add_new_Pilot(Pilot p) {
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		p.setList_of_flights(new LinkedList<Flight>());
		p.getList_of_flights().add(new Flight());
		System.out.println(p);
		Reponse r=null;
		r=pdi.add_new_Pilot(p);

		return r;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{mail}/{password}")
	public Reponse login_passenger(@PathParam("mail")String mail,@PathParam("password")String password) {
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r= pdi.login_Pilot(mail, password);
		return r ;
	}
}


