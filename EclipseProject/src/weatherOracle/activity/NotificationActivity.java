package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
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
        	final RelativeLayout rl = new RelativeLayout(this); 
            textview.setText("Notification " + i);
            textview.setTextSize(2,30);
            rl.addView(textview);
            Button button = new Button(this);
            button.setText("X");
            button.setOnClickListener(new View.OnClickListener()
            {
            	public void onClick(View v)
                {
                	rl.removeAllViews();
                }
            });
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl.addView(button, 1, lp);
            layout.addView(rl);
        }
        
        
    }
}
