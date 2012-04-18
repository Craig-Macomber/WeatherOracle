package weatherOracle.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class NotificationStore {

	private static int filterVersionNumber = -1;
	
	// unmodifiableList of notifications
	private static List<Notification> notifications; // TODO : init this to a list on one "no data yet" notification

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
	}

}
