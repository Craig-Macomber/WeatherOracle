package weatherOracle.activity;

import weatherOracle.app.R;
import android.app.Activity;
import android.content.Intent;
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
    
}