package shared.analytics;

import android.content.Context;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.localytics.android.LocalyticsSession;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsLocalytics implements AnalyticsService {
	static AnalyticsService me;

	private static LocalyticsSession localyticsSession;

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
		localyticsSession.tagEvent(name);
		localyticsSession.close();
		localyticsSession.open();
	}

	// custom init
	public static void init(Context ctx, String key) {
		// Instantiate the object
		localyticsSession = new LocalyticsSession(ctx, key);
		localyticsSession.open();
		localyticsSession.upload();
		
		if (me == null) {
			me = new AnalyticsLocalytics();
			Analytics.add(me);
		}
	}

	public static void stop(Context ctx) {
		localyticsSession.close();
		localyticsSession.upload();
	}
}
