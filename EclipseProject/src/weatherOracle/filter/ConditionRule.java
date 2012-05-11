package weatherOracle.filter;

import android.util.Pair;
import weatherOracle.forecastData.ForecastData;

/**
 * A Rule subclass that keeps track of a single condition and the min and max values associated with
 * that Rule.
 *
 */
public class ConditionRule implements Rule {
	private static final long serialVersionUID = 2695287363381416690L;
	
	// All possible conditions
	public static final String[] conditions = new String[]{"Temperature", "Dewpoint", "Heat Index", "Wind", "Cloud Cover",
													"Percipitation Percent", "Humidity", "Thunder", "Rain"};

	private String condition;
	private int min;
	private int max;
	
	/**
	 * Default Constructor
	 * @param condition the condition of the Rule
	 * @param min the minimum value for that condition
	 * @param max the maximum value for that condition
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

	public Boolean apply(ForecastData data) {
		
		// TODO Auto-generated method stub
		return null;
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

}
