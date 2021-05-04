package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.joda.time.DateTime;
import org.junit.Test;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.Passenger;
import DAO.flightshare.dao.Pilot;
import DAO.flightshare.dao.PilotDaoImpl;

public class PilotDaoImplTest {

	@Test
	public void GetPilotByIdtest() {
		Pilot p = new Pilot("Théau","Eloy","abc","novice","quelques vols","a@b",4);
		Passenger pa = new Passenger("a","a","a","a");
		Flight f1=new Flight("CDG","NRT",DateTime.now(),1,2,3,"michel",1);
		
		PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("FlightShare");
		PersistenceManager pm=pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(p);
			pm.makePersistent(pa);
			pm.makePersistent(f1);
			p.getList_of_flights().add(f1);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		pm=pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			Pilot pilot =pm.getObjectById(Pilot.class,1);
			System.out.println(pilot);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		PilotDaoImpl pdi=new PilotDaoImpl(pmf);
		Pilot p4=pdi.getPilotById(1);
		System.out.println(p4);
	}
	
	@Test
	@SuppressWarnings("unused")
	public void Idtest() {
		PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("FlightShare");
		
		PersistenceManager pm=pmf.getPersistenceManager();
	}

}
