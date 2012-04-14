package weatherOracle.forecastData;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.app.TimeRange;

public class ForecastDataRange {
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
}
