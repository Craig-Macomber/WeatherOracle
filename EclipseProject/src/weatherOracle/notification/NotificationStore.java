package weatherOracle.notification;

import java.util.ArrayList;
import java.util.List;

public abstract class NotificationStore {

	private static int filterVersionNumber = -1;
	private static List<Notification> notifications; // TODO : init this to a list on one "no data yet" notification

	public synchronized static void update(List<Notification> notifications, int filterVersionNumber) {
		if (filterVersionNumber>=NotificationStore.filterVersionNumber){
			NotificationStore.filterVersionNumber=filterVersionNumber;
			NotificationStore.notifications=new ArrayList<Notification>(notifications);
			updated();
		}
	}

	private static void updated(){
		// TODO
	}

}
