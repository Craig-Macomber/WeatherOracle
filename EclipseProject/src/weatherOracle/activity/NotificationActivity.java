package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NotificationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        final LinearLayout mainView = (LinearLayout)findViewById(R.id.linear);
        
        
        
        for (int i = 0;i<20;i++) {
        	TextView textview =new TextView(getApplicationContext());
        	final RelativeLayout rl = new RelativeLayout(this); 
            textview.setText("Notification " + i);
            textview.setTextSize(2,30);
            rl.setBackgroundResource(R.drawable.main_view_element);
           // textview.getBackground().setAlpha(95);
            
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            	     LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            	layoutParams.setMargins(8, 8, 8, 8);

            
            rl.addView(textview);
            Button button = new Button(this);
            button.setText("X");
            button.setOnClickListener(new View.OnClickListener()
            {
            	public void onClick(View v)
                {
                	mainView.removeView(rl);
                }
            });
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl.addView(button, 1, lp);
         //   rl.setPadding(8, 8, 8, 8);
            mainView.addView(rl, layoutParams);
        }
        
        
    }
}
