package shared.android;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
		if (s.length() > 0)
			return s;
		else
			return name + "*";
	}
}
