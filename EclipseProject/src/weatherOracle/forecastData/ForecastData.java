package weatherOracle.forecastData;

import java.util.Date;
import java.util.List;

/**
 * A single data-point of forecast data for the forecast at a given time and location
 * 
 */
public class ForecastData {
	// TODO : include everything, verify units, add constructor, add accessors
	private double temperature_hourly; // in degrees F
	private double temperature_dewPoint; // in degrees F
	private double probabilityOfPrecipitation; // percent
	private double windSpeed_sustained; // mph?
	private double windSpeed_gust; // mph?
	private double windSpeed_direction; // in degrees true
	private double cloudAmount; // in percent
	private double humidity_relative; // in percent
	private double hourly_qpf; // inches 
	
	public enum Rain {
		NONE, chance
	}
	
	public enum Thunder {
		NONE
	}
	
	private Rain rain;
	private Thunder thunder;
	
	// Time
	private int hour;
	private Date day;
	
	public ForecastData(
			int hour,
			Date day,
			double temperature_hourly,
			double temperature_dewPoint,
			double probabilityOfPrecipitation,
			double windSpeed_sustained,
			double windSpeed_gust,
			double windSpeed_direction,
			double cloudAmount,
			double humidity_relative,
			double hourly_qpf,
			Rain rain,
			Thunder thunder){
		this.day=day;
		this.hour=hour;
		this.temperature_hourly=temperature_hourly;
		this.temperature_dewPoint=temperature_dewPoint;
		this.probabilityOfPrecipitation=probabilityOfPrecipitation;
		this.windSpeed_sustained=windSpeed_sustained;
		this.windSpeed_gust=windSpeed_gust;
		this.windSpeed_direction=windSpeed_direction;
		this.cloudAmount=cloudAmount;
		this.humidity_relative=humidity_relative;
		this.hourly_qpf=hourly_qpf;
		this.rain=rain;
		this.thunder=thunder;
	}
	

}
