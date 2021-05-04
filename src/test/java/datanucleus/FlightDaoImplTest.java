package datanucleus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.joda.time.DateTime;
import org.junit.Test;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.FlightDaoImpl;
import DAO.flightshare.dao.Passenger;
import DAO.flightshare.dao.Reservation;

public class FlightDaoImplTest {


	@Test
	public void test_book_flight() {
		Flight f1=new Flight("CDG","NRT",DateTime.now(),1,2,4,"michel",0);
		Passenger pa = new Passenger("a","b","c","d");
		
		PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("Test");
		PersistenceManager pm=pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		FlightDaoImpl fdi=new FlightDaoImpl(pmf);
		long id_pa;
		long id_flight;
		try {
			tx.begin();
			pm.makePersistent(f1);
			pm.makePersistent(pa);
			id_pa=pa.getId();
			id_flight=f1.getId();
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		fdi.book_flight(id_flight,id_pa, 1);
		PersistenceManager pm2=pmf.getPersistenceManager();
		Transaction tx2 = pm2.currentTransaction();
		try {
			tx2.begin();
			Reservation r=pm.getObjectById(Reservation.class,1);
			assertEquals(id_pa,r.getPassenger_id());
			assertEquals(id_pa, r.getFlight_id());
			Flight f2=pm.getObjectById(Flight.class,id_flight);
			Passenger p2=pm.getObjectById(Passenger.class,id_pa);
			assertTrue(f2.getPassengers().contains(r));
			assertTrue(p2.getList_of_reservations().contains(r));
			tx2.commit();
		} finally {
			if (tx2.isActive()) {
				tx2.rollback();
			}
			pm2.close();
		}
		
	}
	
	@Test
	public void salut() {
		System.out.println("yo");
	}

}
