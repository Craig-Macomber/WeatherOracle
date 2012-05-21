package weatherOracle.filter;

import java.util.HashMap;
import java.util.Map;

import android.util.Pair;
import weatherOracle.forecastData.ForecastData;

/**
 * A Rule subclass that keeps track of a single condition and the min and max values associated with
 * that Rule.
 */
public class ConditionRule implements Rule {
	private static final long serialVersionUID = 2695287363381416690L;
	
	/**
	 * All possible conditions for ConditionRules
	 */
	public static final String[] conditions = new String[]{"Temperature", "Dewpoint", "Heat Index", "Wind", "Cloud Cover",
													"Precipitation Percent", "Humidity", "Thunder", "Rain"};

	/**
	 * The units for the different weather conditions
	 */
	public static final Map<String, String> units = new HashMap<String, String>() {
		
		private static final long serialVersionUID = -8447558203204357059L;
		{
		put("Temperature", "\u00b0F");
		put("Dewpoint", "\u00b0F");
		put("Heat Index", "u00b0F");
		put("Wind","mph");
		put("Cloud Cover", "%");
		put("Precipitation Percent", "%");
		put("Humidity", "%");
		}
	};
	
	/**
	 * The condition of this ConditionRule
	 */
	private String condition;
	
	/**
	 * The minimum value for this ConditionRule
	 */
	private int min;
	
	/**
	 * the maximum value for this ConditionRule
	 */
	private int max;
	
	/**
	 * Default Constructor
	 * @param condition the condition of the Rule
	 * @param min the minimum value for that condition
	 * @param max the maximum value for that condition
	 * @throws IllegalArgumentException if the condition is null or
	 * 			the min is greater than the max
	 */
	public ConditionRule(String condition, int min, int max) {
		if (condition == null || min > max) {
			throw new IllegalArgumentException("Illegal parameters for ConditionRule!");
		} else {
			this.condition = condition;
			this.min = min;
			this.max = max;
		}
	}
	
	/**
	 * Gives the condition associated with this Rule
	 * 
	 * @return the condition String for this Rule
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Gives the min and max values for this Rule
	 * 
	 * @return a Pair with the min and max
	 */
	public Pair<Integer, Integer> getMinMax() {
		return new Pair<Integer, Integer>(min, max);
	}

	/**
	 * Checks if this ConditionRule is satisfied by the ForecastData
	 * @param data the ForecastData to compare to the Rule
	 */
	public Boolean apply(ForecastData data) {
		if (condition.equals(conditions[0])) {
			// Temperature
			double temp = data.getTemperature();
			return temp >= min && temp <= max;
		} else if (condition.equals(conditions[1])) {
			// Dew point
			double temp = data.getDewpoint();
			return temp >= min && temp <= max;
		} else if (condition.equals(conditions[2])) {
			// Heat Index
			// TODO
		} else if (condition.equals(conditions[3])) {
			// Wind
			// TODO
		} else if (condition.equals(conditions[4])) {
			// Cloud Cover
			double cover = data.getCloudCover();
			return cover >= min && cover <= max;
		} else if (condition.equals(conditions[5])) {
			// Precipitation Percent
			double percent = data.getProbPrecipitation();
			return percent >= min && percent <= max;
		} else if (condition.equals(conditions[6])) {
			// Humidity
			double humidity = data.getHumidity();
			return humidity >= min && humidity <= max;
		} else if (condition.equals(conditions[7])) {
			// Thunder
			// TODO
		} else if (condition.equals(conditions[8])) {
			// Rain
			// TODO
		} 
		return Boolean.FALSE;
	}
	
	/**
	 * Checks if this ConditionRule is satisfied by the ForecastData
	 * @return the unit type for the given weather condition
	 */
	public static String getUnits(String type) {
		return units.get(type);
	}
	
	// Generated hashcode method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condition.hashCode();
		result = prime * result + max;
		result = prime * result + min;
		return result;
	}

	// Generated equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConditionRule other = (ConditionRule) obj;
		if (!condition.equals(other.condition))
			return false;
		if (max != other.max)
			return false;
		if (min != other.min)
			return false;
		return true;
	}

	/**
	 * CompareTo method
	 * @param otherRule the other Rule to compare with
	 */
	public int compareTo(Rule otherRule) {
		if (getClass() == otherRule.getClass()) {
			ConditionRule other = (ConditionRule) otherRule;
			if (!condition.equals(other.getCondition())) {
				return condition.compareTo(other.getCondition());
			} else {
				Pair<Integer, Integer> otherP = other.getMinMax();
				if (min != otherP.first) {
					return ((Integer)min).compareTo(otherP.first);
				} else if (max != otherP.second) {
					return ((Integer)max).compareTo(otherP.second);
				}
				return 0;
			}
		} else {
			return 1;
		}
	}
}