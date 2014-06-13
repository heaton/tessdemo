package me.heaton.helper;

import static org.junit.Assert.*;

import java.util.Arrays;

import me.heaton.helper.FileName;

import org.junit.Test;

public class TestFileName {

	@Test
	public void test_suffix() {
		FileName fileName = new FileName("/tessdir/aa.png");
		assertEquals("png", fileName.suffix());
	}

	@Test
	public void test_newName() {
		FileName fileName = new FileName("/tessdir/aa.png");
		assertEquals("/tessdir/aa.new.png", fileName.newName());
	}

	@Test
	public void test() {
		String[] ss = new String[]{"aa"};
		String[] s2 = Arrays.copyOfRange(ss, 1, ss.length);
		assertEquals(0, s2.length);
	}

}
