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

/**
 * The main implementation of Reader for reading with real forecast data off the
 * NOAA site.
 * 
 */
public class NoaaReader implements Reader {
	private static final String TAG="*******NoaaReader*******";
	public Map<Location, List<ForecastData>> getData(ForecastRequirements r) {
		Log.i(TAG,"getData start");
		
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

			

			// TODO : Fill dataList with ForecastData from dom
			NodeList times=dom.getElementsByTagName("start-valid-time");
			int count=times.getLength();
			assert count>0;
			
			
			Node startTimeNode=times.item(0);
			
			// get time as string like: 2012-05-15T21:00:00-07:00
			String startTimeString=startTimeNode.getTextContent();
			String[] timeParts=startTimeString.split("[-T:]");
			
			GregorianCalendar dateTime=new GregorianCalendar();
			int year=Integer.parseInt(timeParts[0]);
			int month=Integer.parseInt(timeParts[1]);
			int day=Integer.parseInt(timeParts[2]);
			int hourOfDay=Integer.parseInt(timeParts[3]);
			dateTime.set(year, month, day, hourOfDay, 0);

		
			
			List<ForecastData> dataList = new ArrayList<ForecastData>(count);
			
			NodeList params=dom.getElementsByTagName("parameters").item(0).getChildNodes();
			
			int colCount= 9;
			double[][] doubles=new double[count][colCount];
			for (int k=0;k<colCount;k++){ 
				// WTF with this indexing
				Node n=params.item(k*2+1).getFirstChild();
				for (int i=0;i<count;i++){
					String val=n.getTextContent();
					n=n.getNextSibling();
					if (val==""){
						doubles[i][k]=0;
					} else {
						doubles[i][k]=Double.parseDouble(val);
					}
				}
			}
			
			for (int i=0;i<count;i++){
				double[] d=doubles[i];
				ForecastData.Rain rain=ForecastData.Rain.NONE; // TODO
				ForecastData.Thunder thunder=ForecastData.Thunder.NONE; // TODO
				ForecastData f=new ForecastData(
						(Calendar) dateTime.clone(),
						d[0],
						d[1],
						d[2],
						d[3],
						d[4],
						d[5],
						d[6],
						d[7],
						d[8],
						rain,
						thunder
				);
				dataList.add(f);
				dateTime.add(Calendar.HOUR, 1);
			}
			
			//Element times=dom.getElementById("time-layout");
			//Log.i(TAG,"TIME: "+startTime);
			
			m.put(loc, dataList);
		}
		return m;
	}
}
