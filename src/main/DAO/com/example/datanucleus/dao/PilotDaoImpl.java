package com.example.datanucleus.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.example.datanucleus.dao.PassengerDAO.Reponse;

public class PilotDaoImpl implements PilotDAO {

	private PersistenceManagerFactory pmf;

	public PilotDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

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
			q.setFilter("username == user");
			List<Passenger> user = (List<Passenger>) q.execute(username);
			if(user.isEmpty()) {
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

	public Reponse login_Pilot(String username, String password) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reponse retour;
		try {
			tx.begin();
			Query q = pm.newQuery(Pilot.class);
			q.declareParameters("String user,String pass");
			q.setFilter("username == user && password == pass");

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

}
