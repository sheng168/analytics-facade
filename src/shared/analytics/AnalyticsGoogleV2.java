package shared.analytics;

import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GAServiceManager;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsGoogleV2 extends AnalyticsService {
	static AnalyticsService me;

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
		EasyTracker.getTracker().sendView(name);
	}

	@Override
	public void time(long millis, String... names) {		
		String name = names.length >= 2 ? names[1] : null;
		String label = names.length >= 3 ? names[2] : null;
		
		EasyTracker.getTracker().sendTiming(names[0], millis, name, label);;
	}

	// custom init
	public static void init(Context ctx, int dispatchSeconds) {
		// Start the tracker in manual dispatch mode...
		// tracker.startNewSession("UA-YOUR-ACCOUNT-HERE", this);
		// ...alternatively, the tracker can be started with a dispatch interval
		// (in seconds).
		EasyTracker.getInstance().setContext(ctx);
		GAServiceManager.getInstance().setDispatchPeriod(dispatchSeconds);
		
		if (me == null) {
			me = new AnalyticsGoogleV2();
			Analytics.add(me);
		}
	}

	public static void stop(Context ctx) {
		
	}
}
