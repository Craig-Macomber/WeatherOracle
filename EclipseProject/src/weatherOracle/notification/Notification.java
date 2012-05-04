package weatherOracle.notification;

import weatherOracle.filter.Filter;
import weatherOracle.forecastData.ForecastData;
import java.util.List;

// A Notification for the user, produced by running a filter on ForecastData
public class Notification {
	// A constructor, but maybe we want to subclass, so use a static factory
	public static Notification make(List<ForecastData> dataList, Filter filter){
		// TODO
		// MAKE Notifications here!
		return null;
	}
}
