package weatherOracle.forecastData;

import java.util.Collections;
import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.app.TimeRange;


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
