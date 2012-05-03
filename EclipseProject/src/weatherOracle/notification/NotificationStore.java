package weatherOracle.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

// A static (not instanced) store of the newest Notifications available.
public abstract class NotificationStore {

	private static int filterVersionNumber = -1;
	
	// unmodifiableList of notifications
	private static List<Notification> notifications; // TODO : init this to a list on one "no data yet" notification
	
	// update the notifications, if the filterVersionNumber is the newest we have seen
	public synchronized static void update(List<Notification> notifications, int filterVersionNumber) {
		if (filterVersionNumber>=NotificationStore.filterVersionNumber){
			NotificationStore.filterVersionNumber=filterVersionNumber;
			// get ourself a copy of the passed list, and prevent it from being modified.
			// Assumes the contained Notifications themselves won't be edited!
			NotificationStore.notifications=Collections.unmodifiableList(new ArrayList<Notification>(notifications));
			updated();
		}
	}

	private static void updated(){
		// TODO : send an event/update the UI/whatever
		Log.d("NotificationStore","updated");
	}

}
