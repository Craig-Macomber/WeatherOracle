package weatherOracle.filter;

import weatherOracle.forecastData.ForecastData;

abstract class Rule {
	public abstract Boolean apply(ForecastData data);
	public abstract void showUI(); // TODO : Placeholder for showing UI, needs some paramaters
}
