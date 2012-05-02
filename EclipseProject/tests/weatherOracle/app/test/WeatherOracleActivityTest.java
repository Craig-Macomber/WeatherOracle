package weatherOracle.app.test;

import weatherOracle.activity.HomeScreenActivity;
import android.test.ActivityInstrumentationTestCase2;

public class WeatherOracleActivityTest extends
		ActivityInstrumentationTestCase2<HomeScreenActivity> {
	public WeatherOracleActivityTest() {
		super("weatherOracle.app.WeatherOracleActivity", HomeScreenActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testPreconditions() {
		String s = "hello";
		//assertNotNull(s);
		fail("not implemented");
	}
	
	public void testText() {
		String x = "hello";
		String y = "hello";
		//assertEquals(x, y);
		fail("not implemented");

	}

}
