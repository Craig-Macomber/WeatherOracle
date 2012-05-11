package weatherOracle.filter;

import java.io.Serializable;

import weatherOracle.forecastData.ForecastData;

/**
 * A Rule object stores restrictions about conditions for the users' filters.
 *
 */
public interface Rule extends Serializable{
	/**
	 * Checks if the Rule applies to the given ForecastData
	 * @param data the ForecastData object to check with
	 * @return true if this Rule is satisfied by the ForecastData
	 */
	public abstract Boolean apply(ForecastData data);
}
