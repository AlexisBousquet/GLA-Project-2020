package MailSystem;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;

import DAO.flightshare.dao.Flight;
import DAO.flightshare.dao.Passenger;
import DAO.flightshare.dao.Pilot;
import DAO.flightshare.dao.Reservation;

public class MailSender {
	
	public static class ThreadMail implements Runnable{
		
		int delay;
		public ThreadMail(int d){
			this.delay=d;
		}

		public void run() {
			System.out.println("RUN=====");
			PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("FlightShare");
			PersistenceManager pm=pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				DateTime dt2=DateTime.now().plusDays(1).plusSeconds(delay);
				DateTime dt1=DateTime.now().plusDays(1);
				Query q=pm.newQuery(Flight.class);
				q.declareParameters("org.joda.time.DateTime date1,org.joda.time.DateTime date2,");
				q.setFilter("date > date1 &&  date < date2");
				@SuppressWarnings("unchecked")
				List<Flight> searched_flight = (List<Flight>) q.execute(dt1,dt2);
				Iterator<Flight> i= searched_flight.iterator();
				
				while(i.hasNext()) {
					Flight f=i.next();
					Pilot p=pm.getObjectById(Pilot.class,f.getPilot());
					MailSender es=new MailSender();
					es.mailSender(MailSender.createMessageReminderPilot(p, f),"Rappel Vol",p.getMail());
					System.out.println("Mail Sent to Pilot====");
					
					for(Reservation r : f.getPassengers()) {
						Passenger reminded_passenger = pm.getObjectById(Passenger.class,r.getPassenger_id());
						es.mailSender(MailSender.createMessageReminderPassenger(reminded_passenger, f),"Rappel vol",reminded_passenger.getMail());
						System.out.println("Mail Sent to Passenger====");
					}
					
					
				}
				
				tx.commit();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
			
		}
		
	}
	
	public void mailSender(String message, String subject,String adressee) {
		Properties props = new Properties();
        props.put("mail.smtp.user", "FlightShare@outlook.com");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        try
        {
        Authenticator auth = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("FlightShare@outlook.com", "ShareFlight123");
            }
          };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(message);
        msg.setSubject(subject);
        msg.setFrom(new InternetAddress("FlightShare@outlook.com"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(adressee));
        Transport.send(msg);

        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
	}
	
	public static String createMessageAccept(Passenger p,Flight f) {
		
		String s = "Bonjour Monsieur/Madame "+p.getLastName()+",\n\nNous somme ravie de vous annoncer que le vol de "+f.getDeparture()+" à "+f.getArrival()+" à été accepté."+"\n\nNous vous souhaiton un bon voyage \nL'equipe de FlightShare";	
		return s;
	}
	
	public static String createMessageRefuse(Passenger p, Flight f) {
		String s = "Bonjour Monsieur/Madame "+p.getLastName()+",\n\nNous somme désolé de vous annoncer que le vol de "+f.getDeparture()+" à "+f.getArrival()+" à été refusé."+"\n\nVeuillez nous excuser pour le désagrement. \nL'equipe de FlightShare";
		return s;
	}
	
	public static String createMessageNewPassenger(Pilot p, Flight f,long id_res) {
		String s = "Bonjour Monsieur/Madame "+p.getLastName()+",\n\nVous avez reçu une nouvelle demande de passager cliquez sur ce lien pour plus d'information http://localhost:8080/Confirm_Refuse.html?id="+id_res+"\n\nBien a vous \nL'equipe de FlightShare";
		return s;
	}
	
	public static String createMessageReminderPilot(Pilot p,Flight f) {
		String s = "Bonjour Monsieur/Madame "+p.getLastName()+",\n\nNous vous rapellons que demain vous un avez un vol de "+f.getDeparture()+" à "+f.getArrival()+"\n\nBon voyage\nL'equipe de FlightShare";
		return s;
	}
	
	public static String createMessageReminderPassenger(Passenger p,Flight f) {
		String s = "Bonjour Monsieur/Madame "+p.getLastName()+",\n\nNous vous rapellons que demain vous un avez un vol de "+f.getDeparture()+" à "+f.getArrival()+"\n\nBon voyage\nL'equipe de FlightShare";
		return s;
	}
	
	
	
	
	
	
}
