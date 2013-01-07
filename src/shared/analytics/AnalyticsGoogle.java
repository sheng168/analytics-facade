package shared.analytics;

import android.content.Context;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsGoogle implements AnalyticsService {
	static AnalyticsService me;
	public static GoogleAnalyticsTracker tracker = GoogleAnalyticsTracker
			.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
		tracker.trackPageView(name);
	}

	// custom init
	public static void init(Context ctx, String key, int dispatchSeconds) {
		// Start the tracker in manual dispatch mode...
		// tracker.startNewSession("UA-YOUR-ACCOUNT-HERE", this);
		// ...alternatively, the tracker can be started with a dispatch interval
		// (in seconds).
		tracker.startNewSession(key, dispatchSeconds, ctx); // TODO change 0 before release

		if (me == null) {
			me = new AnalyticsGoogle();
			Analytics.add(me);
		}
	}

	public static void stop(Context ctx) {
		tracker.stopSession();
	}
}
