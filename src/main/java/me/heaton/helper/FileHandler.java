package me.heaton.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;

public class FileHandler {

	private File file;
	
	public FileHandler(File file) {
		this.file = file;
	}

	public void saveFile(FileItem item) throws IOException{
		try {
			item.write(this.file);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

}
