package me.heaton.helper;

import org.apache.commons.fileupload.FileItem;

public interface HttpParam {

	FileItem getFile(String name);

	String getParam(String name);

	String[] getParams(String name);
}
