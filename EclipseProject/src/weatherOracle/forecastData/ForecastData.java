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
	private double temperature_hourly; // in degrees F
	private double temperature_dewPoint; // in degrees F
	private double probabilityOfPrecipitation; // percent
	private double windSpeed_sustained; // mph?
	private double windSpeed_gust; // mph?
	private double windSpeed_direction; // in degrees true
	private double cloudAmount; // in percent
	private double humidity_relative; // in percent
	private double hourly_qpf; // inches 
	
	private enum Rain {
		NONE, chance
	}
	
	private enum Thunder {
		NONE
	}
	
	private Rain rain;
	private Thunder thunder;
	
	// Time
	private int hour;
	private Date day;
}
