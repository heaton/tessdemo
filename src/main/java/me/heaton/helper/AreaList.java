package me.heaton.helper;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class AreaList implements Iterable<Area>{

	private final List<Area> list;
	
	public AreaList(String areas) {
		list = new ArrayList<Area>();
		init(areas);
	}

	private void init(String areas) {
		if(areas == null || areas.isEmpty()) {
			return;
		}
		StringReader sr = new StringReader(areas);
		Properties ocrAreas = new Properties();
		try {
			ocrAreas.load(sr);
			for(Object key : ocrAreas.keySet()) {
				String name = key.toString();
				Area area = Area.create(name, ocrAreas.getProperty(name));
				if(area != null) {
					list.add(area);
				}
			}
		} catch (IOException e) {
		}
	}

	@Override
	public Iterator<Area> iterator() {
		return list.iterator();
	}

}
