package weatherOracle.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import weatherOracle.filter.ConditionRule;
import weatherOracle.filter.Filter;
import weatherOracle.notification.Notification;
import weatherOracle.notification.NotificationStore;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class NotificationActivity extends Activity {
	
	/**
	 *  List of Notifications to be displayed by this activity
	 */
	List<Notification> notificationList;
	LinearLayout mainView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        instance=this;
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        mainView = (LinearLayout)findViewById(R.id.notification_activity_linear_layout);
        populateNotificationList();
        displayNotifications();	
        
    }

    private void updateDisplay(){
    	mainView.removeAllViews();
    	populateNotificationList();
        displayNotifications();	
    }
    
    public void onResume() {
    	instance=this;
    	super.onResume();
    	updateDisplay();
    }
    
    public void onWindowFocusChanged(boolean hasFocus){
    	super.onWindowFocusChanged(hasFocus);
    	if(hasFocus) {
    		updateDisplay();
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
		
		try {
			String tickerText = "";
			if(notificationList.size()==1) {
				tickerText = notificationList.size()+" new notification";
				
				/* Show information about a single notification
				statusBarNotification(R.drawable.icon,
					notificationList.get(0).getName(),
					"WeatherOracle",
					notificationList.get(0).getName() + ". Location:" + notificationList.get(0).getFilter().getLocationName()
						+ ". First Occur at" + notificationList.get(0).getWeatherData().get(0).getTimeString());
				*/
			} else if(notificationList.size()>1) {
				tickerText = notificationList.size() + " new notifications";
			}
			statusBarNotification(R.drawable.icon, tickerText, "WeatherOracle", tickerText);
		} catch (Exception e) {
			
		}
		
		for (int i = 0;i<notificationList.size();i++) {
			
			boolean firstIteration = false;
 			boolean lastIteration = false;
 			if(i == 0){
 				firstIteration = true;
 			}
 			if(i == notificationList.size() - 1){
 				lastIteration = true;
 			}
			
 			// parentll represents an entire on screen notification element; it's first child is
 			// the top divider ... its next is all of the main content of the notification ... and
 			// its third and last child is the bottom divider
 			final LinearLayout parentll = new LinearLayout(this);
 			parentll.setOrientation(LinearLayout.VERTICAL);
 			
 			// set up top divider and add to parent
 			final View divider = new View(this);
 			divider.setBackgroundColor(R.color.grey);
 			LayoutParams dividerParams;
 			if(firstIteration){
 				dividerParams = new LayoutParams(LayoutParams.FILL_PARENT, 2);
 			} else {
 				dividerParams = new LayoutParams(LayoutParams.FILL_PARENT, 1);
 			}
 				//dividerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
 			parentll.addView(divider, dividerParams);
 			
 			// set up ll view that will hold main content of notification
 			LinearLayout ll = new LinearLayout(this);
 			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
           	     LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
 				 layoutParams.setMargins(8, 4, 8, 4);
 			parentll.addView(ll,layoutParams);
 			
 			// set up bottom divider and add to parent
 			final View divider2 = new View(this);
 			divider2.setBackgroundColor(R.color.grey);
 			LayoutParams dividerParams2;
 			if(lastIteration){
 				dividerParams2 = new LayoutParams(LayoutParams.FILL_PARENT, 2);
 			} else {
 				dividerParams2 = new LayoutParams(LayoutParams.FILL_PARENT, 1);
 			}
 			//dividerParams2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
 			parentll.addView(divider2, dividerParams2);
 			
 			RelativeLayout nameAndDetails = new RelativeLayout(this);
 			ll.addView(nameAndDetails);
 		//	LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 50);
 		//	ll.addView(nameAndDetails, nameParams);
           
 			TextView name = new TextView(getApplicationContext());
            name.setText(notificationList.get(i).getName());
            name.setTextSize(2,25);
            name.setTextColor(Color.BLACK);
            RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
            nameAndDetails.addView(name, nameParams);
            
            
            ll.setOrientation(0);
      //      ll.addView(name);
            final int index = i;
            Button internet = new Button(getApplicationContext());
 			internet.setGravity(Gravity.CENTER_VERTICAL);
 			
 			
          
            
            internet.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					try {
						URL url;
						/*url = new URL("http://forecast.weather.gov/MapClick.php?lat="
								+ lat + "&lon=" + lon);*/
						
						Filter currentFilter = notificationList.get(index).getFilter();
						
						double lat = currentFilter.getLocation().lat;
						double lon = currentFilter.getLocation().lon;
						
						String conditionSpecifier = "";
						int timeSpecifier = 0;
						long timeDiff = 0;
						TimeZone filterTimeZone = notificationList.get(index).getWeatherData().get(0).getTimeZone();
						
						for(ConditionRule cr : currentFilter.getConditionRules()) {
							if (!conditionSpecifier.contains(ConditionRule.geturlSpecifier(cr.getCondition()))) {
								conditionSpecifier += ConditionRule.geturlSpecifier(cr.getCondition()) + "&";	
							}
						}
						
						if (notificationList.get(index).getWeatherData() != null) {
							timeDiff = (notificationList.get(index).getWeatherData().get(0).getMillisTime() 
									  	- Calendar.getInstance(filterTimeZone).getTimeInMillis())/(1000*3600);
			            	timeSpecifier = (int) timeDiff;
			            	if (timeSpecifier < 0) {
			            		timeSpecifier = 0;
			            	}
			            }
						
						url = new URL("http://forecast.weather.gov/MapClick.php?"
								+ conditionSpecifier
								+ "&w3u=1&AheadHour="
								+ timeSpecifier
								+ "&Submit=Submit&FcstType=digital&textField1="
								+ lat
								+ "&textField2="
								+ lon
								+ "&site=all&unit=0&dd=0&bw=0");
						
						Log.d("NOTIFICATION ACTIVITY", "http://forecast.weather.gov/MapClick.php?"
								+ conditionSpecifier
								+ "&w3u=1&AheadHour="
								+ timeSpecifier
								+ "&Submit=Submit&FcstType=digital&textField1="
								+ lat
								+ "&textField2="
								+ lon
								+ "&site=all&unit=0&dd=0&bw=0");
							
						Intent myIntent = new Intent(v.getContext(), InternetForecast.class);
		                myIntent.putExtra("url", url);
		                startActivity(myIntent);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
            
            internet.setText("Details");
            if((notificationList.get(i).getName().equals("No Internet Connection") || notificationList.get(i).getName().equals("No Notification Yet")) && notificationList.get(i).getFilter() == null && notificationList.get(i).getWeatherData() == null || notificationList.get(i).getName().contains("No data at location")) {
            	//dont add the connect to internet button
            } else {
            	nameAndDetails.addView(internet);
            	LayoutParams params = (RelativeLayout.LayoutParams)internet.getLayoutParams();
     			params.setMargins(0, 6, -2, 4);
     			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
     			internet.setLayoutParams(params); //causes layout update
            }
            
            
            ll.setOrientation(1);
            
            
            if (notificationList.get(i).getWeatherData() != null) {
            	TextView conditionTag = new TextView(getApplicationContext());
            	//conditionTag.setText();
            	conditionTag.setText("Will First Occur on: " + notificationList.get(i).getWeatherData().get(0).getTimeString() + 
            			"\nWill Occur during: " + notificationList.get(i).getDays());
            	ll.addView(conditionTag);
            }
            
            
            
            if (notificationList.get(i).getFilter() != null) {
            	TextView locationTag = new TextView(getApplicationContext());
            	String location = "";
            	if (notificationList.get(i).getFilter().getLocationName() != null) {
            		location = notificationList.get(i).getFilter().getLocationName();
            		locationTag.setText("Location: " + location);
            		ll.addView(locationTag);
            	}
            	
            	

            	
            	TextView conditionTag = new TextView(getApplicationContext());
            	conditionTag.setText("With Condition(s):");
            	ll.addView(conditionTag);
            	List<ConditionRule> conditions = new ArrayList<ConditionRule>(notificationList.get(i).getFilter().getConditionRules());
            	for(int j = 0 ;j < conditions.size(); j++) {
                	TextView condition = new TextView(getApplicationContext());
                	String range;
                	double min = conditions.get(j).getMinMax().first;
                	double max = conditions.get(j).getMinMax().second;
            		if (min == Double.MIN_VALUE && max == Double.MAX_VALUE) {
            			range = "Any Value/Amount";
            		} else if (min == Double.MIN_VALUE) {
            			range = "Up To " + max + ConditionRule.getUnits(conditions.get(j).getCondition());
            		} else if (max == Double.MAX_VALUE) {
            			range = "From " + min + ConditionRule.getUnits(conditions.get(j).getCondition()) + " and higher.";
            		} else {
            			range = min + ConditionRule.getUnits(conditions.get(j).getCondition()) + " to " + max + ConditionRule.getUnits(conditions.get(j).getCondition()) + ".";	
            		}	
                	condition.setText("\t" + conditions.get(j).getCondition() + ": " + range);
                	ll.addView(condition);
                }	
            }
            
            
            mainView.addView(parentll);
        }
	}
	
	public void statusBarNotification(int icon,CharSequence tickerText,CharSequence contentTitle,CharSequence contentText)
	{
		//Example: statusBarNotification(R.drawable.rain,"It's raining!","WeatherOracle","It's raining outside! Get me my galoshes");
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		long when = System.currentTimeMillis();
		
		android.app.Notification notification = new android.app.Notification(icon, tickerText, when);
		
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, HomeMenuActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		final int HELLO_ID = 1;
		
		mNotificationManager.notify(HELLO_ID, notification);
	}

	
	private static class Updater implements Runnable {
		public void run() {
			instance.updateDisplay();
		}
	}
	
	private static NotificationActivity instance=null;
	public static void asyncUpdate(){
		synchronized(instance){
			instance.runOnUiThread(new Updater());
		}
	}
	
}
