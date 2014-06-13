package me.heaton.helper;

public class FileName {

	private String fileName;

	public FileName(String fileName) {
		this.fileName = fileName;
	}

	public String newName() {
		int doti = lastDot();
		return fileName.substring(0, doti) + ".new" + fileName.substring(doti);
	}

	private int lastDot() {
		return fileName.lastIndexOf(".");
	}

	public String suffix() {
		return fileName.substring(lastDot() + 1);
	}

	public boolean isEmpty() {
		return fileName==null || fileName.isEmpty();
	}

	public String get(){
		return toString();
	}

	@Override
	public String toString(){
		return fileName;
	}

}
