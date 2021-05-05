package DAO.flightshare.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;



public class PilotDaoImpl implements PilotDAO {

	private PersistenceManagerFactory pmf;

	public PilotDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
	
	/*
	 * add a new pilot to the data base
	 */
	@SuppressWarnings("unchecked")
	public Reponse add_new_Pilot(Pilot p) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String username=p.getMail();
		Reponse retour=null;
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			q.declareParameters("String user");
			q.setFilter("mail == user");
			List<Pilot> user = (List<Pilot>) q.execute(username);
			if(user.isEmpty()) {
				pm.makePersistent(p);
				retour=new Reponse(p.getId(),"succes");
			}
			else {
				retour=new Reponse(0,"Mail deja utilisé");
			}
			
			System.out.println(retour);
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
	 * check if the username and password put in arguments correspond to a pilot in the database
	 */
	@SuppressWarnings("unchecked")
	public Reponse login_Pilot(String username, String password) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse retour;
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			q.declareParameters("String user,String pass");
			q.setFilter("mail == user && password == pass");

			List<Pilot> user = (List<Pilot>) q.execute(username,password);
			if(user.isEmpty()) {
				retour=new Reponse(0,"Utilisateur inexistant");
			}
			else {
				retour=new Reponse(user.iterator().next().getId(),"succes");
						
			}
			System.out.println(retour);
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
	 * Retrieve from the database the pilot assign to this id
	 */
	public Pilot getPilotById(long id) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Pilot p=null;
		Pilot p2=null;
		try {
			tx.begin();
			p=pm.getObjectById(Pilot.class,id);
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
	 * Modify the pilot assiociated to this id by the pilot put in arguments
	 */
	@SuppressWarnings("unchecked")
	public Reponse modifyPilot(long id, Pilot p) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse retour= null;
		try {
			tx.begin();
			Pilot p2=pm.getObjectById(Pilot.class,id);
			Query q = pm.newQuery(Pilot.class);
			q.declareParameters("String user");
			q.setFilter("mail == user");
			List<Pilot> user = (List<Pilot>) q.execute(p.getMail());
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
