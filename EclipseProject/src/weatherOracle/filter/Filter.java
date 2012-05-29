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
	
	
	// Unused, but removing breaks serialization in a very odd way. What nonsense.
	private enum Op {ALL, ANY}
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
	 * The name of the location for which this Filter is active
	 */
	private String locationName;

	/**
	 * The Location of this FIlter
	 */
	private Location loc = new Location(47.609, -122.3331);

	/**
	 * Default Constructor
	 * @param name
	 */
	public Filter(String name) {
		this.rules = new TreeSet<Rule>();
		this.name = name;
		this.locationName=null;
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
	 * Sets the location of this filter
	 * @param loc the new location
	 */
	public void setLocation(Location loc) {
		this.loc=loc;
	}
	
	/**
	 * Returns the locationName of this Filter
	 * @return the locationName associated with this filter
	 */
	public String getLocationName() {
		if (locationName==null) return "Unnamed";
		return locationName;
	}
	
	/**
	 * Sets the locationName of this filter
	 * @param locationName the new locationName
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
		Set<Rule> temp = new TreeSet<Rule>();
		for (Rule r : rules) {
			if (r.getClass() == TimeRule.class) {
				temp.add((TimeRule)r);
			}
		}
		this.rules = temp;
	}
	
	/**
	 * Remove all time rules from "rules"
	 * 
	 */
	public void removeTimeRules() {
		Set<Rule> temp = new TreeSet<Rule>();
		for (Rule r : rules) {
			if (r.getClass() == ConditionRule.class) {
				temp.add((ConditionRule)r);
			}
		}
		this.rules = temp;
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
		if (rules == null || getConditionRules().isEmpty() || getTimeRules().isEmpty()) {
			// Filters with no ConditionRules or TimeRules (also, no Rules at all) should not pass
			return false;
		} else {
			// Atleast one ConditionRule and atleast one TimeRule
			boolean matchConditions = true;
			boolean matchTimes = false;
			boolean hasACondition=false;
			for (Rule r : rules) {
				boolean ruleApply = r.apply(data);
				
				if (r.getClass() == TimeRule.class) {
					// TimeRule -> or
					matchTimes |= ruleApply;
				} else if (r.getClass() == ConditionRule.class) {
					// ConditionRule -> and
					hasACondition=true;
					matchConditions &= ruleApply;
				} else {
					// Bad if we are here, or new kinds of rules to be added later
				}
			}
			return matchConditions && matchTimes && hasACondition;
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
		if (!this.locationName.equals(other.locationName))
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
