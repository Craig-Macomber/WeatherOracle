package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.notification.Notification;
import weatherOracle.notification.NotificationStore;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
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
        //notificationList = new ArrayList<Notification>();
        notificationList.add(Notification.make("I",null,null));
        notificationList.add(Notification.make("Love",null,null));
        notificationList.add(Notification.make("Hentai",null,null));
        notificationList.add(Notification.make("And",null,null));
        notificationList.add(Notification.make("Bacon!",null,null));
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
	
	public void statusBarNotification(int icon,CharSequence tickerText,CharSequence contentTitle,CharSequence contentText)
	{
		//Example: statusBarNotification(R.drawable.rain,"It's raining!","WeatherOracle","It's raining outside! Get me my galoshes");
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		long when = System.currentTimeMillis();
		
		android.app.Notification notification = new android.app.Notification(icon, tickerText, when);
		
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, NotificationActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		final int HELLO_ID = 1;
		
		mNotificationManager.notify(HELLO_ID, notification);
	}


	
	
}
