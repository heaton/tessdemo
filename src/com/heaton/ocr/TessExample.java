package com.heaton.ocr;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TessExample {

	public static void main(String[] args) {
		File imageFile = new File("eurotext.tif");
		Tesseract instance = Tesseract.getInstance();

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}
}
