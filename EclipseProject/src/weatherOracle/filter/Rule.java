package weatherOracle.filter;

import weatherOracle.forcastData.ForcastData;

abstract class Rule {
		public abstract Boolean apply(ForcastData data);
		public abstract void showUI(); // Placeholder for showing UI, needs some paramaters
}
