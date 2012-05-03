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
		Filter f = new Filter();
		Rule tR = new TimeRule();
		Rule cR = new ConditionRule();
		
		f.addRule(tR);
		f.addRule(cR);
		
		List<Rule> ruleList = f.getRules();
		assertEquals(ruleList.size(), 2);
	}
	
	public void testRemoveRule() {
		Filter f = new Filter();
		Rule tR = new TimeRule();
		
		f.addRule(tR);
		assertEquals(f.getRules().size(), 1);
		
		f.removeRule(tR);
		assertTrue(f.getRules().isEmpty());
	}
}
