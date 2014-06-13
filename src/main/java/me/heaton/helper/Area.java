package me.heaton.helper;

public class Area {

	private static final String REGEX_COORDINATE = "^(\\d+,){3}\\d+(:(\\w|[;/\\-\\.])*)?$";

	public final String name;
	public final int x, y, w, h;
	private String[] params;
	
	private Area(String name, String value) {
		this.name = name;
		String [] values = value.split(":");
		String[] point = values[0].split(",");
		x = Math.max(0, Integer.parseInt(point[0]));
		y = Math.max(0, Integer.parseInt(point[1]));
		w = Math.max(1, Integer.parseInt(point[2]));
		h = Math.max(1, Integer.parseInt(point[3]));
		if(values.length>1) {
			params = values[1].split(";");
		}
	}

	public String getParam(int i) {
		if(params != null && i<params.length) {
			return params[i];
		}
		return null;
	}

	/**
	 * typical value: 10,20,30,40:p1;p2
	 * @param name
	 * @param value
	 * @return
	 */
	public static Area create(String name, String value) {
		if(!isRegular(value)) {
			return null;
		}
		return new Area(name, value);
	}

	private static boolean isRegular(String value) {
		return value!=null && value.matches(REGEX_COORDINATE);
	}

}
