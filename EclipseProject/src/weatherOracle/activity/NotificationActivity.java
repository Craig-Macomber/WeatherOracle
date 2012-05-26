package weatherOracle.activity;

import java.util.ArrayList;
import java.util.List;

import weatherOracle.filter.ConditionRule;
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
		for (int i = 0;i<notificationList.size();i++) {
        	
			
        	LinearLayout ll = new LinearLayout(this); 
        	ll.setOrientation(1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            	     LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8, 4, 8, 4);
        	
            TextView name = new TextView(getApplicationContext());
            name.setText(notificationList.get(i).getName());
            name.setTextSize(2,25);
            
            ll.addView(name);
            
            if (notificationList.get(i).getWeatherData() != null) {
            	TextView conditionTag = new TextView(getApplicationContext());
            	conditionTag.setText("Will First Occur At:\n" + notificationList.get(i).getWeatherData().get(0).getTimeString());
            	ll.addView(conditionTag);
            }
            
            if (notificationList.get(i).getFilter() != null) {
            	TextView conditionTag = new TextView(getApplicationContext());
            	conditionTag.setText("With Condition(s):");
            	ll.addView(conditionTag);
            	List<ConditionRule> conditions = new ArrayList<ConditionRule>(notificationList.get(i).getFilter().getConditionRules());
            	for(int j = 0 ;j < conditions.size(); j++) {
                	TextView condition = new TextView(getApplicationContext());
                	condition.setText("\t" +conditions.get(j).toString());
                	ll.addView(condition);
                }	
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            mainView.addView(ll, layoutParams);
            
            
            
            
            
            
            
            
            
            

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

	//PLEASE CHANGE THIS SHIT TO STATIC AND PLEASE CALL THE SHIT FOR ME THANKS 
	public void redraw() {
    	mainView.removeAllViews();
    	populateNotificationList();
        displayNotifications();
	}
	
}
