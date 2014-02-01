package shared.android;

import android.content.Context;

import com.google.tagmanager.Container;
import com.google.tagmanager.ContainerOpener;
import com.google.tagmanager.ContainerOpener.OpenType;
import com.google.tagmanager.Logger.LogLevel;
import com.google.tagmanager.TagManager;
import com.google.tagmanager.TagManager.RefreshMode;

/**
 * Singleton to hold the GTM Container (since it should be only created once
 * per run of the app).
 */
public class ContainerHolder {
  private static Container container;
  
  /**
   * Utility class; don't instantiate.
   */
  private ContainerHolder() {
	
  }
  
  public static Container getContainer() {
    return container;
  }
  
  public static void setContainer(Container c) {
    container = c;
  }
  
  public static Container getContainer(Context ctx, String containerId, boolean useLocal) {
		TagManager tagManager = TagManager.getInstance(ctx);
		tagManager.setRefreshMode(useLocal?RefreshMode.DEFAULT_CONTAINER:RefreshMode.STANDARD);

		// Modify the log level of the logger to print out not only
		// warning and error messages, but also verbose, debug, info messages.
		tagManager.getLogger().setLogLevel(LogLevel.VERBOSE);

		// The containerAvailable method will be called as soon as one of the
		// following happens:
		// 1. a saved container is loaded
		// 2. if there is no saved container, a network container is loaded
		// 3. the 2-second timeout occurs

		container = ContainerOpener.openContainer(tagManager, containerId,
				OpenType.PREFER_NON_DEFAULT,
				1L).get();
		
		if (container.getBoolean("refresh"))
			container.refresh();
		
		try {
			LogLevel level = LogLevel.valueOf(container.getString("logLevel"));
			tagManager.getLogger().setLogLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String v = container.getString("app version");
		System.out.println("app version:" + v);
		return container;
  }

}
