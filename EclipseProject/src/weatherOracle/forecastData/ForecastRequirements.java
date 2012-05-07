package weatherOracle.forecastData;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import weatherOracle.app.Location;

/**
 * Represents a set of requirements for getting ForecastData points needed by a
 * set of filters
 * 
 */
public class ForecastRequirements {
	Set<Location> data;

	public ForecastRequirements() {
		data = new HashSet<Location>();
	}

	/**
	 * 
	 * @param add
	 *            a Location to the requirements
	 */
	public void addLoc(Location loc) {
		data.add(loc);
	}

	/**
	 * 
	 * @return an unmodifiableSet of Locations required
	 */
	public Set<Location> getData() {
		return Collections.unmodifiableSet(data);
	}
}
