package dao_test;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.Test;

import com.example.datanucleus.dao.Flight;
import com.example.datanucleus.dao.FlightDaoImpl;
import com.example.datanucleus.dao.Pilot;


public class testFlightDAO {

	@Test
	public void test_add_flight() {
		/*PersistenceManagerFactory pmf= JDOHelper.getPersistenceManagerFactory("FlightShare");
		FlightDaoImpl fdi=new FlightDaoImpl(pmf);
		//Pilot p=new Pilot("michel","bernard",45,"grand chaperon","boubou",50);
		//Flight f=new Flight("berlin","dublin",Date.valueOf("1998-5-5"),Time.valueOf("23:4:5"),30,41,6,"michel",p);
		fdi.add_flight(f);
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class,"this.date < datetest && this.time > timetest");

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
			pm.close();
		}
		*/
	}

}
