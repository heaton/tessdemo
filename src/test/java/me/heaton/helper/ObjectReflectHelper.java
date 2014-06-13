package me.heaton.helper;

import java.lang.reflect.Field;

public class ObjectReflectHelper {

	public static <T> T getFeild(Object obj, String fieldName) {
		try {
			Field f = obj.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			return (T) f.get(obj);
		} catch (Exception e) {
			return null;
		}
	}

}
