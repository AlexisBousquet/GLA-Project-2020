package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class Database {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("FlightShare");
		PersistenceManager pm = pmf.getPersistenceManager();
	}	
}
