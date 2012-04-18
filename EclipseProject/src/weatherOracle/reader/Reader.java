package weatherOracle.reader;

import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastDataRange;
import weatherOracle.forecastData.ForecastRequirements;

// A reader interface, so we can have a TestReader, and a NoaaReader
public interface Reader {
	// This method is thread-safe!
	public Map<Location,ForecastDataRange> getData(ForecastRequirements r);
}
