package me.heaton.helper;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

public class UploadHandler {

	private static final String TESSDIR = "/tessdir/";

	private ServletContext context;

	private FileName fileName;
	private AreaList areas;

	private ImageHandler image;

	public UploadHandler(ServletContext context) {
		this.context = context;
	}

	public JSONObject saveFileAndOcr(HttpParam reqs) throws IOException {
		FileItem file = reqs.getFile("file");
		if (file == null) {
			return wrongJson("no file to find");
		}
		fileName = new FileName(TESSDIR + file.getName());
		save(file);
		
		return processImg(reqs);
	}

	private void save(FileItem file)
			throws IOException {
		FileHandler fileHandler = new FileHandler(oriImgFile());
		fileHandler.saveFile(file);
	}

	private JSONObject processImg(HttpParam reqs) throws IOException {
		String command = reqs.getParam("command");

		String whiteList = reqs.getParam("white_list");
		String language = reqs.getParam("language");
		areas = new AreaList(reqs.getParam("ocr_areas"));

		String needOcr = reqs.getParam("ocr");

		image = new ImageHandler(oriImgFile());
		image.filter(command);
		image.findEage();
		if("yes".equals(needOcr)) {
		    image.ocr(whiteList, language, areas);
		}
		image.saveImage(fileName.suffix(), newImgFile());

		return successJson();
	}

	public JSONObject checkFileAndOcr(HttpParam reqs) throws IOException {
		fileName = new FileName(reqs.getParam("file_name"));
		if(fileName.isEmpty()){
			return wrongJson("miss file_name");
		}
		return processImg(reqs);
	}

	private File oriImgFile() {
		return new File(context.getRealPath(fileName.get()));
	}

	private File newImgFile() {
		return new File(context.getRealPath(fileName.newName()));
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
		resp.put("imgPath", context.getContextPath() + fileName.newName());
		resp.put("imgName", fileName.get());
		resp.put("ocrResult", image.ocrResult());
		return resp;
	}

}
