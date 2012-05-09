package weatherOracle.filter;

import weatherOracle.forecastData.ForecastData;

public class ConditionRule implements Rule {
	private static final long serialVersionUID = 2695287363381416690L;
	
	// All possible conditions
	public static String[] conditions = new String[]{"TEMPERATURE", "DEWPOINT", "HEAT_INDEX", "WIND", "CLOUD_COVER",
													"PERCIPITATION_PERCENT", "HUMIDITIY", "THUNDER", "RAIN"};

	// NEED SOME FIELDS HERE
	private String condition;
	private int min;
	private int max;
	
	public ConditionRule(String condition, int min, int max) {
		if (condition == null || min > max) {
			throw new IllegalArgumentException("UI you suck!");
		}
		this.condition = condition;
		this.min = min;
		this.max = max;
	}

	public Boolean apply(ForecastData data) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
