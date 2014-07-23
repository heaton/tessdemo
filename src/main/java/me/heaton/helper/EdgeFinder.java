package me.heaton.helper;

import java.awt.Rectangle;

import com.jhlabs.image.WholeImageFilter;

public class EdgeFinder extends WholeImageFilter{

    private StringBuilder result = new StringBuilder();
    
    public String result() {
        return result.toString();
    }

    @Override
    protected int[] filterPixels(int width, int height, int[] inPixels,
            Rectangle transformedSpace) {
        int[] rows = new int[height];
        int[] rs = new int[5];
        int i = 0;
        for (int y = 0; y < height; y++) {
            int n = 0;
            for (int x = 0; x < width; x++) {
				int rgb = inPixels[y*width+x];
				int r = (rgb & 0xff0000) >> 16;
				if(r > 128) {
				    n++;
				}
            }
            rs[i] = n;
            i = (i+1) % rs.length;
            rows[y] = sum(rs);
        }
        findLine(rows);
        return inPixels;
    }

    private int sum(int[] is) {
        int sum = 0;
        for(int i : is) {
            sum += i;
        }
        return sum;
    }

    private void findLine(int[] rows) {
        for(int y=0;y<rows.length;y++) {
            int r = rows[y];
            result.append(y).append(".").append(r).append("\n");
        }
    }

}
