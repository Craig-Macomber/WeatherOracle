package weatherOracle.filter.test;

import java.util.*;

import weatherOracle.filter.*;

import android.test.AndroidTestCase;
import android.util.Pair;

public class FilterStoreTest extends AndroidTestCase {
	public void testFilterStore() {
		//fail("not implemented");
		
		// Get with no filters inside
		Pair<List<Filter>, Integer> returnFilters = FilterStore.getFilters();
		
		assertEquals(returnFilters.second.intValue(), 0);
		assertTrue(returnFilters.first.isEmpty());
		
		// Put in some filters and get again
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
