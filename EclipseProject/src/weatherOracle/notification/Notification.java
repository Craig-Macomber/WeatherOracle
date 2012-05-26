package weatherOracle.notification;

import weatherOracle.filter.Filter;
import weatherOracle.forecastData.ForecastData;
import java.util.List;

/**
 * A Notification for the user, produced by running a filter on a list of
 * ForecastData
 * 
 */
public class Notification implements Comparable<Notification> {
	private String name;
	private Filter filter;
	private List<ForecastData> dataList;

	/**
	 * 
	 * @param name
	 *            =the name of the notification to be displayed
	 * @param dataList
	 *            =the data that passed the filter and triggered the
	 *            notification
	 * @param filter
	 *            =the filter that produced this notification
	 */
	private Notification(String name, List<ForecastData> dataList, Filter filter) {
		this.name = name;
		this.dataList = dataList;
		this.filter = filter;
	}

	/**
	 * @return The list of weather data that passed the filter
	 */
	public List<ForecastData> getWeatherData() {
		return dataList;
	}

	/**
	 * @return The name of the filter
	 */
	public String getName() {
		return name;
	}

	/**
	 * A constructor, but maybe we want to subclass, so use a static factory
	 * 
	 * @param dataList
	 *            the ForecastData that passed filter. Must contain at least one
	 *            entry
	 * @param filter
	 *            the that this notification is supposed to be about
	 * @return a Notification representing the passing of the contents of
	 *         dataList through filter
	 */
	public static Notification make(List<ForecastData> dataList, Filter filter) {
		String name = filter.getName() + ": " + dataList.get(0).getTimeString();
		return new Notification(name, dataList, filter);
	}

	public static Notification makeNoData() {
		return new Notification("no data yet", null, null);
	}
	
	public static Notification makeNoLocationData(Filter filter) {
		return new Notification(filter.getName()+": No data at location", null, filter);
	}

	// sort by time of first passing dataList entry
	public int compareTo(Notification other) {
		if (this.dataList==null) return 1;
		if (other.dataList==null) return -1;
		
		return (int) (this.dataList.get(0).getMillisTime() - other.dataList
				.get(0).getMillisTime());
	}
}
