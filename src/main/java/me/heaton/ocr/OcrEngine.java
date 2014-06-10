package me.heaton.ocr;

import java.awt.Rectangle;
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrEngine {

	private Tesseract instance;

	private Rectangle rect;
	
	public OcrEngine() {
		instance = Tesseract.getInstance();
	}

	public void chooseArea(String area) {
		String[] point = area.split(",");
		int x = Math.max(0, Integer.parseInt(point[0]));
		int y = Math.max(0, Integer.parseInt(point[1]));
		int w = Math.max(1, Integer.parseInt(point[2]));
		int h = Math.max(1, Integer.parseInt(point[3]));
		rect = new Rectangle(x, y, w, h);
	}

	public String result(File file) {
		try {
			return instance.doOCR(file, rect);
		} catch (TesseractException e) {
			return e.getMessage();
		}	
	}

}
