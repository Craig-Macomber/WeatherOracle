package weatherOracle.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.app.Location;
import weatherOracle.control.MainControl;

/**
 * The main implementation of Reader for reading with real forecast data off the
 * NOAA site.
 * 
 */
public class NoaaReader implements Reader {
	private static final String TAG = "*******NoaaReader*******";

	private static final String units[] = { "Degrees fahrenheit",// temperature_hourly
			"Degrees fahrenheit",// temperature_dewPoint
			"Percentage",// probabilityOfPrecipitation
			"Miles per hour",// windSpeed_sustained
			"Miles per hour",// windSpeed_gust
			"Miles per hour",// windSpeed_direction
			"",// cloudAmount
			"",// humidity_relative
			"",// hourly_qpf
			"",// Rain
			"", };// Thunder

	/**
	 * 
	 * @param i
	 *            =index of ForecastData items you need a unit for
	 * @return a string of the name of the unit used
	 */
	public static String getUnit(int i) {
		// TODO: support unit conversion to metric or whatever
		return units[i];
	}

	/**
	 * 
	 * @param condition
	 *            =the name of the weather condition
	 * @return a string of the name of the unit used
	 */
	public static String getUnit(String condition) {
		if (condition.equalsIgnoreCase("temperature_hourly"))
			return units[0];
		else if (condition.equalsIgnoreCase("temperature_dewPoint"))
			return units[1];
		else if (condition.equalsIgnoreCase("probabilityOfPrecipitation"))
			return units[2];
		else if (condition.equalsIgnoreCase("windSpeed_sustained"))
			return units[3];
		else if (condition.equalsIgnoreCase("windSpeed_gust"))
			return units[4];
		else if (condition.equalsIgnoreCase("windSpeed_direction"))
			return units[5];
		else if (condition.equalsIgnoreCase("cloudAmount"))
			return units[6];
		else if (condition.equalsIgnoreCase("humidity_relative"))
			return units[7];
		else if (condition.equalsIgnoreCase("hourly_qpf"))
			return units[8];
		else if (condition.equalsIgnoreCase("Rain"))
			return units[9];
		else if (condition.equalsIgnoreCase("Thunder"))
			return units[10];
		else
			return null;
	}

	public Map<Location, List<ForecastData>> getData(ForecastRequirements r) {
		Log.i(TAG, "getData start");

		Map<Location, List<ForecastData>> m = new HashMap<Location, List<ForecastData>>();
		try {
			// http://forecast.weather.gov/MapClick.php?lat=47.66076&lon=-122.29508&FcstType=digitalDWML
			for (Location loc : r.getData()) {
				URL url;
				url = new URL("http://forecast.weather.gov/MapClick.php?lat="
						+ loc.lat + "&lon=" + loc.lon + "&FcstType=digitalDWML");

				InputStream s;
				s = url.openConnection().getInputStream();

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();

				// Using factory get an instance of document builder
				DocumentBuilder db=null;

				try {
					db = dbf.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// parse using builder to get DOM representation of the XML file
				Document dom=null;
				try {
					dom = db.parse(s);
				} catch (SAXException e) {
					continue; // location likley invalid, skip
				}

				// TODO : Fill dataList with ForecastData from dom
				NodeList times = dom.getElementsByTagName("start-valid-time");
				int count = times.getLength();
				assert count > 0;

				Node startTimeNode = times.item(0);

				// get time as string like: 2012-05-15T21:00:00-07:00
				String startTimeString = startTimeNode.getTextContent();
				String[] timeParts = startTimeString.split("[-T:]");

				GregorianCalendar dateTime = new GregorianCalendar();
				int year = Integer.parseInt(timeParts[0]);
				int month = Integer.parseInt(timeParts[1]);
				int day = Integer.parseInt(timeParts[2]);
				int hourOfDay = Integer.parseInt(timeParts[3]);
				int timeZone = -Integer.parseInt(timeParts[6]);
				dateTime.clear();
				dateTime.set(year, month - 1, day, hourOfDay, 0);
				TimeZone zone=new SimpleTimeZone(timeZone*1000*60*60,"");
				dateTime.setTimeZone(zone);
				List<ForecastData> dataList = new ArrayList<ForecastData>(count);

				NodeList params = dom.getElementsByTagName("parameters")
						.item(0).getChildNodes();

				int colCount = 9;
				double[][] doubles = new double[count][colCount];
				for (int k = 0; k < colCount; k++) {
					// WTF with this indexing
					Node n = params.item(k * 2 + 1).getFirstChild();
					for (int i = 0; i < count; i++) {
						String val = n.getTextContent();
						n = n.getNextSibling();
						if (val == "") {
							doubles[i][k] = 0;
						} else {
							doubles[i][k] = Double.parseDouble(val);
						}
					}
				}

				for (int i = 0; i < count; i++) {
					double[] d = doubles[i];
					ForecastData f = new ForecastData(
							(Calendar) dateTime.clone(), d[0], d[1], d[2],
							d[3], d[4], d[5], d[6], d[7], d[8]);
					dataList.add(f);
					dateTime.add(Calendar.HOUR, 1);
				}

				// Element times=dom.getElementById("time-layout");
				// Log.i(TAG,"TIME: "+startTime);

				m.put(loc, dataList);
			}
			MainControl.setLastUpdateWorked(true);
			return m;

		} catch (IOException e){
			MainControl.setLastUpdateWorked(false);
			return null;
		}
	}

}
