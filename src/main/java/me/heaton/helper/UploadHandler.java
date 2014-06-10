package me.heaton.helper;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

public class UploadHandler {

	private static final String TESSDIR = "/tessdir/";

	private ServletContext context;

	private String filePath;
	private String ocrResult;

	public UploadHandler(ServletContext context) {
		this.context = context;
	}

	public JSONObject operate(FileItemList reqs) throws IOException {
		FileItem file = reqs.getFile("file");
		if (file == null) {
			return wrongJson("no file to find");
		}
		filePath = TESSDIR + file.getName();
		ocrResult = saveThenOcr(file);

        return successJson();
	}

	private String saveThenOcr(FileItem file)
			throws IOException {
		String realPath = context.getRealPath(filePath);
		FileHandler fileHandler = new FileHandler(realPath);
		fileHandler.saveFile(file);
		return fileHandler.ocr();
	}

	private JSONObject wrongJson(String message) {
		JSONObject resp = new JSONObject();
		resp.put("result", "error");
		resp.put("message", message);
		return resp;
	}

	private JSONObject successJson() {
		JSONObject resp = new JSONObject();
		resp.put("result", "success");
		resp.put("imgPath", context.getContextPath() + filePath);
		resp.put("imgName", filePath);
		resp.put("ocrResult", ocrResult);
		return resp;
	}

}
