package me.heaton.image;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterList implements Iterable<BufferedImageOp>{

	private List<BufferedImageOp> list;

	public FilterList() {
		list = new ArrayList<BufferedImageOp>();
	}

	public void add(BufferedImageOp filter) {
		if(filter == null) {
			return;
		}
		list.add(filter);
	}

	@Override
	public Iterator<BufferedImageOp> iterator() {
		return list.iterator();
	}

}
