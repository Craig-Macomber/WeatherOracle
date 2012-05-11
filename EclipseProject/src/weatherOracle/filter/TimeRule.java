package weatherOracle.filter;

import weatherOracle.forecastData.ForecastData;

/**
 * A Rule subclass that keeps track of a day of the week.
 *
 */
public class TimeRule implements Rule {
	private static final long serialVersionUID = -8095730510021338560L;
	
	public static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	private String day;
	
	/**
	 * Default Constructor
	 * @param day the day of the week
	 */
	public TimeRule(String day) {
		if (day == null) {
			throw new IllegalArgumentException();
		} else {
			this.day = day;
		}
	}

	public Boolean apply(ForecastData data) {
		// TODO Auto-generated method stub
		return null;
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
}
