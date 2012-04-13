package weatherOracle.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Filter {
	private enum Op {
		ALL, ANY
	}
	
	private Op op;
	
	private abstract class Rule {
		public abstract Set<TimeRange> apply(TimeRange t);
		public abstract void showUI(); // Placeholder for showing UI, needs some paramaters
	}
	
	private List<Rule> rules;
	public void AddRequirements(ForecastRequirements r){
		
	}
	public List<Notification> Apply(ForcastData data){
		List<Notification> notifications=new ArrayList<Notification>();
		
		
		return notifications;
	}
}

