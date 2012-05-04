package weatherOracle.forecastData;

import java.util.Collections;
import java.util.Set;

import weatherOracle.app.Location;

// Represents a set of required ForecastData points needed by a set of filters
public class ForecastRequirements {
	Set<Location> data;
	public void addLoc(Location loc){
		//TODO
	}
	public Set<Location> getData(){
		return Collections.unmodifiableSet(data);
	}
}
