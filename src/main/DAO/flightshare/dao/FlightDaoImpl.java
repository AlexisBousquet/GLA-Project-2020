package DAO.flightshare.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.joda.time.DateTime;

import MailSystem.MailSender;

public class FlightDaoImpl implements FlightDAO {
	
	private PersistenceManagerFactory pmf;

	public FlightDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	/*@Param id of the flight, id_passenger id of the passenger currently booking the flight, and the number of seats
	 * This function will add in the list of the passenger and the flight a reservation according to the number of seats ordered.
	 * 
	 * 
	*/
	public void book_flight(long id,long id_passengers, int seats) {
			
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Flight f=pm.getObjectById(Flight.class,id);
			f.setNbremainningseats(f.getNbremainningseats()-seats);
			Passenger p=pm.getObjectById(Passenger.class,id_passengers);
			Reservation reserv=new Reservation(p.getId(),f.getId(),seats,0);
			pm.makePersistent(reserv);
			Pilot pilot=pm.getObjectById(Pilot.class,f.getPilot());
			f.getPassengers().add(reserv);
			p.getList_of_reservations().add(reserv);
			MailSender es=new MailSender();
			es.mailSender(MailSender.createMessageNewPassenger(pilot, f, reserv.id),"Demande de passager",pilot.getMail());
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		
	}
	/*
	 * @Param the airport of departure,the interval of date and the number of seats
	 * This function will retrieve from the database the list of flight wit hthe correct number of seats 
	 * and i between the two dates
	 */
	@SuppressWarnings("unchecked")
	public List<Flight> search_flight(String a_dep, DateTime date_dep,DateTime date_dep2,int seats) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Flight> searched_flight = null;
		List<Flight> detached_searched_flight = new ArrayList<Flight>();
		try {
			tx.begin();
			Query q=pm.newQuery(Flight.class);
			q.declareParameters("org.joda.time.DateTime date1,org.joda.time.DateTime date2,String dept");
			q.setFilter("date > date1 &&  date < date2 && departure == dept && nbremainningseats > 0");
			searched_flight = (List<Flight>) q.execute(date_dep,date_dep2,a_dep);
			System.out.println(searched_flight.iterator().next() + "Bonjour");
			detached_searched_flight = (List<Flight>) pm.detachCopyAll(searched_flight);
			Iterator<Flight> i= detached_searched_flight.iterator();
			while(i.hasNext()) {
				if(i.next().getNbremainningseats()<seats) {
					i.remove();
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached_searched_flight;
	}
	/*@Param the Flight to add,The pilot associated to this flight
	 * This function will add a flight to the database and the list of flight of the pilot
	 * @returns a Response success or failed
	 */
	public Reponse add_flight(Flight flight,long id_pilot) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse r=null;
		try {
			tx.begin();

			
			Pilot p=pm.getObjectById(Pilot.class,id_pilot);
			flight.setPilot(p.getId());
			flight.setDate(flight.getDate().minusHours(2));
			pm.makePersistent(flight);
			p.getList_of_flights().add(flight);
			r=new Reponse(flight.getId(),"Vol ajouté");
			tx.commit();
		}
		 finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return r;
	}

	/*
	 * This function will send a flight from the database according to the id put in argument
	 */
	public Flight getFlightInfo(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Flight f=null;
		Flight f2=null;
		try {
			tx.begin();
			f=pm.getObjectById(Flight.class,id);
			System.out.println(f);
			f2=pm.detachCopy(f);
			System.out.println(f2);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return f2;
		
	}

}

