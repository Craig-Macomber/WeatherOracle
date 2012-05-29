//
//package weatherOracle.activity;
//
//import java.util.List;
//
//import weatherOracle.filter.Filter;
//import weatherOracle.filter.FilterStore;
//import android.app.Activity;
//import android.content.Context;
//import android.location.Location;
//import android.location.LocationManager;
//import android.location.LocationProvider;
//import android.os.Bundle;
//import android.util.Pair;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.RelativeLayout.LayoutParams;
//
//public class LocationActivity extends Activity {
//
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//        setContentView(R.layout.location_activity);
//        displayLocation();
//
//        initializeSetLocationButton();
//	}
//
//	public void onWindowFocusChanged(boolean hasFocus){
// 		super.onWindowFocusChanged(hasFocus);
// 		
//	 	if(hasFocus) {
//	 	    displayLocation();
//	 		
// 		}
// 	}
//	
//	private void displayLocation(){
//		double[] location = getLocation();
//		double latitude = location[0];
//		double longitude = location[1];
//		TextView latView = (TextView) findViewById(R.id.current_latitude);
//		TextView longView = (TextView) findViewById(R.id.current_longitude);
//		latView.setText(""+latitude);
//		longView.setText(""+longitude);
//	}
//	
//	
//	private void initializeSetLocationButton(){
//		final Button setLocationButton = new Button(this);
//		
//		setLocationButton.setOnClickListener(new View.OnClickListener()
//		{
//			public void onClick(View v)
//			{
////				LocationManager test = HomeMenuActivity.lm;
////				LocationProvider locationProvider = test.NETWORK_PROVIDER;
////				// Or use LocationManager.GPS_PROVIDER
////
////				Location lastKnownLocation = HomeMenuActivity.lm.getLastKnownLocation(locationProvider);
////				
//				Pair filterListPair = FilterStore.getFilters();
//				final List<Filter> filterList = (List<Filter>) filterListPair.first;
//				for (Filter f : filterList){
//					
//				}
////				
////				   //     	Filter testFilter = new Filter("this is a test");
////				   //     	List<Filter> filterList = new LinkedList<Filter>();
////				   //     	filterList.add(testFilter);
////				   //     	FilterStore.setFilters(filterList);
////				   //         final List<Filter> filterList = filterListPair.first;
////				   //         filterList.add(testFilter);
////				   //         FilterStore.setFilters(filterList);
////				   //     }	
//          
//	   	  			
//			}
//		});
//  
//			
//	}
//	
//	private double[] getLocation() {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
//        List<String> providers = lm.getProviders(true);
//
//        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
//        Location l = null;
//        
//        for (int i=providers.size()-1; i>=0; i--) {
//                l = lm.getLastKnownLocation(providers.get(i));
//                if (l != null) break;
//        }
//        
//        double[] gps = new double[2];
//        if (l != null) {
//                gps[0] = l.getLatitude();
//                gps[1] = l.getLongitude();
//        }
//        return gps;
//	}
//}