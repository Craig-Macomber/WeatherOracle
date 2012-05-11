package weatherOracle.filter.test;

import weatherOracle.filter.*;
import android.test.AndroidTestCase;

import java.util.*;

public class FilterTest extends AndroidTestCase {
	public void testFilter() {
		//assertTrue(true);
		fail("not implemented");

	}
	
	public void testAddRule() {
		Filter f = new Filter("a");
		Rule tR = new TimeRule(TimeRule.days[0]);
		Rule cR = new ConditionRule(ConditionRule.conditions[0], 0, 5);
		
		f.addRule(tR);
		f.addRule(cR);
		
		Set<Rule> ruleList = f.getRules();
		assertEquals(ruleList.size(), 2);
	}
	
	public void testRemoveRule() {
		Filter f = new Filter("a");
		Rule tR = new TimeRule(TimeRule.days[0]);
		
		f.addRule(tR);
		assertEquals(f.getRules().size(), 1);
		
		f.removeRule(tR);
		assertTrue(f.getRules().isEmpty());
	}
}
