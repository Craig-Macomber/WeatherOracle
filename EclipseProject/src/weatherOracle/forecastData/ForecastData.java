package weatherOracle.forecastData;

import java.util.Date;

/**
 * A single data-point of forecast data for the forecast at a given time and location
 * 
 */
public class ForecastData {
	// TODO : include everything, verify units, add constructor, add accessors
	private double windSpeed; // in m/s
	private double windDirection; // in degrees from X
	private double temperature; // in degrees C  
	private int hour;
	private Date day;
}
