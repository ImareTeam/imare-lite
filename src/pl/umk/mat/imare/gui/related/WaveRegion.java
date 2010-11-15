/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.umk.mat.imare.gui.related;

import pl.umk.mat.imare.io.Wave;

/**
 *
 * @author pieterer
 */
public class WaveRegion {

    public final Wave wave;
    public final int channels;
    
    public double[][] minima;
    public double[][] maxima;
    public int start;
    public int visibles;
    public int pxWidth;

    WaveRegion(Wave wave) {
        this.wave = wave;
        this.channels = wave.getAudioFormat().getChannels();
        this.pxWidth = 0;
        this.minima = new double[channels][];
        this.maxima = new double[channels][];
    }

    public int sampleFromPixel(int x) {
        return start+(int)((long)visibles*(long)x/pxWidth);
    }

    public void compute(int pxWidth, int start, int visibles) {
        this.start = start;
        this.visibles = visibles;
        this.pxWidth = pxWidth;

        for (int ch=0; ch<channels; ++ch) {
            minima[ch] = new double[pxWidth];
            maxima[ch] = new double[pxWidth];
        }

        int sampleNext = sampleFromPixel(0);
        for (int x=0; x<pxWidth; ++x) {
            int sampleNow = sampleNext;
            sampleNext = sampleFromPixel(x+1);

            for (int ch=0; ch<channels; ++ch) {
                minima[ch][x] = Double.NaN;
                maxima[ch][x] = Double.NaN;
            }

            if (sampleNow<sampleNext) {
                int count = sampleNext - sampleNow;
                int data[] = new int[count];

                for (int ch=0; ch<channels; ++ch) {
                    wave.read(ch, data, start);
                    minima[ch][x] = data[0];
                    maxima[ch][x] = data[0];
                    for (int i=1; i<count; ++i) {
                        if (data[i]<minima[ch][x]) minima[ch][x]=data[i];
                        if (data[i]>maxima[ch][x]) maxima[ch][x]=data[i];
                    }
                }
            }
        }
    }

    public double[] getChannelMin(int ch) {
        return minima[ch];
    }

    public double[] getChannelMax(int ch) {
        return maxima[ch];
    }
}
