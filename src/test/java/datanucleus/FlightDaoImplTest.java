package datanucleus;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.joda.time.DateTime;
import org.junit.Test;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.FlightDaoImpl;

public class FlightDaoImplTest {

	@Test
	public void test_search_flight() {
		
		PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("FlightShare");
		PersistenceManager pm=pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Flight f1=new Flight("CDG","NRT",DateTime.now(),1,2,3,"michel",null);
		Flight f2=new Flight("CDG","MICH",DateTime.now().plusDays(4),1,2,3,"michel",null);
		Flight f3=new Flight("CDG","EL",DateTime.now().plusMinutes(10),1,2,3,"michel",null);
		try {
			tx.begin();
			pm.makePersistent(f1);
			pm.makePersistent(f2);
			pm.makePersistent(f3);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		FlightDaoImpl fdi=new FlightDaoImpl(pmf);
		List<Flight> lf= fdi.search_flight("CDG",DateTime.now().minusDays(1),DateTime.now().plusDays(1));
		System.out.println(lf.size());
		
	}

}
