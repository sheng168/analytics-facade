package us.jsy.android.shared;

import android.net.Uri;

public class Constants {
	// String is ok as it'll be copied into the referencing project
	public static final String LAT = "lat";
	public static final String TEST = "test";
	public static final Uri uri = Uri.parse("timetable://jsy.us/ok");
	
	public static String getShared() {
		System.out.println("getShared");
		return TEST;
	}
}
