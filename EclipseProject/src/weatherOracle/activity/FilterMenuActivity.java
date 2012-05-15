package weatherOracle.activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import weatherOracle.filter.ConditionRule;
import weatherOracle.filter.Filter;
import weatherOracle.filter.FilterStore;
import weatherOracle.filter.TimeRule;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class FilterMenuActivity extends TabActivity {
    static Filter filter;
	static String currentFilterName = "";
	static String initialFilterName = "";
	static Set<ConditionRule> conditions;
	static Set<TimeRule> times;
	static TabHost tabHost;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filter_menu_activity);

        Resources res = getResources(); // Resource object to get Drawables
        tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

       
        intent = new Intent().setClass(this, FilterOptionsActivity.class);
        spec = tabHost.newTabSpec("tools").setIndicator("Name",
                          res.getDrawable(R.drawable.ic_tab_tools))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        
        
        
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ConditionRuleViewerActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("weather").setIndicator("Weather",
                          res.getDrawable(R.drawable.ic_tab_weather))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        // get extras passed to this activity by FiltersActivity
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	filter = (Filter) extras.get("filter");
        	currentFilterName = filter.getName();

        	initialFilterName = filter.getName();

        	conditions = filter.getConditionRules();
        }
        

   //     String test = "no";
   //     if(newFilter){
   //     	test = "new filter";
   //     	Filter testFilter = new Filter("this is a test");
   //     	HomeMenuActivity.testFilterList.add(testFilter);
   
   //     	Filter testFilter = new Filter("this is a test");
   //     	List<Filter> filterList = new LinkedList<Filter>();
   //     	filterList.add(testFilter);
   //     	FilterStore.setFilters(filterList);
   //         final List<Filter> filterList = filterListPair.first;
   //         filterList.add(testFilter);
   //         FilterStore.setFilters(filterList);
   //     }
//        
        
        // Do the same for the other tabs
        intent = new Intent().setClass(this, TimeRuleViewerActivity.class);
        spec = tabHost.newTabSpec("clock").setIndicator("Clock",
                          res.getDrawable(R.drawable.ic_tab_clock))
                      .setContent(intent);
        tabHost.addTab(spec);


        tabHost.setCurrentTab(0);
    }
}