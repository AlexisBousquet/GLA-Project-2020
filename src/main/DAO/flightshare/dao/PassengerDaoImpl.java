package DAO.flightshare.dao;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class PassengerDaoImpl implements PassengerDAO {
	
	private PersistenceManagerFactory pmf;

	public PassengerDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	/*
	 * This function will add to the database a passenger
	 */
	public Reponse add_new_Passenger(Passenger p) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String username=p.getMail();
		Reponse retour=null;
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String user");
			q.setFilter("mail == user");
			List<Passenger> user = (List<Passenger>) q.execute(username);
			if(user.isEmpty()) {
				p.setList_of_reservations(new LinkedList<Reservation>());
				pm.makePersistent(p);
				retour=new Reponse(p.getId(),"succes");
			}
			else {
				retour=new Reponse(0,"Mail deja utilisé");
			}
			

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return retour;
		
	}
	/*
	 * This function test if the user username and password correspond to a passenger in the database
	 */
	@SuppressWarnings("unchecked")
	public Reponse login_passenger(String username, String password) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse retour;
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String user,String pass");
			q.setFilter("mail == user && password == pass");

			List<Passenger> user = (List<Passenger>) q.execute(username,password);
			if(user.isEmpty()) {
				retour=new Reponse(0,"Utilisateur inexistant");
			}
			else {
				retour=new Reponse(user.iterator().next().getId(),"succes");
						
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return retour;
	}
	/*
	 * this function will send a Passenger according to the id put in argument
	 */
	public Passenger getPassengerById(long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Passenger p=null;
		Passenger p2=null;
		try {
			tx.begin();
			p=pm.getObjectById(Passenger.class,id);
			System.out.println(p);
			p2=pm.detachCopy(p);
			System.out.println(p2);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return p2;
		
	}
	/*
	 * @Param the id of the modified passenger, and the new passenger
	 * This function will modify the passenger linked to the id wby the the one put in arguments
	 */
	@SuppressWarnings("unchecked")
	public Reponse modifyPassenger(long id, Passenger p) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse retour= null;
		try {
			tx.begin();
			Passenger p2=pm.getObjectById(Passenger.class,id);
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String user");
			q.setFilter("mail == user");
			List<Passenger> user = (List<Passenger>) q.execute(p.getMail());
			if(user.isEmpty() || p2.getMail().equals(p.getMail())) {
				p2.copy(p);
				retour=new Reponse(p.getId(),"succes");
			}
			else {
				retour=new Reponse(0,"Mail deja utilisé");
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return retour;
	}
	
	
}
