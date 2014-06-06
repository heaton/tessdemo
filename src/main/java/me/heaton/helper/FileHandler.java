package me.heaton.helper;

import java.io.File;
import java.io.IOException;

import me.heaton.ocr.OcrEngine;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class FileHandler {

	private File file;
	
	public FileHandler(String realPath) {
		file = new File(realPath);
	}

	public void saveFile(FileItem item) throws IOException{
		try {
			item.write(this.file);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public String ocr() {
		OcrEngine ocr = new OcrEngine();
		return ocr.result(file);
	}

}
