package weatherOracle.filter.test;

import weatherOracle.filter.*;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastData.Rain;
import weatherOracle.forecastData.ForecastData.Thunder;
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
		ForecastData data = new ForecastData(0, null, 35.0, 0.0, 0.0, 0.0, 0.0, 0.0, 37.0, 0.0, 0.0, Rain.NONE, Thunder.NONE);
		
		Filter shouldPass = new Filter("pass");
		Filter shouldFail = new Filter("fail");
		Filter emptyFilter = new Filter("empty");
		
		ConditionRule cR = new ConditionRule(ConditionRule.conditions[0], 30, 45);		// Temperature 30 -> 45
		//TimeRule tR = new TimeRule(TimeRule.days[0]);
		ConditionRule cR2 = new ConditionRule(ConditionRule.conditions[4], 35, 45);		// Cloud Cover 35 -> 45
		ConditionRule bad = new ConditionRule(ConditionRule.conditions[0], 0, 5);		// Temperature 0 -> 5
		
		shouldPass.addRule(cR);
		shouldPass.addRule(cR2);
		
		shouldFail.addRule(cR2);
		shouldFail.addRule(bad);
		
		assertTrue(shouldPass.apply(data));
		assertFalse(emptyFilter.apply(data));
		assertFalse(shouldFail.apply(data));
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
}
