package shared.analytics;

public class Start {
	long start = System.currentTimeMillis();
	
	public void to(String name) {
		Analytics.time(System.currentTimeMillis()-start, name);
	}
}
