/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.umk.mat.imare.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import pl.umk.mat.imare.io.Wave;

/**
 *
 * @author PieterEr
 */
public class WaveDisplayer extends Component {

    public static Color BACKGROUND_COLOR = Color.BLUE;
    public static Color DATA_COLOR = Color.WHITE;
    public static Color SELECTED_COLOR = Color.RED;
    public static Color PLAYING_COLOR = Color.YELLOW;

    private Wave wave = null;
    private int sampleStart = 0;
    private int samplesVisible = 1;
    private int samplesTotal = 1;

    private int selected[] = null;
    private Integer playing = null;

    public WaveDisplayer() {

        MouseInputListener mi = new MouseInputListener() {

            private Integer xOld = null;

            @Override
            public void mousePressed(MouseEvent e) {
                xOld = new Integer(e.getX());
                selectPixel(xOld);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                xOld = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (xOld != null) {
                    selectPixels(xOld,e.getX());
                }
            }

            @Override public void mouseClicked(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
            @Override public void mouseMoved(MouseEvent e) { }
        };

        addMouseListener(mi);
        addMouseMotionListener(mi);
    }

    public void loadWave(Wave wave) {
        this.wave = wave;
        samplesTotal = wave.getSampleCount();
        resetZoom();
    }

    private int sampleFromPixel(int x) {
        return (int)((long)samplesVisible*x/getWidth());
    }

    public void selectPixels(int xFirst, int xLast) {
        int s1 = sampleFromPixel(xFirst);
        int s2 = sampleFromPixel(xLast);
        if (s1 <= s2) {
            selected = new int[] { s1, s2 };
        } else {
            selected = new int[] { s2, s1 };
        }
        repaint();
    }

    public void selectPixel(int xOnly) {
        int s = sampleFromPixel(xOnly);
        selected = new int[] { s, s };
        repaint();
    }

    public void setCurrentlyPlaying(int sample) {

    }

    public void unsetCurrentlyPlaying() {

    }

    public void deselect() {
        selected = null;
        repaint();
    }

    public void resetZoom() {
        sampleStart = 0;
        samplesVisible = samplesTotal;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle r = g.getClipBounds();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(r.x, r.y, r.width, r.height);

        if (wave != null) {
            int chs = wave.getAudioFormat().getChannels();
            int height_2 = getHeight()/(2*chs);

            int xMin = r.x;
            int xEnd = r.x + r.width;

            g.setColor(DATA_COLOR);
            for (int x=xMin; x<xEnd; ++x) {
                int sampleNow = sampleFromPixel(x);
                int sampleNext = sampleFromPixel(x+1);
                if (sampleNow<sampleNext) {

                    if (playing != null && sampleNow<=playing && playing<sampleNext) {
                        g.setColor(PLAYING_COLOR);
                        g.drawLine(x,r.y,x,r.y+r.height);
                        g.setColor(DATA_COLOR);
                    } else {
                        int count = sampleNext - sampleNow;
                        int start = sampleStart + sampleNow;

                        int data[] = new int[count];

                        if (selected!=null && sampleNext>selected[0] && sampleNow<=selected[1]) {
                            g.setColor(SELECTED_COLOR);
                            g.drawLine(x,r.y,x,r.y+r.height);
                            g.setColor(DATA_COLOR);
                        }

                        for (int ch=0; ch<chs; ++ch) {
                            int yMin = ch*getHeight()/chs;

                            wave.read(ch, data, start);
                            int valueMin = data[0];
                            int valueMax = data[0];
                            for (int i=1; i<count; ++i) {
                                if (data[i]<valueMin) valueMin=data[i];
                                if (data[i]>valueMax) valueMax=data[i];
                            }
                            int yTop = yMin + (int)Math.round(height_2*(1.0 -(double)valueMax/(double)Wave.MAX_VAL));
                            int yBot = yMin + (int)Math.round(height_2*(1.0 -(double)valueMin/(double)Wave.MAX_VAL));

                            g.drawLine(x, yTop, x, yBot);
                        }
                    }
                }
            }
        }
    }
}
