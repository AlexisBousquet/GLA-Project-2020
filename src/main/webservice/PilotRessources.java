package webservice;

import java.util.LinkedList;

import javax.jdo.JDOHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.Pilot;
import DAO.flightshare.dao.PilotDaoImpl;
import DAO.flightshare.dao.Reponse;

@Path("/pilot")
public class PilotRessources{

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signUp")
	public Reponse add_new_Pilot(Pilot p) {
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		p.setList_of_flights(new LinkedList<Flight>());
		Reponse r=null;
		r=pdi.add_new_Pilot(p);

		return r;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{mail}/{password}")
	public Reponse login_Pilot(@PathParam("mail")String mail,@PathParam("password")String password) {
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r= pdi.login_Pilot(mail, password);
		return r ;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPilotInformation/{pilot_id}")
	public Pilot getPilotInformation(@PathParam("pilot_id")long pilot_id) {
		
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Pilot p=pdi.getPilotById(pilot_id);
		System.out.println(p);
		
		return p;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{pilot_id}")
	public Reponse modifyPilot(@PathParam("pilot_id")long pilot_id,Pilot p) {
		PilotDaoImpl pdi=new PilotDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		return pdi.modifyPilot(pilot_id, p);
	}
	
}


