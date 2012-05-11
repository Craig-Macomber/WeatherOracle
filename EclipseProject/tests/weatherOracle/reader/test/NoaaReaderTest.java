package weatherOracle.reader.test;

import java.util.List;
import java.util.Map;

import weatherOracle.app.Location;
import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.reader.NoaaReader;
import weatherOracle.reader.Reader;
import android.test.AndroidTestCase;

public class NoaaReaderTest extends AndroidTestCase {
	public void testNoaaReader() {
		Location loc=new Location(47.66076,-122.2950);
		ForecastRequirements r=new ForecastRequirements();
		r.addLoc(loc);
		Reader reader=new NoaaReader();
		Map<Location, List<ForecastData>> result=reader.getData(r);
		assertTrue(result.containsKey(loc));
		assertTrue(result.get(loc)!=null);
		assertTrue(result.get(loc).size()>100);
		assertTrue(result.get(loc).get(0)!=null);
	}
}
