package shared.analytics;

import android.content.Context;

import com.flurry.android.FlurryAgent;


/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsFlurry implements AnalyticsService {
	static AnalyticsService me;
	
	/* (non-Javadoc)
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	
	@Override
	public void track(String name) {
		FlurryAgent.logEvent(name);
	}
	
	// custom init
	public static void init(Context ctx, String key) {
		FlurryAgent.onStartSession(ctx, key);
		
		if (me == null) {
			me = new AnalyticsFlurry();
			Analytics.add(me);
		}
	}
	public static void stop(Context ctx) {
		FlurryAgent.onEndSession(ctx);
	}
}
