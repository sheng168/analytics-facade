package shared.android;

import java.util.UUID;

import android.content.Context;

public class Installation {
	private static final String KEY = Installation.class.getName();

	public static synchronized String getId(Context ctx) {
		String id = Settings.get(ctx, KEY, null);
		if (id == null) {
			id = UUID.randomUUID().toString();
			Settings.set(ctx, KEY, id);
		}
		
		return id;
	}
}
