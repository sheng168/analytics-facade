package shared.analytics;

import android.content.Context;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsGoogleTracker extends AnalyticsService {
	static AnalyticsService me;
	// private Context ctx;
	Tracker tracker;

	public AnalyticsGoogleTracker(Tracker tracker) {
		super();
		this.tracker = tracker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
		tracker.send(MapBuilder.createAppView().set(Fields.SCREEN_NAME, name)
				.build());
	}
	@Override
	public void event(String name) {
		tracker.send(MapBuilder.createEvent(name, null, null, null)
				.build());
	}

	@Override
	public void time(long millis, String... names) {
		String category = names[0];
		String name = names.length >= 2 ? names[1] : null;
		String label = names.length >= 3 ? names[2] : null;

		tracker.send(MapBuilder.createTiming(category, millis, name, label)
				.build());
	}

	// custom init
	public static void init(Context ctx, int dispatchSeconds, String trackingId) {
		Tracker tracker = GoogleAnalytics.getInstance(ctx).getTracker(
				trackingId);

		GAServiceManager.getInstance().setLocalDispatchPeriod(dispatchSeconds);

		if (me == null) {
			me = new AnalyticsGoogleTracker(tracker);
			Analytics.add(me);
		}
	}

	public static void stop(Context ctx) {

	}

}
