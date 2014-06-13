package me.heaton.ocr;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import me.heaton.helper.Area;
import net.sourceforge.tess4j.TessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrEngine {

    private static final String VAR_CHAR_WHITELIST = "tessedit_char_whitelist";
	private static final String WHITELIST_DEFAULT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890()/ ";
//	private static final String WHITELIST_VIN = "ABCDEFGHJKLMNPRSTUVWXYZ1234567890";
	private static final String ENG = "eng";
    
	private Tesseract instance;

	private Rectangle rect;
	
	public OcrEngine() {
		instance = Tesseract.getInstance();
		String path = new File(TessExample.class.getResource("/").getPath()).getAbsolutePath();
		instance.setDatapath(path);
		instance.setOcrEngineMode(TessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
		setWhiteList(WHITELIST_DEFAULT);
		setLanguage(ENG);
	}

	public void chooseArea(Area area) {
		rect = new Rectangle(area.x, area.y, area.w, area.h);
	}

	public void setWhiteList(String chars) {
		if(chars == null || chars.isEmpty()) {
			return;
		}
		instance.setTessVariable(VAR_CHAR_WHITELIST, chars);
	}

	public void setLanguage(String l) {
		if(l == null || l.isEmpty()) {
			return;
		}
		instance.setLanguage(l);
	}

	public String result(File file) {
		try {
			return instance.doOCR(file, rect);
		} catch (TesseractException e) {
			return e.getMessage();
		}	
	}

	public String result(BufferedImage img) {
		try {
			return instance.doOCR(img, rect);
		} catch (TesseractException e) {
			return e.getMessage();
		}	
	}

}
