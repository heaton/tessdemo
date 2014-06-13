package me.heaton.image;

import static org.junit.Assert.*;

import java.awt.image.BufferedImageOp;
import java.util.List;

import me.heaton.helper.ObjectReflectHelper;

import org.junit.Test;

import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.PosterizeFilter;
import com.jhlabs.image.ReduceNoiseFilter;
import com.jhlabs.image.ThresholdFilter;
import com.jhlabs.image.UnsharpFilter;

public class TestFilterFactory {

	@Test
	public void emptyString_noFilter() {
		List<BufferedImageOp> actual = filtersFromCommand("");
		assertEquals(0, actual.size());
	}

	@Test
	public void oneFilter_rnToReduceNoiseFilter() {
		List<BufferedImageOp> actual = filtersFromCommand("rn");
		assertEquals(1, actual.size());
		assertEquals(ReduceNoiseFilter.class, actual.get(0).getClass());
	}

	@Test
	public void multiFilter_checkNumber() {
		List<BufferedImageOp> actual = filtersFromCommand("sp|gs|md");
		assertEquals(3, actual.size());
	}

	@Test
	public void multiFilterWithWrongKey_checkIgnore() {
		List<BufferedImageOp> actual = filtersFromCommand("sp|gs|a|md");
		assertEquals(3, actual.size());
	}

	@Test
	public void multiFilter_checkOder() {
		List<BufferedImageOp> actual = filtersFromCommand("us|iv|ed");
		assertEquals(UnsharpFilter.class, actual.get(0).getClass());
		assertEquals(InvertFilter.class, actual.get(1).getClass());
		assertEquals(EdgeFilter.class, actual.get(2).getClass());
	}

	@Test
	public void multiFilter_checkParam() {
		List<BufferedImageOp> actual = filtersFromCommand("th:100|pt:32");
		ThresholdFilter thresholdFilter = (ThresholdFilter) actual.get(0);
		PosterizeFilter posterizeFilter = (PosterizeFilter) actual.get(1);
		assertEquals(100, thresholdFilter.getLowerThreshold());
		assertEquals(100, thresholdFilter.getUpperThreshold());
		assertEquals(32, posterizeFilter.getNumLevels());
	}

	private List<BufferedImageOp> filtersFromCommand(String command) {
        FilterList fl = FilterFactory.create(command);
        return ObjectReflectHelper.getFeild(fl, "list");
	}
}
