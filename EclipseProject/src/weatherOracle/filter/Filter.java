package weatherOracle.filter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;

/**
 * Requests ForecastDatas at a given location, and filters them using its rules.
 * 
 */
public final class Filter implements Serializable {
	private static final long serialVersionUID = -1153920572465553775L;

	private enum Op {
		ALL, ANY
	}

	/**
	 * If all or any of the Rules must pass to make a notification
	 */
	private Op op;

	/**
	 * The Set of all Rules in this Filter
	 */
	private Set<Rule> rules;

	/**
	 * The name of this Filter
	 */
	private String name;

	/**
	 * The Location of this FIlter
	 */
	private Location loc;

	/**
	 * Default Constructor
	 * @param name
	 */
	public Filter(String name) {
		this.name = name;
		this.rules = new TreeSet<Rule>();
		this.op = Op.ALL;
	}
	
	/**
	 * Registers with the ForecastRequirements object what ForecastDatas this
	 * instance needs. This makes sure all the needed data is fetched by the
	 * Reader that is producing ForecastDatas. This architecture is mainly to
	 * allow future changes. Perhaps more than just the location will be needed
	 * at some point.
	 * 
	 * @param r
	 *            the ForecastRequirements to register with
	 */
	public void addRequirements(ForecastRequirements r) {
		r.addLoc(loc);
	}

	/**
	 * Returns the Location of this Filter
	 * @return the location associated with this filter
	 */
	public Location getLocation() {
		return loc;
	}
	
	/**
	 * Returns the name of this Filter
	 * @return the name associated with this filter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this Filter
	 * @param name
	 * 			the name that this Filter will be given
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Adds a Rule to this Filter
	 * @param newRule
	 *            the Rule to add
	 */
	public void addRule(Rule newRule) {
		rules.add(newRule);
	}

	/**
	 * Removes a Rule from this Filter
	 * @param rule
	 *            the Rule to remove
	 */
	public boolean removeRule(Rule rule) {
		return rules.remove(rule);
	}

	/**
	 * Return all Rules in the Filter
	 * @return a List of Rules
	 */
	public Set<Rule> getRules() {
		return Collections.unmodifiableSet(rules);
	}
	
	/**
	 * Return all ConditionRules in the Filter
	 * @return a Set of all ConditionRules
	 */
	public Set<ConditionRule> getConditionRules() {
		Set<ConditionRule> conditionRules = new TreeSet<ConditionRule>();
		
		for (Rule r : rules) {
			if (r.getClass() == ConditionRule.class) {
				conditionRules.add((ConditionRule) r);
			}
		}
		return conditionRules;
	}
	
	/**
	 * Returns all TimeRules in the Filter
	 * @return a Set of all TimeRules
	 */
	public Set<TimeRule> getTimeRules() {
		Set<TimeRule> timeRules = new TreeSet<TimeRule>();
		
		for (Rule r : rules) {
			if (r.getClass() == TimeRule.class) {
				timeRules.add((TimeRule) r);
			}
		}
		return timeRules;
	}

	/**
	 * Remove all condition rules from "rules"
	 * 
	 */
	public void removeConditionRules() {
		for (Rule r : rules) {
			if (r.getClass() == ConditionRule.class) {
				removeRule((ConditionRule) r);
			}
		}
	}
	
	/**
	 * Remove all time rules from "rules"
	 * 
	 */
	public void removeTimeRules() {
		for (Rule r : rules) {
			if (r.getClass() == TimeRule.class) {
				removeRule((TimeRule) r);
			}
		}
	}
	
	/**
	 * Add the given set of condition rules to this filter
	 * 
	 * @param rules
	 * 			a set of condition rules to be added to this Filter
	 */
	public void addSetOfConditionRules(Set<ConditionRule> rules) {
		for (Rule r : rules){
			addRule(r);
		}
	}
	
	/**
	 * Add the given set of time rules to this filter
	 * 
	 * @param rules
	 * 			a set of time rules to be added to this Filter
	 */
	public void addSetOfTimeRules(Set<TimeRule> rules) {
		for (Rule r : rules){
			addRule(r);
		}
	}
	
	
	
	/**
	 * Filters ForecastData, producing a pass/fail boolean.
	 * 
	 * @param data
	 *            the ForecastData to be filtered
	 * @return did data pass this filter? true=pass, false=fail
	 */
	public boolean apply(ForecastData data) {
		if (rules.isEmpty()) {
			// Empty Filters should not trigger a notification
			return false;
		} else {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (!this.name.equals(other.name))
			return false;
		Set<Rule> otherRules = other.getRules();
		if(otherRules.size() != this.rules.size()){
			return false;
		} 
		for (Rule r : this.rules){
			if(!otherRules.contains(r)){
				return false;
			}
		}
		return true;
	}
}
