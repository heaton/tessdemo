package me.heaton.helper;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class StringParams implements HttpParam{

	private final Map<String, String[]> map;
	
	public StringParams(Map<String, String[]> parameterMap) {
		this.map = parameterMap;
	}

	@Override
	public String getParam(String name) {
		String[] params = getParams(name);
		if(params!=null && params.length>0) {
			return params[0];
		}
		return null;
	}

	@Override
	public FileItem getFile(String name) {
		return null;
	}

	@Override
	public String[] getParams(String name) {
		return map.get(name);
	}

}
