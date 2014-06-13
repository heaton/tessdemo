package me.heaton.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class FileItemList implements HttpParam {

	private Map<String, FileItem> params;
	
	public FileItemList(List<FileItem> items){
		params = new HashMap<String, FileItem>();
		init(items);
	}

	private void init(List<FileItem> items) {
		for(FileItem item : items){
			params.put(item.getFieldName(), item);
		}
	}
	
	@Override
	public String getParam(String name){
		FileItem item = params.get(name);
		if(item==null) return null;
		return item.getString();
	}
	
	@Override
	public FileItem getFile(String name){
		return params.get(name);
	}

	@Override
	public String[] getParams(String name) {
		return null;
	}
	
}
