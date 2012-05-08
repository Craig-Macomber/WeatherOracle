package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NotificationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView mainView = new ScrollView(this);
        setContentView(mainView);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        mainView.addView(layout);
        
        
        
        for (int i = 0;i<20;i++) {
        	TextView textview =new TextView(getApplicationContext());
            textview.setText("Filter " + i);
            textview.setTextSize(2,30);
            layout.addView(textview);
            
        }
        
        
    }
}
