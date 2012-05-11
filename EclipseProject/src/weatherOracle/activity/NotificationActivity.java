package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.notification.Notification;
import weatherOracle.notification.NotificationStore;

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
	
	List<Notification> notificationList;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        
        populateNotificationList();
        displayNotifications();	
        
        
        
    }

	private void populateNotificationList() {
		notificationList = NotificationStore.getNotifications();
        //REASSIGN THIS SHIT FOR TESTING UI WHILE WAITING FOR THE MODEL SHIT TO WORK
        //TODO
        notificationList = new ArrayList<Notification>();
        notificationList.add(new Notification("I"));
        notificationList.add(new Notification("Love"));
        notificationList.add(new Notification("Hentai!"));
        //END OF TESTING CODE
		
	}

	private void displayNotifications() {
		final LinearLayout mainView = (LinearLayout)findViewById(R.id.notification_activity_linear_layout);
		for (int i = 0;i<notificationList.size();i++) {
        	TextView textview =new TextView(getApplicationContext());
        	final RelativeLayout rl = new RelativeLayout(this); 
            textview.setText(notificationList.get(i).getName());
            textview.setTextSize(2,30);
            rl.setBackgroundResource(R.drawable.main_view_element);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            	     LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8, 4, 8, 4);
            rl.addView(textview);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mainView.addView(rl, layoutParams);
        }
	}
}
