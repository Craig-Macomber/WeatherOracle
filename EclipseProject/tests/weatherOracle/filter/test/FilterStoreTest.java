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
		Filter f = new Filter();
		f.addRule(new ConditionRule());
		
		filters.add(f);
		FilterStore.setFilters(filters);
		
		returnFilters = FilterStore.getFilters();
		
		assertEquals(returnFilters.second.intValue(), 1);
		
		boolean pairMatch = true;
		for (int i = 0; i < returnFilters.first.size(); i++) {
			pairMatch = pairMatch && returnFilters.first.get(i).equals(filters.get(i));
			
		}
		
		assertTrue(pairMatch);
	}
}
