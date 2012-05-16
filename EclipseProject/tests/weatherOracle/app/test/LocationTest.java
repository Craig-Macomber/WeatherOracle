package weatherOracle.app.test;

import weatherOracle.app.Location;
import android.test.AndroidTestCase;

public class LocationTest extends AndroidTestCase {
	public void testCurrentLocation() {
		Location l=Location.getCurrentLocation(); // Seems to crash - TODO : make it pass
		assertNotNull(l); 
		Location l2=Location.getCurrentLocation();
		assertTrue(l.equals(l2));
	}
	public void testCompare() {
		Location l=new Location(12.3,45.6);
		Location l2=new Location(12.3,45.6);
		Location l3=new Location(12.3,45.7);
		Location l4=new Location(12.4,45.7);
		assertEquals(l,l2);
		assertEquals(l,l);
		assertFalse(l.equals(l3));
		assertFalse(l.equals(l4));
	}
}
