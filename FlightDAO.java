import java.util.Date;
import java.util.List;

public interface FlightDAO {
	/*renvoie la liste de tous les vols */
	List<Flight> getFlight();
	
	/*renvoie la liste des vols associés a ces aerodrome et date*/
	List<Flight> getFlight(String departure,String arrival,Date d);
	
	/*renvoie le vol associé a cet ID*/
	Flight getFlight(String ID);
		
}
