package weatherOracle.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastDataRange;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.notification.Notification;

// Requests forecast data at a given location and set of times,
// and can produce a set of notifications for the user using its rules and this data.
public final class Filter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153920572465553775L;
	private enum Op {
		ALL, ANY
	}
	
	// op = Must all rules pass, or any of them to make a notification
	private Op op;
	
	// The rules that make up this filter
	private List<Rule> rules;
	
	// the filters name, as specified by and shown to the user
	private String name;
	
	// the location this filter uses forecast data from
	private Location loc;
	
	// registers with the ForecastRequirements object what ForecastDatas this instance needs
	public void AddRequirements(ForecastRequirements r){
		// TODO
	}
	
	// location accessor
	public Location getLocation(){
		return loc;
	}
	
	// takes in ForecastDataRange, and filters it, producing a set of notifications
	// TODO : API subject to changer here
	// perhaps just taking in a ForecastData and returning Boolean would be better
	public List<Notification> Apply(ForecastDataRange data){
		List<Notification> notifications=new ArrayList<Notification>();
		
		boolean all=op==Op.ALL;
		for (ForecastData d:data){
			boolean match=all;
			for (Rule r:rules){
				boolean m=r.apply(d);
				if (all){
					match&=m;
				} else {
					match|=m;
				}
			}
			if (match){
				// ForecastData d passed filter
				// TODO
			}
		}
		
		return notifications;
	}
}

