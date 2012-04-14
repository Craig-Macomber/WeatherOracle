package weatherOracle.filter;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.app.Notification;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastDataRange;
import weatherOracle.forecastData.ForecastRequirements;


public final class Filter {
	private enum Op {
		ALL, ANY
	}
	
	private Op op;
	
	private List<Rule> rules;
	public void AddRequirements(ForecastRequirements r){
		
	}
	public List<Notification> Apply(ForecastDataRange data){
		List<Notification> notifications=new ArrayList<Notification>();
		
		// TODO
		
		return notifications;
	}
}

