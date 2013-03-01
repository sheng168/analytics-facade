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
		log.info("track: {}", name);
		for (AnalyticsService s : list) {
			s.track(name);
		}
	}
	
	public static void time(long millis, String... name) {
		log.info("time: {}, {}", millis, name[0]);
		for (AnalyticsService s : list) {
			s.time(millis, name);
		}
	}


	public static void track(Object obj) {
		track(obj.getClass().getSimpleName());
	}

	public static void track(Object obj, Object sub) {
		track(obj.getClass().getSimpleName() + "/" + sub);
	}

	static void add(AnalyticsService s) {
		list.add(s);
	}
}
