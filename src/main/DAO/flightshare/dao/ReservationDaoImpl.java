package DAO.flightshare.dao;

import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import MailSystem.MailSender;

public class ReservationDaoImpl implements ReservationDAO {
	
	private PersistenceManagerFactory pmf;

	public ReservationDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
		/*
		 * retrieve the Reservation associated to the id
		 */
    public Reservation getReservationInfo(long id_res) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Reservation retour=null;
        try {
            tx.begin();
            Reservation r=pm.getObjectById(Reservation.class,id_res);
            retour=pm.detachCopy(r);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println(retour);
        return retour;
    }
	
    /*
     * This function will change the status of the reservation and send an email to the passenger telling him he is accepted
     */
	public void acceptReservation(long id_res) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Reservation r=pm.getObjectById(Reservation.class,id_res);
			Passenger p=pm.getObjectById(Passenger.class,r.passenger_id);
			Flight f=pm.getObjectById(Flight.class,r.flight_id);
			r.setStatus(1);
			System.out.println(r);
			MailSender es=new MailSender();
			es.mailSender(MailSender.createMessageAccept(p, f),"Acceptation de reservation",p.getMail());
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}	
	}
	
	//This function will change the status of the reservation and send an email to the passenger telling him he is refused
	public void refuseReservation(long id_res) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Reservation r=pm.getObjectById(Reservation.class,id_res);
			Passenger p=pm.getObjectById(Passenger.class,r.passenger_id);
			Flight f=pm.getObjectById(Flight.class,r.flight_id);
			Reservation r2=null;
			Iterator<Reservation> i=p.getList_of_reservations().iterator();
			while(i.hasNext()) {
				r2=i.next();
				if(r2.id==id_res) {
					i.remove();
				}
			}
			
			f.getPassengers().remove(r);
			MailSender es=new MailSender();
			es.mailSender(MailSender.createMessageRefuse(p, f),"Refus de reservation",p.getMail());
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

}
