package shared.analytics;

import java.util.Map;

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
		tracker.set(Fields.SCREEN_NAME, name);
		
		Map<String, String> param = MapBuilder.createAppView()
				.build();
		tracker.send(param);
		
		GAServiceManager.getInstance().dispatchLocalHits();
	}
	@Override
	public void event(String name) {
		Map<String, String> build = MapBuilder.createEvent(name, "action", "label", 1L)
				.build();
		tracker.send(build);
	}

	@Override
	public void time(long millis, String... names) {
		String category = names[0];
		String name = names.length >= 2 ? names[1] : "name";
		String label = names.length >= 3 ? names[2] : "label";

		Map<String, String> build = MapBuilder.createTiming(category, millis, name, label)
				.build();
		tracker.send(build);
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
