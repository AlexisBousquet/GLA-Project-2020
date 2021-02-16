
public interface PassengerDAO {
	/*renvoie le passager associé a cette ID*/
	Passenger getPassenger(String id);
	
	/*Renvoie l'email associé a cet ID*/
	String getEmail(String id);
}
