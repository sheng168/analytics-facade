package shared.analytics;

public abstract class AnalyticsService {

	/**
	 * @param name
	 */
	public abstract void track(String name);

	public void event(String name) {
		track("event/"+name);
	}
	
	public void screen(String name) {
		track("screen/"+name);
	}
	
	public void time(long millis, String... name) {
		track("event/"+name[0]);
	}
}