package weatherOracle.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastDataRange;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.notification.Notification;


public final class Filter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153920572465553775L;
	private enum Op {
		ALL, ANY
	}
	
	private Op op;
	
	private List<Rule> rules;
	private String name;
	private Location loc;
	
	public void AddRequirements(ForecastRequirements r){
		// TODO
	}
	
	public Location getLocation(){
		return loc;
	}
	
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

