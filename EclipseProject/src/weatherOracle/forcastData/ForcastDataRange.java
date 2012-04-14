package weatherOracle.forcastData;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.app.TimeRange;

public class ForcastDataRange {
	private final TimeRange timeRange;
	private final List<ForcastData> data;
	public ForcastDataRange(ForecastRequirements req){
		assert(req!=null);
		timeRange=req.getTimeRange();
		assert(timeRange!=null);
		data=new ArrayList<ForcastData>(getCount());
		// TODO : Fill DATA
	}
	public int getCount(){
		return timeRange.getCount();
	}
}
