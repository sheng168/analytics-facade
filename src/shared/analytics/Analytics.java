package shared.analytics;

import java.util.ArrayList;


/**
 * Simple analytics facade.
 * 
 */
public class Analytics {
	static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Analytics.class);
	
	static ArrayList<AnalyticsService> list = new ArrayList<AnalyticsService>();
	
	/**
	 * @param name
	 */
	public static void track(String name) {
		for (AnalyticsService s : list) {
			s.track(name);
		}
		log.trace("track {}", name);
	}

	public static void track(Object obj) {
		track(obj.getClass().getSimpleName());
	}

	static void add(AnalyticsService s) {
		list.add(s);
	}
}
