package me.heaton.helper;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestArea {

	@Test
	public void emptyString_returnNull() {
		Area actual = Area.create("1", "");
		assertNull(actual);
	}

	@Test
	public void notRegularValue_returnNull() {
		Area actual = Area.create("1", "10,200,3");
		assertNull(actual);
	}

	@Test
	public void justCoordinate() {
		Area actual = Area.create("1", "10,200,3,40");
		checkEquals(actual, "1", 10, 200, 3, 40);
	}

	@Test
	public void coordinateWithParam() {
		Area actual = Area.create("1", "10,200,3,40:abc");
		checkEquals(actual, "1", 10, 200, 3, 40, "abc");
	}

	@Test
	public void coordinateWithMultiParam() {
		Area actual = Area.create("1", "10,200,3,40:p1;p2");
		checkEquals(actual, "1", 10, 200, 3, 40, "p1", "p2");
	}

	private void checkEquals(Area actual,
			String expectName, int expectX, int expectY, int expectWidth, int expectHeight,
			String... expectParam) {
		assertEquals(expectName, actual.name);
		assertEquals(expectX, actual.x);
		assertEquals(expectY, actual.y);
		assertEquals(expectWidth, actual.w);
		assertEquals(expectHeight, actual.h);
		for (int i = 0; i < expectParam.length; i++) {
			assertEquals(expectParam[i], actual.getParam(i));
		}
	}
}
