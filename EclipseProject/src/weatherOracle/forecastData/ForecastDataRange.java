package weatherOracle.forecastData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import weatherOracle.app.TimeRange;

// A set of ForecastData objects over a span of time at a given location
public class ForecastDataRange implements Iterable<ForecastData>{
	private final TimeRange timeRange;
	private final List<ForecastData> data;
	public ForecastDataRange(ForecastRequirements req){
		assert(req!=null);
		timeRange=req.getTimeRange();
		assert(timeRange!=null);
		data=new ArrayList<ForecastData>(getCount());
		// TODO : Fill DATA
	}
	public int getCount(){
		return timeRange.getCount();
	}
	public Iterator<ForecastData> iterator() {
		return data.iterator();
	}
}
