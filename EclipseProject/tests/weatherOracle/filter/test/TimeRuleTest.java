package weatherOracle.filter.test;

//import java.util.Date;

import weatherOracle.filter.ConditionRule;
import weatherOracle.filter.TimeRule;
import android.test.AndroidTestCase;

public class TimeRuleTest extends AndroidTestCase {
	private static String[] days = TimeRule.days;
	
	public void testConstructor() {
		TimeRule tR = new TimeRule(days[0]);		// Sunday rule
		
		assertEquals(days[0], tR.getDay());
	}
	
	public void testEqualsAndCompareTo() {
		TimeRule tR = new TimeRule(days[0]);		// Sunday
		TimeRule tR2 = new TimeRule(days[5]);		// Friday
		TimeRule tR3 = new TimeRule(days[0]);		// Sunday (same as first)
		ConditionRule cR = new ConditionRule(ConditionRule.conditions[0], 0, 5);		// Temperature 0 -> 5
		
		// Testing equals
		assertFalse(tR.equals(tR2));
		assertTrue(tR.equals(tR3));
		assertFalse(tR.equals(cR));			// Different class of Rule
		assertFalse(tR.equals(cR));			// Different class of Rule
		
		// Testing compareTo
		int betweenDiffTR = tR.compareTo(tR2);
		int betweenSameTR = tR.compareTo(tR3);
		int betweenDiffRule = tR.compareTo(cR);	
		int betweenDiffRule2 = tR2.compareTo(cR);	
		
		assertTrue(betweenDiffTR < 0);
		assertTrue(betweenSameTR == 0);
		assertTrue(betweenDiffRule < 0);			// ConditionRules should always be last
		assertTrue(betweenDiffRule2 < 0);			// ConditionRules should always be last
	}
	
	public void testApply() {
		// Forecast data with 35 temperature and 37 cloud cover, zero for everything else
		//Date d = new Date();
		//ForecastData data = new ForecastData(0, d, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Rain.NONE, Thunder.NONE);
		
		fail("not implemented yet, Date is deprecated so what do we use?");
	}

}
