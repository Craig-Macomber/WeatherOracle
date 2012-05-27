package weatherOracle.activity;

import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class LocationActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        setLocation();
	}

	private void setLocation(){
		final Button setLocationButton = new Button(this);
			
  	
		
		setLocationButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
//				LocationManager test = HomeMenuActivity.lm;
//				LocationProvider locationProvider = test.NETWORK_PROVIDER;
//				// Or use LocationManager.GPS_PROVIDER
//
//				Location lastKnownLocation = HomeMenuActivity.lm.getLastKnownLocation(locationProvider);
//				
//				Pair filterListPair = FilterStore.getFilters();
//				final List<Filter> filterList = (List<Filter>) filterListPair.first;
//				for (Filter f : filterList){
//					
//				}
//				
//				   //     	Filter testFilter = new Filter("this is a test");
//				   //     	List<Filter> filterList = new LinkedList<Filter>();
//				   //     	filterList.add(testFilter);
//				   //     	FilterStore.setFilters(filterList);
//				   //         final List<Filter> filterList = filterListPair.first;
//				   //         filterList.add(testFilter);
//				   //         FilterStore.setFilters(filterList);
//				   //     }	
          
	   	  			
			}
		});
  
			
	}
	
}
