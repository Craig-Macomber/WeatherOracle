package weatherOracle.activity;

import java.util.LinkedList;
import java.util.List;

import weatherOracle.app.Location;
import weatherOracle.control.MainControl;
import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import weatherOracle.notification.NotificationStore;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class HomeMenuActivity extends TabActivity {
	static List<Filter> filterList;
	public static LocationManager lm;
	public static Context mainContext;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainContext = this.getApplicationContext();
        
        NotificationStore.initializeNotificationStore();
        filterList = new LinkedList<Filter>(FilterStore.getFilters().first);
        MainControl.start();
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        
        
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, NotificationActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("notifications").setIndicator("Notifications",
                          res.getDrawable(R.drawable.ic_tab_notifications))
                      .setContent(intent);
       
        tabHost.addTab(spec);
        
        // Do the same for the other tabs
        intent = new Intent().setClass(this, FiltersActivity.class);
        spec = tabHost.newTabSpec("filters").setIndicator("Filters",
                          res.getDrawable(R.drawable.ic_tab_filters))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ToolsActivity.class);
        spec = tabHost.newTabSpec("tools").setIndicator("Location",
                          res.getDrawable(R.drawable.ic_tab_tools))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        
        
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 9, ll);
    }
    
    private class mylocationlistener implements LocationListener {
        
        public void onLocationChanged(android.location.Location location) {
            if (location != null) {
            	Location.setCurrentLocation(location);
            }
        }
        
        public void onProviderDisabled(String provider) {
        }
        
        public void onProviderEnabled(String provider) {
        }
        
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }   
}