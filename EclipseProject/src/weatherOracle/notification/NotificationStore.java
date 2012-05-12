package weatherOracle.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.util.Log;

/**
 * A static (not instanced) store of the newest Notifications available.
 * 
 */
public abstract class NotificationStore {

	private static int filterVersionNumber = -1;

	// unmodifiableList of notifications
	private static List<Notification> notifications; 
	
	/**
	 * initializes the NotificationStore to have one element in it with the name "no data yet"
	 */
	public static void initializeNotificationStore()
	{
		notifications=new ArrayList<Notification>();
		notifications.add(Notification.make("no data yet",null,null));
	}
	
	/**
	 * update the notifications, if the filterVersionNumber is the newest we
	 * have seen. If the filterVersionNumber is lower than the highest seen, the
	 * passed notifications will be discarded.
	 * 
	 * @param notifications
	 *            the new notifications to store
	 * @param filterVersionNumber
	 *            the version number from the FilterStore for the filters that
	 *            generated these notifications
	 */
	public synchronized static void update(List<Notification> notifications,
			int filterVersionNumber) {
		if (filterVersionNumber >= NotificationStore.filterVersionNumber) {
			NotificationStore.filterVersionNumber = filterVersionNumber;
			// get ourself a copy of the passed list, and prevent it from being
			// modified.
			// Assumes the contained Notifications themselves won't be edited!
			NotificationStore.notifications = Collections
					.unmodifiableList(new ArrayList<Notification>(notifications));
			updated();
		}
	}

	private static void updated() {
		// TODO : send an event/update the UI/whatever
		Log.d("NotificationStore", "updated");
	}

	/**
	 * 
	 * @return unmodifiableList of Notifications containing the newest
	 *         Notifications available
	 */
	public synchronized static List<Notification> getNotifications() {
		return notifications;
	}

}
