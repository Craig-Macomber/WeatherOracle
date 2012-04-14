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
	
	private abstract class Rule {
		public abstract Boolean apply(ForecastData data);
		public abstract void showUI(); // Placeholder for showing UI, needs some paramaters
	}
	
	private List<Rule> rules;
	public void AddRequirements(ForecastRequirements r){
		
	}
	public List<Notification> Apply(ForecastDataRange data){
		List<Notification> notifications=new ArrayList<Notification>();
		
		
		return notifications;
	}
}

