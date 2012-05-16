package weatherOracle.filter;

import weatherOracle.forecastData.ForecastData;

/**
 * A Rule subclass that keeps track of a day of the week.
 */
public class TimeRule implements Rule {
	private static final long serialVersionUID = -8095730510021338560L;
	
	/**
	 * All possible days for this TimeRule
	 */
	public static final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	/**
	 * The current day for this TimeRule
	 */
	private String day;
	
	/**
	 * Default Constructor
	 * @param day the day of the week
	 * @throws IllegalArgumentException if the day is null
	 */
	public TimeRule(String day) {
		if (day == null) {
			throw new IllegalArgumentException();
		} else {
			this.day = day;
		}
	}
	
	/**
	 * Returns the day associated with this TimeRule
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	
	/**
	 * Checks if this TimeRule is satisfied by the ForecastData
	 * @param data the ForecastData to compare to the Rule
	 */	
	public Boolean apply(ForecastData data) {
		int dayOfWeek = data.getDayOfWeek();
		return day.equals(days[dayOfWeek - 1]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeRule other = (TimeRule) obj;
		if (!day.equals(other.day))
			return false;
		return true;
	}
	
	/**
	 * CompareTo method
	 * @param otherRule the other Rule to compare with
	 */
	public int compareTo(Rule otherRule) {
		if (getClass() == otherRule.getClass()) {
			TimeRule other = (TimeRule) otherRule;
			
			return indexOfDay(day) - indexOfDay(other.getDay()); 
		} else {
			return -1;
		}
	}
	
	// Computes the index of the day
	private int indexOfDay(String day) {
		for (int i = 0; i < days.length; i++) {
			if (days[i].equals(day)) {
				return i;
			}
		}
		return -1;
	}
}
