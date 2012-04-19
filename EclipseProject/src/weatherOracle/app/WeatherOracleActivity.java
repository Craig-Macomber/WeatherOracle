package weatherOracle.app;

import weatherOracle.control.MainControl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherOracleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d("WeatherOracleActivity","onCreate");
        
        setContentView(R.layout.main);
        
        MainControl.start();
    }
}