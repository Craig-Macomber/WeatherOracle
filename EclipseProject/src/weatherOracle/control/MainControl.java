package weatherOracle.control;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import weatherOracle.reader.NoaaReader;
import weatherOracle.reader.Reader;

// built using http://www.javapractices.com/topic/TopicAction.do?Id=54 as an example
// should use http://developer.android.com/guide/topics/fundamentals/services.html
/**
 * Provides functions for managing and running updates of notifications.
 */
public abstract class MainControl {
	private static Reader reader = new NoaaReader();

	private static final int NUM_THREADS = 1;
	private static final ScheduledExecutorService fScheduler = Executors
			.newScheduledThreadPool(NUM_THREADS);
	private static final boolean DONT_INTERRUPT_IF_RUNNING = true;
	private static final long fDelayBetweenRuns = 600; // in seconds
	private static ScheduledFuture<?> updateFuture;

	/**
	 * Schedule an update of notifications
	 */
	public synchronized static void startUpdate() {
		fScheduler.schedule(new UpdateTask(reader), 0, TimeUnit.SECONDS);
	}

	/**
	 * Start automatic updates, and do any other startup duties
	 */
	public synchronized static void start() {
		startAutoUpdate();
	}

	/**
	 * Stop timed auto updates
	 */
	public synchronized static void stopAutoUpdate() {
		updateFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
		updateFuture = null;
	}

	/**
	 * Start timed auto updates, or do nothing if already started. Next update
	 * will be scheduled to run immediately if starting.
	 */
	private synchronized static void startAutoUpdate() {
		if (updateFuture == null) {
			Runnable task = new UpdateTask(reader);
			updateFuture = fScheduler.scheduleWithFixedDelay(task, 0,
					fDelayBetweenRuns, TimeUnit.SECONDS);
		}
	}

}
