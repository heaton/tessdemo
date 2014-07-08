package me.heaton.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.heaton.image.ImageFilter;
import me.heaton.ocr.OcrEngine;

public class ImageHandler {

	private OcrEngine ocrEngine;
	private ImageFilter imageFilter;

	private BufferedImage image;
	private StringBuilder ocrResult;

	public ImageHandler(File file) throws IOException{
		image = ImageIO.read(file);
		imageFilter = new ImageFilter(image);
		ocrEngine = new OcrEngine();
		ocrResult = new StringBuilder();
	}

	public void ocr(String whiteList, String language, AreaList areas) throws IOException {
		ocrEngine.setWhiteList(whiteList);
		ocrEngine.setLanguage(language);
		ocr(areas);
	}

	public void filter(String command) throws IOException {
		image = imageFilter.filterFromCommand(command);
	}

	private void ocr(AreaList oreas) {
		ocrResult.append(ocrEngine.result(image));
		for(Area area : oreas) {
			ocrEngine.chooseArea(area);
			ocrResult.append("\n")
					.append(area.name).append(": ")
					.append(ocrEngine.result(image));
		}
	}

	public void saveImage(String fileType, File file) throws IOException {
		ImageIO.write(image, fileType, file);
	}

	public String ocrResult() {
		return ocrResult.toString();
	}

}
