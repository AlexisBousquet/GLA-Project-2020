package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.datanucleus.dao.PassengerDAO;

@Path("/passenger")
public class PassengerRessources implements PassengerDAO {

	@PUT
	@Path("/signUp/{username}/{password}/{mail}")
	public void add_new_Passenger(String name,String surname,String mail,String password) {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{mail}/{password}")
	public boolean login_passenger(String mail,String password) {
		return true;
	}
}
