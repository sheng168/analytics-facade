package shared.analytics;

import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.tagmanager.DataLayer;
import com.google.tagmanager.TagManager;

/**
 * Simple analytics facade.
 * 
 */
public class AnalyticsTagMgr extends AnalyticsService {
	static AnalyticsService me;
	
	private Context ctx;

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.jsy.android.shared.AnalyticsService#track(java.lang.String)
	 */
	@Override
	public void track(String name) {
	    // This call assumes the container has already been opened, otherwise events
	    // pushed to the DataLayer will not fire tags in that container.
		TagManager.getInstance(ctx).getDataLayer().push(DataLayer.mapOf("event", "openScreen",        // Event, Name of Open Screen Event.
	                                    "screenName", name));  // Name of screen name field, Screen name value.

	}

	@Override
	public void time(long millis, String... names) {		
		String category = names[0];
		String name = names.length >= 2 ? names[1] : null;
		String label = names.length >= 3 ? names[2] : null;
		
		TagManager.getInstance(ctx).getDataLayer().push(DataLayer.mapOf("event", "event",
                "event_category", category));
	}

	// custom init
	public static void init(Context ctx) {
	    if (me == null) {
			me = new AnalyticsTagMgr(ctx);
			
			Analytics.add(me);
		}
	}

	public AnalyticsTagMgr(Context ctx) {
		super();
		this.ctx = ctx;
	}

	public static void stop(Context ctx) {
		
	}
}
