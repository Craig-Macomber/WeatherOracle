package weatherOracle.app;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NotificationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d("WeatherOracleActivity","onCreate");
        
        //setContentView(R.layout.main);
        
    }
    
    public void goToFilterViewer(View v)
    {
    	setContentView(R.layout.filterviewer);
    }
    
}