package weatherOracle.forecastData.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import weatherOracle.forecastData.ForecastData;
import android.test.AndroidTestCase;

public class ForecastDataTest extends AndroidTestCase {
	public void testForecastData() {
		Calendar dateTime = new GregorianCalendar();
		dateTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		// Monday
		double temp = 35.0;
		double dew = 23.0;
		double prob = 65.0;
		double wind = 12.0;
		double gust = 30.0;
		double dir = 0.0;
		double cloud = 37.0;
		double humid = 87.0;
		double qpf = 13.0;
		ForecastData data = new ForecastData(dateTime, temp, dew, prob, wind, gust, dir, cloud, humid, qpf);
		
		assertEquals(dateTime.get(Calendar.DAY_OF_WEEK), data.getDayOfWeek());
		assertEquals(temp, data.getTemperature());
		assertEquals(dew, data.getDewpoint());
		assertEquals(prob, data.getProbPrecipitation());
		assertEquals(wind, data.getSustainedWindSpeed());
		assertEquals(gust, data.getGustWindSpeed());
		//assertEquals(dir, );				// Not using wind direction
		assertEquals(cloud, data.getCloudCover());
		assertEquals(humid, data.getHumidity());
		assertEquals(qpf, data.getQPF());
	}
}
