package weatherOracle.app;

public class Location{
	public double lat;
	public double lon;
	public Location(double lat, double lon){
		this.lat=lat;
		this.lon=lon;
	}
	public Location(android.location.Location loc){
		this(loc.getLatitude(),loc.getLongitude());
	}
	public int hashCode(){
		return new Double(lat).hashCode()^new Double(lon).hashCode();
	}
	public boolean equals(Object aThat){
		  if ( this == aThat ) return true;
		  if ( !(aThat instanceof Location) ) return false;
		  Location that = (Location)aThat;
		  return this.lat==that.lat&&this.lon==that.lon;
		}
}
