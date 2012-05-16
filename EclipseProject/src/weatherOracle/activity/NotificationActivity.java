package weatherOracle.activity;

import java.util.List;

import weatherOracle.notification.Notification;
import weatherOracle.notification.NotificationStore;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NotificationActivity extends Activity {
	
	/**
	 *  List of Notifications to be displayed by this activity
	 */
	List<Notification> notificationList;
	LinearLayout mainView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        mainView = (LinearLayout)findViewById(R.id.notification_activity_linear_layout);
        populateNotificationList();
        displayNotifications();	
        
    }

    public void onResume() {
    	super.onResume();
    	mainView.removeAllViews();
    	populateNotificationList();
        displayNotifications();	
    }
    
    public void onWindowFocusChanged(boolean hasFocus){
    	super.onWindowFocusChanged(hasFocus);
    	if(hasFocus) {
 	    	mainView.removeAllViews();
 	    	populateNotificationList();
 	        displayNotifications();
 		} else {
 			mainView.removeAllViews();
 		}
 	}
    
    /**
     * Populate and update the notificationList Field 
     */
	private void populateNotificationList() {
		
		notificationList = NotificationStore.getNotifications();
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
