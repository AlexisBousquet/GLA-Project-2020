
public interface PassengerDAO {
	/*renvoie le passager associ� a cette ID*/
	Passenger getPassenger(String id);
	
	/*Renvoie l'email associ� a cet ID*/
	String getEmail(String id);
}
