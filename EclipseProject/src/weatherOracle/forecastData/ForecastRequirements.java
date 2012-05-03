package weatherOracle.forecastData;

import java.util.Collections;
import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.app.TimeRange;

// Represents a set of required ForecastData points needed by a set of filters
public class ForecastRequirements {
	Map<Location,TimeRange> data;
	public void addTimeRange(Location loc, TimeRange t){
		//TODO
	}
	public Map<Location,TimeRange> getData(){
		return Collections.unmodifiableMap(data);
	}
	protected TimeRange getTimeRange(){
		// TODO
		return null;
	}
}
