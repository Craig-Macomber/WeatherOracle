package weatherOracle.reader;

import java.util.Map;
import java.util.List;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;

/**
 * A reader interface, so we can have a TestReader, and a NoaaReader
 */
public interface Reader {
	
	/**
	 * Returns a map from location, to all a list of available ForecastData for
	 * that location. The lists are ordered soonest to most distant in the
	 * future, and have one entry for each hour. There will be one entry in the
	 * returned map for each location registered with the ForecastRequirements.
	 * <p>
	 * This method is thread-safe!
	 * 
	 * @return A mapping from locations to a lists of ForecastData objects at
	 *         that location
	 */
	public Map<Location,List<ForecastData>> getData(ForecastRequirements r);
}
