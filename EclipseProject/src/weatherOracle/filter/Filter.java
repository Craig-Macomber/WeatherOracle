package weatherOracle.filter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;

/**
 * Requests ForecastDatas at a given location, and filters them using its rules.
 * 
 */
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

	/**
	 * registers with the ForecastRequirements object what ForecastDatas this
	 * instance needs. This makes sure all the needed data is fetched by the
	 * Reader that is producing ForecastDatas. This architecture is mainly to
	 * allow future changes. Perhaps more than just the location will be needed
	 * at some point.
	 * 
	 * @param r
	 *            the ForecastRequirements to registers with
	 */
	public void addRequirements(ForecastRequirements r) {
		r.addLoc(loc);
	}

	
	public Filter(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the location associated with this filter
	 */
	public Location getLocation() {
		return loc;
	}

	/**
	 * 
	 * @param newRule
	 *            the Rule to add
	 */
	public void addRule(Rule newRule) {
		rules.add(newRule);
	}

	/**
	 * 
	 * @param rule
	 *            the Rule to remove
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

	/**
	 * filters ForecastData, producing a pass/fail boolean.
	 * 
	 * @param data
	 *            the ForecastData to be filtered
	 * @return did data pass this filter? true=pass, false=fail
	 */
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
