package weatherOracle.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.notification.Notification;
import weatherOracle.notification.NotificationStore;
import weatherOracle.reader.Reader;
import android.util.Log;
import android.util.Pair;

/**
 * Generates a set of notifications for the current filters, and sends them to
 * the NotificationStore
 */
class UpdateTask implements Runnable {
	private Reader reader;

	public UpdateTask(Reader reader) {
		super();
		this.reader = reader;
	}

	public void run() {
		Log.d("UpdateTask", "run");

		Pair<List<Filter>, Integer> pair = FilterStore.getFilters();
		int filterVersionNumber = pair.second;
		ForecastRequirements req = new ForecastRequirements();
		for (Filter f : pair.first) {
			f.addRequirements(req);
		}
		Map<Location, List<ForecastData>> m = reader.getData(req);

		List<Notification> notifications = new ArrayList<Notification>();

		for (Filter f : pair.first) {
			List<ForecastData> passed = new ArrayList<ForecastData>();
			for (ForecastData d : m.get(f.getLocation())) {
				if (f.apply(d)) {
					passed.add(d);
				}
			}
			if(!passed.isEmpty())
				notifications.add(Notification.make(passed, f));
		}

		NotificationStore.update(notifications, filterVersionNumber);
	}
}
