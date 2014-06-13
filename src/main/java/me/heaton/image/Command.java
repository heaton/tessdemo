package me.heaton.image;

import java.util.Arrays;

public class Command {

	private final static String NAME_PARAM_SPLIT = ":";

	public final String name;
	private String[] params;
	
	public Command(String c) {
		String[] cs = c.split(NAME_PARAM_SPLIT);
		name = cs[0];
		params = Arrays.copyOfRange(cs, 1, cs.length);
	}

	public String getParam(int i) {
		return getParam(i, null);
	}

	public String getParam(int i, String def) {
		if(i >= params.length) {
			return def;
		}
		return params[i];
	}

	public float getFloatParam(int i, float def) {
		if(i >= params.length) {
			return def;
		}
		return Float.parseFloat(params[i]);
	}

	public int getIntParam(int i, int def) {
		if(i >= params.length) {
			return def;
		}
		return Integer.parseInt(params[i]);
	}

}
