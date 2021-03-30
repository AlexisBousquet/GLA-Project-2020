package webservice;

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

import com.example.datanucleus.dao.Passenger;
import com.example.datanucleus.dao.PassengerDAO;
import com.example.datanucleus.dao.PassengerDAO.Reponse;
import com.example.datanucleus.dao.PassengerDaoImpl;

@Path("/passenger")
public class PassengerRessources{

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signUp")
	public Reponse add_new_Passenger(String json) {
		ObjectMapper om=new ObjectMapper();
		PassengerDaoImpl rdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r=null;
		try {
			Passenger p=om.readValue(json, Passenger.class);
			r=rdi.add_new_Passenger(p);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{mail}/{password}")
	public Reponse login_passenger(@PathParam("mail")String mail,@PathParam("password")String password) {
		PassengerDaoImpl rdi=new PassengerDaoImpl(JDOHelper.getPersistenceManagerFactory("FlightShare"));
		Reponse r= rdi.login_passenger(mail, password);
		return r ;
	}
}
