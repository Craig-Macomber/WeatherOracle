package weatherOracle.filter;

import java.io.Serializable;

import weatherOracle.forecastData.ForecastData;

// Used by filters to perform the actual filtering of ForecastData
public interface Rule extends Serializable{
	// Takes a ForecastData, and returns if it passes this Rule instance.
	public abstract Boolean apply(ForecastData data);
	// Used to display the UI for letting the user do any needed config for this rule instance
	public abstract void showUI(); // TODO : Placeholder for showing UI, needs some paramaters
}
