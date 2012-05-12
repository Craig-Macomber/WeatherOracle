package weatherOracle.filter.test;

import weatherOracle.filter.*;
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
		assertEquals(ruleList.size(), 3);
		
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
