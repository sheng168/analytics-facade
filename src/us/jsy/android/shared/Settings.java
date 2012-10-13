package us.jsy.android.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * very thin wrapper just to save you time looking for API in Android's docs
 * @author shengyu
 *
 */
public class Settings {
	public static void set(Context ctx, String key, String value) {
		SharedPreferences sp = getSharedPreferences(ctx);
		sp.edit().putString(key, value).commit();
	}
	
	public static String get(Context ctx, String key, String ifNull) {
		SharedPreferences defaultSharedPreferences = getSharedPreferences(ctx);
		return defaultSharedPreferences.getString(key, ifNull);
	}
	
	public static void set(Context ctx, int resId, String value) {
		String key = ctx.getString(resId);
		set(ctx, key, value);
	}

	public static String get(Context ctx, int resId, String ifNull) {
		String key = ctx.getString(resId);
		return get(ctx, key, ifNull);
	}

	public static boolean get(Context ctx, int resId, boolean ifNull) {
		String key = ctx.getString(resId);
		SharedPreferences defaultSharedPreferences = getSharedPreferences(ctx);
		return defaultSharedPreferences.getBoolean(key, ifNull);
	}

	public static int get(Context ctx, int resId, int ifNull) {
		try {
			String key = ctx.getString(resId);
			SharedPreferences defaultSharedPreferences = getSharedPreferences(ctx);
			return defaultSharedPreferences.getInt(key, ifNull);
		} catch (Exception e) {
			e.printStackTrace();
			return ifNull;
		}
	}

	public static SharedPreferences getSharedPreferences(Context ctx) {
		SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return defaultSharedPreferences;
	}
}
