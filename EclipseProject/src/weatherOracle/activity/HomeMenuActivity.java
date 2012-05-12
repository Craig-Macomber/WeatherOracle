package weatherOracle.activity;

import java.util.LinkedList;
import java.util.List;

import weatherOracle.filter.Filter;
import weatherOracle.notification.NotificationStore;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class HomeMenuActivity extends TabActivity {
	static List<Filter> testFilterList = new LinkedList<Filter>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        NotificationStore.initializeNotificationStore();
        
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
        spec = tabHost.newTabSpec("tools").setIndicator("Tools",
                          res.getDrawable(R.drawable.ic_tab_tools))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
    
    public void onResume(Bundle savedInstanceBundle){
    	
    }
    
    
    
}