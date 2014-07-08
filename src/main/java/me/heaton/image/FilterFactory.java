package me.heaton.image;

import java.awt.image.BufferedImageOp;

import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.BumpFilter;
import com.jhlabs.image.DespeckleFilter;
import com.jhlabs.image.DoGFilter;
import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.GammaFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.HSBAdjustFilter;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.IteratedFilter;
import com.jhlabs.image.LaplaceFilter;
import com.jhlabs.image.MedianFilter;
import com.jhlabs.image.PosterizeFilter;
import com.jhlabs.image.ReduceNoiseFilter;
import com.jhlabs.image.SharpenFilter;
import com.jhlabs.image.SmartBlurFilter;
import com.jhlabs.image.ThresholdFilter;
import com.jhlabs.image.UnsharpFilter;

public class FilterFactory {

	private static final String COMMAND_SPLIT = "\\|";
	private static final String REGEX_COMMAND = "^\\w+(:(\\w|\\.)+)*(\\|\\w+(:\\w+)*)*$";

	/**
	 * typical command: rn|gs|th:100
	 * @param command
	 * @return
	 */
	public static FilterList create(String command) {
		FilterList filters = new FilterList();
		if (isRegular(command)) {
			for (String c : command.split(COMMAND_SPLIT)) {
				Command com = new Command(c);
				filters.add(fetchFilter(com));
			}
		}
		return filters;
	}

	private static boolean isRegular(String command) {
		return command != null && command.matches(REGEX_COMMAND);
	}

	private static BufferedImageOp fetchFilter(Command com) {
		if("rn".equals(com.name)) {
			return new ReduceNoiseFilter();
		}
		if("sp".equals(com.name)) {
			return new SharpenFilter();
		}
		if("gs".equals(com.name)) {
			return new GrayscaleFilter();
		}
		if("th".equals(com.name)) {
			int t = com.getIntParam(0, 0x7F);
			return new ThresholdFilter(t);
		}
		if("gm".equals(com.name)) {
			float g = com.getFloatParam(0, 1.0f);
			return new GammaFilter(g);
		}
		if("dc".equals(com.name)) {
			int times = com.getIntParam(0, 1);
			return new IteratedFilter(new DespeckleFilter(), times);
		}
		if("md".equals(com.name)) {
			return new MedianFilter();
		}
		if("us".equals(com.name)) {
			UnsharpFilter f = new UnsharpFilter();
			return f;
		}
		if("ha".equals(com.name)) {
			HSBAdjustFilter f = new HSBAdjustFilter();
			f.setSFactor(0.1f);
			f.setBFactor(0.1f);
			return f;
		}
		if("pt".equals(com.name)) {
			int nl = com.getIntParam(0, 64);
			PosterizeFilter f = new PosterizeFilter();
			f.setNumLevels(nl);
			return f;
		}
		if("iv".equals(com.name)) {
			return new InvertFilter();
		}
		if("ed".equals(com.name)) {
			EdgeFilter f = new EdgeFilter();
			return f;
		}
		if("dg".equals(com.name)) {
			DoGFilter f = new DoGFilter();
			return f;
		}
		if("lp".equals(com.name)) {
			return new LaplaceFilter();
		}
		if("bu".equals(com.name)) {
		    return new BumpFilter();
		}
		if("bl".equals(com.name)) {
		    return new BlurFilter();
		}
		if("sb".equals(com.name)) {
		    return new SmartBlurFilter();
		}
		return null;
	}

}
