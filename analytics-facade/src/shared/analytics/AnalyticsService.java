package shared.analytics;

public abstract class AnalyticsService {

	/**
	 * @param name
	 */
	public abstract void track(String name);

	public void time(long millis, String... name) {
		track(name[0]);
	}
}