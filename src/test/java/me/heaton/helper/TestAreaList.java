package me.heaton.helper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TestAreaList {

	@Test
	public void emptyString_emptyAreaList() {
		List<Area> list = areaListFromString("");
		assertEquals(0, list.size());
	}

	@Test
	public void oneArea() {
		List<Area> list = areaListFromString("1=1,2,3,4");
		assertEquals(1, list.size());
	}

	@Test
	public void multiAreaWithEmptyLine() {
		List<Area> list = areaListFromString(
				"0=1,2,3,4\n"
			  + "\n"
			  + "\n"
			  + "1=10,20,30,40\n"
			  + "\n"
			  + "2=50,60,70,80\n");
		assertEquals(3, list.size());
	}

	@Test
	public void multiAreaWithWrongLine() {
		List<Area> list = areaListFromString(
				"0=1,2,3,4\n"
			  + "\n"
			  + "\n"
			  + "1=10,20,40\n"
			  + "\n"
			  + "2=50,60,70,80\n");
		assertEquals(2, list.size());
	}

	private List<Area> areaListFromString(String s) {
		AreaList al = new AreaList(s);
		return ObjectReflectHelper.getFeild(al, "list");
	}

}
