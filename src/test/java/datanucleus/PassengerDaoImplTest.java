package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import DAO.flightshare.dao.FlightDaoImpl;


public class PassengerDaoImplTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("FlightShare");
		FlightDaoImpl fdi = new FlightDaoImpl(pmf);
		
		
	}

}
