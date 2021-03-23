package com.example.datanucleus.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class FlightDaoImpl implements FlightDAO {
	
	private PersistenceManagerFactory pmf;

	public FlightDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	
	public void modify_flight_remainingseats(long id, int seats, String passengers) {
			
		
	}

	@SuppressWarnings("unchecked")
	public List<Flight> search_flight(String a_dep, LocalDateTime date_dep, int places) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Flight> searched_flight = null;
		try {
			tx.begin();
			Query q=pm.newQuery(Flight.class);
			q.declareParameters("java.sql.Date date,java.sql.Time time1,java.sql.Time time2");
			q.setFilter("date == date && time1 >= time && time <=time2 ");
			searched_flight = (List<Flight>) q.execute(Date.valueOf(date_dep.toLocalDate()),Time.valueOf(date_dep.toLocalTime().minusMinutes(30)),Time.valueOf(date_dep.toLocalTime().plusMinutes(30)));
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

