package weatherOracle.filter.test;

import java.util.*;

import weatherOracle.filter.*;

import android.test.AndroidTestCase;
import android.util.Pair;

public class FilterStoreTest extends AndroidTestCase {
	public void testFilterStore() {
		// Try getting filters when there are none in there yet
		Pair<List<Filter>, Integer> returnFilters = FilterStore.getFilters();
		
		assertEquals(returnFilters.second.intValue(), 0);
		assertTrue(returnFilters.first.isEmpty());
		
		// Put in some filters and get the filters again
		List<Filter> filters = new LinkedList<Filter>();
		Filter f = new Filter("a");
		f.addRule(new ConditionRule(ConditionRule.conditions[0], 0, 5));
		f.addRule(new TimeRule(TimeRule.days[0]));
		f.addRule(new ConditionRule(ConditionRule.conditions[1], 3, 19));
		
		//System.out.println(f.getRules());
		
		filters.add(f);
		
		
		FilterStore.setFilters(filters);
		
		returnFilters = FilterStore.getFilters();
		
		assertEquals(returnFilters.second.intValue(), 1);
		
		boolean pairMatch = true;
		for (int i = 0; i < returnFilters.first.size(); i++) {
			pairMatch = pairMatch && returnFilters.first.get(i).equals(filters.get(i));
			
		}
		
		assertEquals(returnFilters.first.size(), 3);
		
		assertTrue(pairMatch);
	}
}
