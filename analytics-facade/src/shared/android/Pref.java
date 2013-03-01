package shared.android;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Pref implements OnSharedPreferenceChangeListener, Closeable {
	org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Pref.class);
	
	/**
	 * keep object from getting garbage-collected 
	 * after registerOnSharedPreferenceChangeListener
	 */
	private static Set<Pref> set = Collections.synchronizedSet(new HashSet<Pref>());

	private SharedPreferences sp;
//	private String[] keys;
	private Set<String> keySet;
	
	public Pref(SharedPreferences sp, String... keys) {
		this.sp = sp;
//		this.keys = keys;
//		
//		Arrays.sort(this.keys);
		keySet = new HashSet<String>(Arrays.asList(keys));
		
		sp.registerOnSharedPreferenceChangeListener(this);
		set.add(this);
	}
	
	public Pref() {
		this(PreferenceManager.getDefaultSharedPreferences(null));
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		log.debug("prefChanged {}: {}", key, sharedPreferences.getAll().get(key));
//		if (Arrays.binarySearch(keys, key) >= 0) {
//			// we care about this key
//		}
		
		if (keySet.contains(key)) {
			// we care about this key
		}
	}

	@Override
	public void close() throws IOException {
		sp.unregisterOnSharedPreferenceChangeListener(this);
		set.remove(this);
	}
}
