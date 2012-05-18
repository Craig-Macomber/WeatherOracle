package weatherOracle.app;

import java.io.Serializable;

import android.content.Context;
import android.location.LocationManager;

/**
 * Stores a serializable location by latitude and longitude.
 * <p>
 * Has a proper hash and equals so it can be use in set and map keys.
 *
 */
public class Location implements Serializable{
	public double lat;
	public double lon;

	/**
	 * 
	 * @param lat=latitude of new Location
	 * @param lon=longitude of new Location
	 * Constructor creates a new Location object from a specified lat/lon location
	 */
	public Location(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * 
	 * @param loc=android location structure to create a Location object from
	 * Constructor creates a new Location object from an android location
	 */
	public Location(android.location.Location loc) {
		this(loc.getLatitude(), loc.getLongitude());
	}

	/**
	 * @return a hash of the object to assist in equals() calls
	 */
	public int hashCode() {
		return new Double(lat).hashCode() ^ new Double(lon).hashCode();
	}

	/**
	 * @return true if this==aThat, false otherwise
	 */
	public boolean equals(Object aThat) {
		if (this == aThat)
			return true;
		if (!(aThat instanceof Location))
			return false;
		Location that = (Location) aThat;
		return this.lat == that.lat && this.lon == that.lon;
	}
	
	private static android.location.Location myCurrentLocation;
	
	/**
	 * 
	 * @return the user's current Lat/Lon location as recorded by the phone
	 */
	public static Location getCurrentLocation()
	{
		return new Location(myCurrentLocation.getLatitude(),myCurrentLocation.getLongitude());
	}
	/**
	 * 
	 * @param loc=new location to assign as the phone's current location
	 * @modify the user's current location
	 */
	public static void setCurrentLocation(android.location.Location loc)
	{
		myCurrentLocation=loc;
	}
}
