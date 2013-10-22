package shared.android;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.tagmanager.Container;

public class ContainerFacade {
	
	@SuppressWarnings("unchecked")
	public static <T> T get(final Container container, Class<T> interfaceClass) {
		InvocationHandler handler = new InvocationHandler() {			
			@Override
			public Object invoke(Object obj, Method method, Object[] args)
					throws Throwable {
				assert args.length == 0;
				
				final Class<?> returnType = method.getReturnType();
				final String name = method.getName();

				if (returnType.equals(String.class)) {
					final String string = container.getString(name);
					if (string.length() > 0)
						return string;
					else
						return name;
				} else if (returnType.equals(Boolean.class) || returnType.equals(boolean.class)) {
					return container.getBoolean(name);
				} else if (returnType.equals(Long.class) || returnType.equals(long.class)) {
					return container.getLong(name);
				} else if (returnType.equals(Double.class) || returnType.equals(double.class)) {
					return container.getDouble(name);
				} else {
					throw new IllegalArgumentException("Must be String, Boolean, Long or Double: " + returnType);
				}
			}
		};
		
		Object obj = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass},
				handler);
		return (T) obj;
	}

	public static <T> T get(Class<T> interfaceClass) {
		return get(ContainerHolder.getContainer(), interfaceClass);
	}

	public static String get(String name) {
		String s = ContainerHolder.getContainer().getString(name);
		final boolean debug = false; //BuildConfig.DEBUG;
		if (s.length() > 0)
			return debug?s + '!':s;
		else
			return debug?name + "*":name;
	}

	public static void translate(TextView... textViews) {
		for (TextView textView : textViews) {
			textView.setText(get(textView.getText().toString()));
		}
	}

	/**
	 * 
	 * @param textView or Buttons and other subclasses
	 */
	public static void translate(TextView textView) {
		if (textView == null)
			return;
		
		final String string = textView.getText().toString();
		if (string.length() > 0 && string.endsWith("")) {
			textView.setText(get(string.trim()));
		}
	}

	public static void translate(View view) {
		if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup)view;
			for (int i = 0; i < group.getChildCount(); i++) {
				View child = group.getChildAt(i);
				
				translate(child);
			}
		} else if (view instanceof TextView) {
			translate((TextView)view);
		}
	}

	public static void translate(Activity act) {
        act.setTitle(ContainerFacade.get(act.getTitle().toString()));
        //HierarchicalViewIdViewer.debugViewIds(act.findViewById(android.R.id.content), "views");
        // no easy way to get content view
	}
}
