package weatherOracle.notification;

import weatherOracle.filter.Filter;
import weatherOracle.forecastData.ForecastData;
import java.util.List;

/**
 * A Notification for the user, produced by running a filter on a list of
 * ForecastData
 * 
 */
public class Notification {
	/**
	 * A constructor, but maybe we want to subclass, so use a static factory
	 * 
	 * @param dataList
	 *            the ForecastData that passed filter
	 * @param filter
	 *            the that this notification is supposed to be about
	 * @return a Notification representing the passing of the contents of
	 *         dataList through filter
	 */
	public static Notification make(List<ForecastData> dataList, Filter filter) {
		// TODO
		// MAKE Notifications here!
		return null;
	}
}
