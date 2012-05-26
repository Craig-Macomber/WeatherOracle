package weatherOracle.filter.test;

import weatherOracle.filter.*;
import weatherOracle.forecastData.ForecastData;
import android.test.AndroidTestCase;

import java.util.*;

public class FilterTest extends AndroidTestCase {
	public void testAddRule() {
		Filter f = new Filter("a");
		Rule tR = new TimeRule(TimeRule.days[0]);
		Rule cR = new ConditionRule(ConditionRule.conditions[0], 0, 5);
		Rule tR2 = new TimeRule(TimeRule.days[2]);
		
		f.addRule(tR);
		f.addRule(cR);
		f.addRule(tR2);
		
		Set<Rule> ruleList = f.getRules();
		assertEquals(3, ruleList.size());
		
	}
	
	public void testRemoveRule() {
		Filter f = new Filter("a");
		Rule tR = new TimeRule(TimeRule.days[0]);
		
		f.addRule(tR);
		assertEquals(1, f.getRules().size());
		
		f.removeRule(tR);
		assertTrue(f.getRules().isEmpty());
	}
	
	public void testGetRules() {
		Filter f = new Filter("first");
		
		ConditionRule cR = new ConditionRule(ConditionRule.conditions[0], 35, 45);
		TimeRule tR = new TimeRule(TimeRule.days[0]);
		ConditionRule cR2 = new ConditionRule(ConditionRule.conditions[4], 35, 45);
		
		f.addRule(cR);
		f.addRule(tR);
		f.addRule(cR2);
		
		Set<Rule> allRules = f.getRules();
		
		assertEquals(3, allRules.size());
		assertEquals(2, f.getConditionRules().size());
		assertEquals(1, f.getTimeRules().size());
		
		// Supposed Order: tR, cR2, cR
		int i = 0;
		boolean orderMatch = true;
		for (Rule r : allRules) {
			if (i == 0) {
				orderMatch &= r.equals(tR);
			} else if (i == 1) {
				orderMatch &= r.equals(cR2);
			} else {
				// i == 2
				orderMatch &= r.equals(cR);
			}
			i++;
		}
		assertTrue(orderMatch);
	}
	
	public void testApply() {
		// Forecast data with 35 temperature and 37 cloud cover, zero for everything else
		Calendar dateTime = new GregorianCalendar();
		dateTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		// Monday
		ForecastData data = new ForecastData(dateTime, 35.0, 0.0, 0.0, 0.0, 0.0, 0.0, 37.0, 0.0, 0.0);
		
		Filter shouldPass = new Filter("pass");
		Filter shouldFail = new Filter("fail");
		Filter emptyFilter = new Filter("empty");
		Filter multipleDays = new Filter("multipleDays");
		Filter wrongDay = new Filter("wrongDay");
		Filter noConditions = new Filter("noConditions");
		Filter noTimes = new Filter("noTimes");
		
		ConditionRule cR = new ConditionRule(ConditionRule.conditions[0], 30, 45);		// Temperature 30 -> 45
		TimeRule tR = new TimeRule(TimeRule.days[0]);			// Monday
		TimeRule tR2 = new TimeRule(TimeRule.days[1]);			// Tuesday
		ConditionRule cR2 = new ConditionRule(ConditionRule.conditions[4], 35, 45);		// Cloud Cover 35 -> 45
		ConditionRule bad = new ConditionRule(ConditionRule.conditions[0], 0, 5);		// Temperature 0 -> 5
		
		shouldPass.addRule(cR);
		shouldPass.addRule(cR2);
		shouldPass.addRule(tR);
		
		shouldFail.addRule(cR2);
		shouldFail.addRule(bad);
		shouldFail.addRule(tR2);
		
		multipleDays.addRule(cR);
		multipleDays.addRule(tR);
		multipleDays.addRule(tR2);
		
		wrongDay.addRule(cR);
		wrongDay.addRule(cR2);
		wrongDay.addRule(tR2);
		
		noConditions.addRule(tR);
		noConditions.addRule(tR2);
		
		noTimes.addRule(cR);
		noTimes.addRule(cR2);
		
		assertTrue(shouldPass.apply(data));
		assertFalse(emptyFilter.apply(data));
		assertFalse(shouldFail.apply(data));
		assertTrue(multipleDays.apply(data));
		assertFalse(wrongDay.apply(data));
		assertFalse(noConditions.apply(data));
		assertFalse(noTimes.apply(data));	
	}
	
	public void testEquals() {
		Filter first = new Filter("first");
		Filter second = new Filter("second");
		Filter sameNameCopy = new Filter("first");
		Filter sameNameEmpty = new Filter("first");
		
		ConditionRule cR = new ConditionRule(ConditionRule.conditions[0], 35, 45);
		TimeRule tR = new TimeRule(TimeRule.days[0]);
		ConditionRule cR2 = new ConditionRule(ConditionRule.conditions[4], 35, 45);
		
		first.addRule(cR);
		first.addRule(tR);
		first.addRule(cR2);
		
		second.addRule(cR2);
		
		sameNameCopy.addRule(tR);
		sameNameCopy.addRule(cR2);
		sameNameCopy.addRule(cR);
		
		assertFalse(first.equals(second));
		assertTrue(first.equals(sameNameCopy));
		assertFalse(first.equals(sameNameEmpty));
	}
	
	public void testAddAndRemoveRules() {
		Filter f1 = new Filter("F1");
		Filter f2 = new Filter("F2");
		Filter empty = new Filter("Empty");
		
		Set<TimeRule> TimeRuleSet = new TreeSet<TimeRule>();
		
		for(int i = 0; i < 7; i++) {
			TimeRuleSet.add(new TimeRule(TimeRule.days[i]));
		}
		
		f1.addSetOfTimeRules(TimeRuleSet);
		f2.addSetOfTimeRules(TimeRuleSet);
		
		assertTrue(f1.getTimeRules().equals(f2.getTimeRules()));
		
		f2.removeTimeRules();
		
		assertFalse(f1.getTimeRules().equals(f2.getTimeRules()));
		assertTrue(f2.getTimeRules().equals(empty.getTimeRules()));
		
		f2.addSetOfTimeRules(TimeRuleSet);
		
		assertTrue(f1.getTimeRules().equals(f2.getTimeRules()));
		assertFalse(f2.getTimeRules().equals(empty.getTimeRules()));
		
		
		ConditionRule temperature = new ConditionRule(ConditionRule.conditions[0], 0, 5);
		ConditionRule dewpoint = new ConditionRule(ConditionRule.conditions[1], 0, 5);
		ConditionRule cloudcover = new ConditionRule(ConditionRule.conditions[4], 0, 5);
		
		Set<ConditionRule> ConditionRuleSet = new TreeSet<ConditionRule>();
		ConditionRuleSet.add(temperature);
		ConditionRuleSet.add(dewpoint);
		ConditionRuleSet.add(cloudcover);
		
		Filter f3 = new Filter("F3");
		Filter f4 = new Filter("F4");
		
		f3.addSetOfConditionRules(ConditionRuleSet);
		f4.addSetOfConditionRules(ConditionRuleSet);
		
		assertTrue(f3.getConditionRules().equals(f4.getConditionRules()));
		
		f4.removeConditionRules();
		
		assertFalse(f3.getConditionRules().equals(f4.getConditionRules()));
		assertTrue(f4.getConditionRules().equals(empty.getConditionRules()));
		
		f4.addSetOfConditionRules(ConditionRuleSet);
		
		assertTrue(f3.getConditionRules().equals(f4.getConditionRules()));
		assertFalse(f4.getConditionRules().equals(empty.getConditionRules()));
		
		f1 = new Filter("F1");
		
		f1.addSetOfTimeRules(TimeRuleSet);
		f1.addSetOfConditionRules(ConditionRuleSet);
		f2.addSetOfTimeRules(TimeRuleSet);
		f2.addSetOfConditionRules(ConditionRuleSet);
		
		assertTrue(f1.getConditionRules().equals(f2.getConditionRules()));
		f1.removeConditionRules();
		assertFalse(f1.getConditionRules().equals(f2.getConditionRules()));
		assertTrue(f1.getTimeRules().equals(f2.getTimeRules()));
		f1.addSetOfConditionRules(ConditionRuleSet);
		assertTrue(f1.getConditionRules().equals(f2.getConditionRules()));
		
		assertTrue(f1.getTimeRules().equals(f2.getTimeRules()));
		f1.removeTimeRules();
		assertFalse(f1.getTimeRules().equals(f2.getTimeRules()));
		assertTrue(f1.getConditionRules().equals(f2.getConditionRules()));
		f1.addSetOfTimeRules(TimeRuleSet);
		assertTrue(f1.getTimeRules().equals(f2.getTimeRules()));
	}
}
