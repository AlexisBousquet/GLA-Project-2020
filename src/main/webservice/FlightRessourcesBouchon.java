package webservice;

import java.time.LocalDateTime;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/flightbouchon")
public class FlightRessourcesBouchon {
	public class SimpleClass{
		public String s = "TEST";
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add/{a_dep}/{a_arr}/{date_dep}/{duration}/{price}/{remainingseats}/{id_pilot}")
	public SimpleClass addFlight(@PathParam("a_dep") String a_dep, @PathParam("a_arr") String a_arr, @PathParam("date_dep") String date_dep, @PathParam("duration") String duration, @PathParam("price") String price, @PathParam("remainingseats") String remainingseats, @PathParam("id_pilot") String id_pilot) {		
		SimpleClass c = new SimpleClass();
		c.s = "Departure location = "+a_dep+"\n"
				+"Arrival location = "+a_arr+"\n"
				+"Date of departure = "+date_dep+"\n"
				+"Duration = "+duration+"\n"
				+"Price = "+price+"\n"
				+"Remaining seats = "+remainingseats+"\n"
				+"Pilot's ID = "+id_pilot;
		System.out.println(c.s);
		return c;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/change_seat/{flight_id}/{seats}/{passengers}")
	public SimpleClass changeSeats(@PathParam("flight_id") String flight_id, @PathParam("seats") String seats, @PathParam("passengers") String passengers) {		
		SimpleClass c = new SimpleClass();
		c.s = "Flight's ID = "+flight_id+"\n"
				+"Remaining seats = "+seats;
		System.out.println(c.s);
		return c;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{flight_id}")
	public SimpleClass deleteFlight(@PathParam("flight_id") String flight_id) {		
		SimpleClass c = new SimpleClass();
		c.s = "Flight's ID = "+flight_id;
		System.out.println(c.s);
		return c;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{flight_id}")
	public SimpleClass getFlight(@PathParam("flight_id") String flight_id) {		
		SimpleClass c = new SimpleClass();
		c.s = "Flight's ID = "+flight_id;
		System.out.println(c.s);
		return c;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchFull/{a_dep}/{date_dep}/{seats}")
	public SimpleClass searchFlight(@PathParam("a_dep") String a_dep, @PathParam("date_dep") String date_dep, @PathParam("seats") String seats) {		
		SimpleClass c = new SimpleClass();
		c.s = "Departure location = "+a_dep+"\n"
				+"Date of departure = "+date_dep+"\n"
				+"Remaining seats = "+seats;
		System.out.println(c.s);
		return c;
	}

}
//@Path("/add/{a_dep}/{a_arr}/{date_dep}/{duration}/{price}/{remainingseats}/{id_pilot}")
/*@PathParam("a_dep")String a_dep,@PathParam("a_arr")String a_arr,@PathParam("date_dep")LocalDateTime date_dep,@PathParam("duration")int duration,@PathParam("price")int price,@PathParam("remainingseats")int remainingseats,@PathParam("id_pilot")long idpilot*/