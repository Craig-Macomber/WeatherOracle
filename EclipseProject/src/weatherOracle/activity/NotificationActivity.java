package weatherOracle.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("Whole shit load of notifications!!");
        setContentView(textview);

        
    //    setContentView(R.layout.main);
    }
}
