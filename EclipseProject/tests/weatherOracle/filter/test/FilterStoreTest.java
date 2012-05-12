package weatherOracle.filter.test;

import java.util.*;

import weatherOracle.filter.*;

import android.test.AndroidTestCase;
import android.util.Pair;

public class FilterStoreTest extends AndroidTestCase {
	public void testFilterStore() {
		// Try getting filters when there are none in there yet
		FilterStore.ctx = getContext();
		FilterStore.clearMemory();
		
		Pair<List<Filter>, Integer> returnFilters = FilterStore.getFilters();
		
		assertEquals(0, returnFilters.second.intValue());
		assertEquals(0, returnFilters.first.size() );
		
		// Put in some filters and get the filters again
		List<Filter> filters = new LinkedList<Filter>();
		Filter f = new Filter("a");
		f.addRule(new ConditionRule(ConditionRule.conditions[0], 0, 5));
		f.addRule(new TimeRule(TimeRule.days[0]));
		f.addRule(new ConditionRule(ConditionRule.conditions[1], 3, 19));
		
		//System.out.println(f.getRules());
		
		filters.add(f);
		
		
		FilterStore.setFilters(filters);
		returnFilters = null;
		returnFilters = FilterStore.getFilters();
		
		assertEquals(1, returnFilters.second.intValue());
		
		List<Filter> outputFilters = returnFilters.first;
		
		assertEquals(1, outputFilters.size());
		
		boolean pairMatch = true;
		for (int i = 0; i < outputFilters.size(); i++) {
			pairMatch = pairMatch && returnFilters.first.get(i).equals(filters.get(i));
			
		}
		assertTrue(pairMatch);
		
		Filter output = outputFilters.get(0);
		
		assertEquals(3, output.getRules().size());
		assertEquals(1, output.getTimeRules().size());
		assertEquals(2, output.getConditionRules().size());
		
	}
}
