package weatherOracle.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weatherOracle.activity.NotificationActivity;
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
	 * initializes the NotificationStore to have one element in it with the name
	 * "no data yet"
	 */
	private synchronized static void lazyInit() {
		if (notifications==null) {
			notifications = new ArrayList<Notification>();
			notifications.add(Notification.makeNoData());
			filterVersionNumber = -1;
		}
	}

	/**
	 * initializes the NotificationStore to have one element in it with the name
	 * "no data yet"
	 */
	public static void initializeNotificationStore(boolean isOnline) {
		notifications = new ArrayList<Notification>();
		if (!isOnline) {
			notifications.add(Notification.makeNoConnection());
		} else {
			notifications.add(Notification.makeNoData());
		}
		filterVersionNumber = -1;
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
		lazyInit();
		if (filterVersionNumber >= NotificationStore.filterVersionNumber) {
			Log.d("NotificationStore", "*********new data*********");
			NotificationStore.filterVersionNumber = filterVersionNumber;
			// get ourself a copy of the passed list, and prevent it from being
			// modified.
			// Assumes the contained Notifications themselves won't be edited!
			List<Notification> newNotifications=new ArrayList<Notification>(notifications);
			Collections.sort(newNotifications);
			NotificationStore.notifications = Collections.unmodifiableList(newNotifications);
			Log.d("NotificationStore", "*********saved data*********");
			updated();
			Log.d("NotificationStore", "*********updated*********");
		} else {
			Log.d("NotificationStore", "*********OLD data!*********");
		}
	}
	
	


	
	private static void updated() {
		// TODO : send an event/update the UI/whatever
		Log.d("NotificationStore", "*********update*********");
		NotificationActivity.asyncUpdate();
	}

	/**
	 * 
	 * @return unmodifiableList of Notifications containing the newest
	 *         Notifications available
	 */
	public synchronized static List<Notification> getNotifications() {
		lazyInit();
		return notifications;
	}

}
