package weatherOracle.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import weatherOracle.forecastData.ForecastData;
import weatherOracle.forecastData.ForecastRequirements;
import weatherOracle.app.Location;

/**
 *  The main implementation of Reader for reading with real forecast data off the NOAA site.
 *
 */
public class NoaaReader implements Reader {


	public Map<Location, List<ForecastData>> getData(ForecastRequirements r) {
		Map<Location, List<ForecastData>> m = new HashMap<Location, List<ForecastData>>();

		// http://forecast.weather.gov/MapClick.php?lat=47.66076&lon=-122.29508&FcstType=digitalDWML
		for (Location loc : r.getData()) {
			URL url;
			try {
				url = new URL("http://forecast.weather.gov/MapClick.php?lat="
						+ loc.lat + "&lon=" + loc.lon + "&FcstType=digitalDWML");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			InputStream s;
			try {
				s = url.openConnection().getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			// Using factory get an instance of document builder
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			// parse using builder to get DOM representation of the XML file
			Document dom;
			try {
				dom = db.parse(s);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			List<ForecastData> dataList = new ArrayList<ForecastData>();

			// TODO : Fill dataList with ForecastData from dom

			m.put(loc, dataList);
		}
		return m;
	}
}
