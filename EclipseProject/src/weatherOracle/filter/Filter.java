package weatherOracle.filter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;

// Requests forecast data at a given location and set of times,
// and can produce a set of notifications for the user using its rules and this data.
public final class Filter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153920572465553775L;

	private enum Op {
		ALL, ANY
	}

	// op = Must all rules pass, or any of them to make a notification
	private Op op;

	// The rules that make up this filter
	private Set<Rule> rules;

	// the filters name, as specified by and shown to the user
	private String name;

	// the location this filter uses forecast data from
	private Location loc;

	// registers with the ForecastRequirements object what ForecastDatas this
	// instance needs
	public void addRequirements(ForecastRequirements r) {
		r.addLoc(loc);
	}

	// location accessor
	public Location getLocation() {
		return loc;
	}

	/**
	 * 
	 * @param newRule the Rule to add
	 */
	public void addRule(Rule newRule) {
		rules.add(newRule);
	}

	/**
	 * 
	 * @param rule the Rule to remove
	 */
	public boolean removeRule(Rule rule) {
		return rules.remove(rule);
	}

	/**
	 * 
	 * @return a List of Rules
	 */
	public Set<Rule> getRules() {
		return Collections.unmodifiableSet(rules);
	}

	// ForecastData, and filters it, producing a pass/fail boolean
	public boolean apply(ForecastData data) {

		boolean all = op == Op.ALL;

		boolean match = all;
		for (Rule r : rules) {
			boolean m = r.apply(data);
			if (all) {
				match &= m;
			} else {
				match |= m;
			}
		}
		return match;
	}
}
