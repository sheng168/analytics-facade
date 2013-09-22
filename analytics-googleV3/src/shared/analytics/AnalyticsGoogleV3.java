package shared.analytics;

import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsGoogleV3 extends AnalyticsService {
	static AnalyticsService me;
	private Context ctx;

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
		EasyTracker.getInstance(ctx).send(
				MapBuilder.createAppView()
				.set(Fields.SCREEN_NAME, name)
				.build());
		
//		GAServiceManager.getInstance().dispatchLocalHits();
	}

	@Override
	public void time(long millis, String... names) {		
		String category = names[0];
		String name = names.length >= 2 ? names[1] : null;
		String label = names.length >= 3 ? names[2] : null;
		
		EasyTracker.getInstance(ctx).send(MapBuilder.createTiming(category, millis, name, label).build());
	}

	// custom init
	public static void init(Context ctx, int dispatchSeconds) {
		// Start the tracker in manual dispatch mode...
		// tracker.startNewSession("UA-YOUR-ACCOUNT-HERE", this);
		// ...alternatively, the tracker can be started with a dispatch interval
		// (in seconds).
		GoogleAnalytics.getInstance(ctx);
		EasyTracker.getInstance(ctx);
		GAServiceManager.getInstance().setLocalDispatchPeriod(dispatchSeconds);
		
		if (me == null) {
			me = new AnalyticsGoogleV3();
			Analytics.add(me);
		}
	}

	public static void stop(Context ctx) {
		
	}
}
