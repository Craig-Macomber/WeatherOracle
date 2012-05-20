package weatherOracle.filter.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import weatherOracle.filter.ConditionRule;
import weatherOracle.filter.TimeRule;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastData.Rain;
import weatherOracle.forecastData.ForecastData.Thunder;
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
		// Forecast data with Tuesday
		Calendar dateTime = new GregorianCalendar();
		dateTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		ForecastData data = new ForecastData(dateTime, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Rain.NONE, Thunder.NONE);
		
		TimeRule tR = new TimeRule(days[4]);		// Friday
		TimeRule tR2 = new TimeRule(days[1]);		// Tuesday
		
		assertFalse(tR.apply(data));
		assertTrue(tR2.apply(data));
	}
}
