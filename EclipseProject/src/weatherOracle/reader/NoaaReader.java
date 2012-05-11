package weatherOracle.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
			String startTimeString=startTimeNode.getTextContent();
			DateFormat df=DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
			Date startDay=null;
			
			try {
				startDay=df.parse(startTimeString); // TODO : Does not work
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int startHour=0;//startDay.getHours(); // TODO : Not done
			
			List<ForecastData> dataList = new ArrayList<ForecastData>(count);
			
			NodeList params=dom.getElementsByTagName("parameters").item(0).getChildNodes();
			
			Node[] doubleSources=new Node[9];
			for (int k=0;k<doubleSources.length;k++){ 
				// WTF with this indexing
				doubleSources[k]=params.item(k*2+1).getFirstChild();
			}
			
			for (int i=0;i<count;i++){
				double[] d=new double[9];
				for (int k=0;k<d.length;k++){
					String val=doubleSources[k].getTextContent();
					if (val==""){
						d[k]=0;
					} else {
						d[k]=Double.parseDouble(val);
					}
					doubleSources[k]=doubleSources[k].getNextSibling();
				}
				
				Date day=null;
				int hour=(startHour+i)%24;
				ForecastData.Rain rain=ForecastData.Rain.NONE; // TODO
				ForecastData.Thunder thunder=ForecastData.Thunder.NONE; // TODO
				
				ForecastData f=new ForecastData(
						hour,
						day,
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
			}
			
			//Element times=dom.getElementById("time-layout");
			//Log.i(TAG,"TIME: "+startTime);
			
			m.put(loc, dataList);
		}
		return m;
	}
}
