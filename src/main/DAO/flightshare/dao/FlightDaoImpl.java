package DAO.flightshare.dao;

import java.sql.Date;
import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.joda.time.DateTime;

public class FlightDaoImpl implements FlightDAO {
	
	private PersistenceManagerFactory pmf;

	public FlightDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	
	public void modify_flight_remainingseats(long id, int seats, String passengers) {
			
	
		
	}

	@SuppressWarnings("unchecked")
	public List<Flight> search_flight(String a_dep, DateTime date_dep,DateTime date_dep2) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Flight> searched_flight = null;
		try {
			tx.begin();
			Query q=pm.newQuery(Flight.class);
			q.declareParameters("org.joda.time.DateTime date1,org.joda.time.DateTime date2,String dept");
			//q.declareParameters("String dept");
			q.setFilter("date > date1 &&  date < date2 && arrival == dept && nbremainningseats > 0");
			//q.setFilter("departure == dept");
			searched_flight = (List<Flight>) q.execute(date_dep,date_dep2,a_dep);
			//searched_flight = (List<Flight>) q.execute(a_dep);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return searched_flight;
	}

	public void add_flight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}

