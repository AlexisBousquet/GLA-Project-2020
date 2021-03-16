package webservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/passenger")
public class PassengerResourcesBouchon {
	
	public class SimpleClass{
		public String s = "TEST";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signUp/{username}/{password}")
	public SimpleClass signUp(@PathParam("username") String username, @PathParam("password") String password) {		
		SimpleClass c = new SimpleClass();
		c.s = "Username = "+username+"\nPassword = "+password;
		System.out.println(c.s);
		return c;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signUp/{username}/{surname}/{mail}/{password}")
	public SimpleClass newPilot(@PathParam("username") String username,@PathParam("surname") String surname,@PathParam("mail") String mail, @PathParam("password") String password) {
		SimpleClass c = new SimpleClass();
		c.s = "Username = "+username+"\n"
				+ "Surname = "+surname+"\n"
				+ "Mail = "+mail+"\n"
				+ "Password = "+password;
		System.out.println(c.s);
		return c;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{username}/{password}")
	public SimpleClass logIn(@PathParam("username") String username, @PathParam("password") String password) {		
		SimpleClass c = new SimpleClass();
		c.s = "Username = "+username+"\n"
				+ "Password = "+password;
		System.out.println(c.s);
		return c;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{username}/{password}/{data}")
	public SimpleClass modifyPilot(@PathParam("username") String username, @PathParam("password") String password, @PathParam("data") String data) {		
		SimpleClass c = new SimpleClass();
		c.s = "Username = "+username+"\n"
				+ "Password = "+password+"\n"
				+ "Data = "+data+"\n";
		System.out.println(c.s);
		return c;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logIn/{username}/{password}")
	public SimpleClass deletePilot(@PathParam("username") String username, @PathParam("password") String password) {		
		SimpleClass c = new SimpleClass();
		c.s = "Username = "+username+"\n"
				+ "Password = "+password;
		System.out.println(c.s);
		return c;
	}
}
