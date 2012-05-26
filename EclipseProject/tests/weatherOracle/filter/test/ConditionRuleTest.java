package weatherOracle.filter.test;

import weatherOracle.filter.ConditionRule;
import weatherOracle.filter.TimeRule;
import weatherOracle.forecastData.ForecastData;
import android.test.AndroidTestCase;
import android.util.Pair;

public class ConditionRuleTest extends AndroidTestCase {
	private static String[] conditions = ConditionRule.conditions;
	
	public void testConstructor() {
		ConditionRule cR = new ConditionRule(conditions[0], 0, 5); 
		
		assertEquals(conditions[0], cR.getCondition());
		assertEquals(new Pair<Integer, Integer>(0, 5), cR.getMinMax());
	}
	
	public void testEqualsAndCompareTo() {
		ConditionRule cR = new ConditionRule(conditions[0], 0, 5);		// Temperature 0 -> 5
		ConditionRule cR2 = new ConditionRule(conditions[4], 0, 5);		// Cloud Cover 0 -> 5 (different than first)
		ConditionRule cR3 = new ConditionRule(conditions[0], 0, 5);		// Same as first
		TimeRule tR = new TimeRule(TimeRule.days[0]);					// Different class of Rule
		
		// Testing equals between objects
		assertFalse(cR.equals(cR2));
		assertTrue(cR.equals(cR3));
		assertFalse(cR.equals(tR));
		assertFalse(cR2.equals(tR));
		
		// Testing compareTo between objects
		int betweenDiffCR = cR.compareTo(cR2);
		int betweenSameCR = cR.compareTo(cR3);
		int betweenDiffRule = cR.compareTo(tR);
		int betweenDiffRule2 = cR2.compareTo(tR);
		
		// a CT b positive -> a after b
		assertTrue(betweenDiffCR > 0);
		assertTrue(betweenSameCR == 0);
		assertTrue(betweenDiffRule > 0);		// TimeRules should always be first
		assertTrue(betweenDiffRule2 > 0);		// TimeRules should always be first	
	}
	
	public void testApply() {
		// Forecast data with 35 temperature and 37 cloud cover, zero for everything else
		ForecastData data = new ForecastData(null, 35.0, 0.0, 0.0, 0.0, 0.0, 0.0, 37.0, 0.0, 0.0);
		
		ConditionRule cR = new ConditionRule(conditions[0], 0, 5);		// Temperature between 0 and 5
		ConditionRule cR2 = new ConditionRule(conditions[4], 35, 45);	// Cloud Cover between 35 and 45  
		
		assertFalse(cR.apply(data));
		assertTrue(cR2.apply(data));
	}
}
