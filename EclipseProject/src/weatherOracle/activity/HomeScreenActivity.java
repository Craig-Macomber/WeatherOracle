package weatherOracle.activity;

import weatherOracle.app.R;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;

// Main Android Activity, shown on launch
public class HomeScreenActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);    
    }
    
    public void goToHomeScreen(View v) {
    	setContentView(R.layout.home_screen);
    }
    
    public void goToViewFilterScreen(View v) {
    	setContentView(R.layout.view_filter_screen);
    }
    
    public void goToEditConditionScreen(View v) {
    	setContentView(R.layout.edit_condition_screen);
    }
    
    public void goToEditUtilScreen(View v) {
    	setContentView(R.layout.edit_util_screen);
    }
    
    public void goToEditTimeScreen(View v) {
    	setContentView(R.layout.edit_time_screen);
    }
    
    public void goToEditTimerangeScreen(View v) {
    	setContentView(R.layout.edit_timerange_screen);
    }
    
    public void goToEditRuleScreen(View v) {
    	setContentView(R.layout.edit_rule_screen);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}