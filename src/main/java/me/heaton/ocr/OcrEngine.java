package me.heaton.ocr;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrEngine {

	private Tesseract instance;
	
	public OcrEngine() {
		instance = Tesseract.getInstance();
	}

	public String result(File file) {
		try {
			return instance.doOCR(file);
		} catch (TesseractException e) {
			return e.getMessage();
		}	
	}

}
