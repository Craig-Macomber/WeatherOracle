package weatherOracle.reader;

import java.util.Map;
import java.util.List;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;

// A reader interface, so we can have a TestReader, and a NoaaReader
public interface Reader {
	// This method is thread-safe!
	public Map<Location,List<ForecastData>> getData(ForecastRequirements r);
}
