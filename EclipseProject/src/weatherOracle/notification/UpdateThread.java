package weatherOracle.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import weatherOracle.forecastData.ForecastDataRange;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.reader.Reader;
import android.util.Pair;

public class UpdateThread extends Thread {
    private Reader reader;
	public UpdateThread(Reader reader){
    	super();
    	this.reader=reader;
    }
	
	public void run() {
    	Pair<List<Filter>,Integer> pair=FilterStore.getFilters();
    	int filterVersionNumber=pair.second;
    	ForecastRequirements req=new ForecastRequirements();
    	for (Filter f:pair.first){
    		f.AddRequirements(req);
    	}
    	Map<Location,ForecastDataRange> m=reader.getData(req);
    	List<Notification> notifications=new ArrayList<Notification>();
    	for (Filter f:pair.first){
    		notifications.addAll(f.Apply(m.get(f.getLocation())));
    	}
    	NotificationStore.update(notifications, filterVersionNumber);
    }
}
