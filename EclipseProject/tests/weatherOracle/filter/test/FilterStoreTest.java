package weatherOracle.filter.test;

import java.util.*;

import weatherOracle.app.Location;
import weatherOracle.filter.*;

import android.test.AndroidTestCase;
import android.util.Pair;

public class FilterStoreTest extends AndroidTestCase {
	public void testFilterStore() {
		FilterStore.ctx = getContext();
		
		// Create a list of Filters
		List<Filter> filters = new LinkedList<Filter>();
		Filter f = new Filter("firstFilter");
		f.addRule(new ConditionRule(ConditionRule.conditions[0], 0, 5));
		f.addRule(new TimeRule(TimeRule.days[0]));
		f.addRule(new ConditionRule(ConditionRule.conditions[1], 3, 19));
		filters.add(f);
		
		// Store Filters in persistent memory
		FilterStore.setFilters(filters);
		
		// Retrieve from persistent memory
		Pair<List<Filter>, Integer> firstFilters = FilterStore.getFilters();
		
		assertEquals(1, firstFilters.first.size());			// Only one Filter in the list
		Filter first = firstFilters.first.get(0);
		assertEquals(3, first.getRules().size());			// 3 Rules in the first Filter
		assertEquals("firstFilter", first.getName());
		
		int firstVersion = firstFilters.second.intValue();

		// Create a new List of Filters
		filters = new LinkedList<Filter>();
		Filter f2 = new Filter("secondFilter");
		Filter f3 = new Filter("thirdFilter");
		f2.addRule(new ConditionRule(ConditionRule.conditions[5], 30, 35));
		f2.addRule(new TimeRule(TimeRule.days[1]));
		f3.addRule(new ConditionRule(ConditionRule.conditions[2], 3, 19));
		filters.add(f2);
		filters.add(f3);
		
		// Store second List of Filters, overwriting the new Filters
		FilterStore.setFilters(filters);
		
		// Retrieve again
		Pair<List<Filter>, Integer> secondFilters = FilterStore.getFilters();
		
		assertTrue(firstVersion < secondFilters.second.intValue());	// Second version number should be larger 
		
		assertEquals(2, secondFilters.first.size());		// Two Filters in the List
		Filter firstNew = secondFilters.first.get(0);
		Filter secondNew = secondFilters.first.get(1);
		assertEquals(2, firstNew.getRules().size());		// First Filter has two Rules
		assertEquals(1, secondNew.getRules().size());		// Second Filter has only one Rule
		
		// Match each Filter 
		boolean pairMatch = true;
		for (int i = 0; i < filters.size(); i++) {
			pairMatch = pairMatch && secondFilters.first.get(i).equals(filters.get(i));
			
		}
		assertTrue(pairMatch);
	}
	
	public void testFilterStoreLocations() {
		FilterStore.ctx = getContext();
		
		// Create a list of Filters
		List<Filter> filters = new LinkedList<Filter>();
		Filter f = new Filter("firstFilter");
		f.setLocation(new Location(123,123));
		filters.add(f);
		
		// Store Filters in persistent memory
		FilterStore.setFilters(filters);
		
		// Retrieve from persistent memory
		Pair<List<Filter>, Integer> firstFilters = FilterStore.getFilters();
		
		assertEquals(1, firstFilters.first.size());			// Only one Filter in the list
		Filter first = firstFilters.first.get(0);
		assertEquals(f.getLocation(), first.getLocation());			// 3 Rules in the first Filter
	}
}
