package me.heaton.image;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
import com.jhlabs.image.ThresholdFilter;
import com.jhlabs.image.UnsharpFilter;

public class ImageFilter {

	private BufferedImage image;

	public ImageFilter(File file) throws IOException {
		image = ImageIO.read(file);
	}

	public ImageFilter(BufferedImage image) {
		this.image = image;
	}

	public ImageFilter reduceNoise() {
		return go(new ReduceNoiseFilter());
	}

	public ImageFilter sharpen() {
		return go(new SharpenFilter());
	}

	public ImageFilter gray() {
		return go(new GrayscaleFilter());
	}

	public ImageFilter thresholding() {
		return go(new ThresholdFilter());
	}

	public ImageFilter gamma(float g) {
		return go(new GammaFilter(g));
	}

	public ImageFilter despeckle(int times) {
		return go(new IteratedFilter(new DespeckleFilter(), times));
	}

	public ImageFilter median(){ 
		return go(new MedianFilter());
	}

	public ImageFilter unsharp() {
		UnsharpFilter f = new UnsharpFilter();
		return go(f);
	}

	public ImageFilter hsb() {
		HSBAdjustFilter f = new HSBAdjustFilter();
		f.setSFactor(0.1f);
		f.setBFactor(0.1f);
		return go(f);
	}

	public ImageFilter posterize() {
		PosterizeFilter f = new PosterizeFilter();
		f.setNumLevels(64);
		return go(f);
	}

	public ImageFilter invert() {
		return go(new InvertFilter());
	}

	public ImageFilter edge() {
		EdgeFilter f = new EdgeFilter();
		return go(f);
	}

	public ImageFilter dog() {
		DoGFilter f = new DoGFilter();
		return go(f);
	}

	public ImageFilter laplace() {
		LaplaceFilter f = new LaplaceFilter();
		return go(f);
	}

	private ImageFilter go(BufferedImageOp op) {
		BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		image = op.filter(image, temp);
		return this;
	}

	public BufferedImage get() {
		return image;
	}

	public BufferedImage filterFromCommand(String command) {
		BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		FilterList filters = FilterFactory.create(command);
		for(BufferedImageOp filter : filters) {
			image = filter.filter(image, temp);
		}
		return get();
	}

}
