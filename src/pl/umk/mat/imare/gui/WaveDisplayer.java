/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.mat.imare.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import pl.umk.mat.imare.gui.related.WaveRegion;
import pl.umk.mat.imare.io.Wave;

/**
 *
 * @author PieterEr
 */
public class WaveDisplayer extends Component {

  public static Color COLOR_BACKGROUND = new Color(235, 255, 255);
  public static Color COLOR_DATA = Color.BLUE;
  public static Color COLOR_SEL_BACKGROUND = Color.BLACK;
  public static Color COLOR_SEL_DATA = Color.YELLOW;
  public static Color COLOR_PLAYING = Color.RED;

  private final Wave wave;
  private final WaveRegion wr;
  private int selected[] = null;
  private Integer playing = null;

  public WaveDisplayer(Wave wave) {

    this.wave = wave;
    if (wave != null) {
      this.wr = new WaveRegion(wave);
    } else {
      this.wr = null;
    }

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
          selectPixels(xOld, e.getX());
        }
      }

      @Override
      public void mouseClicked(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mouseMoved(MouseEvent e) {
      }
    };

    addComponentListener(new ComponentListener() {

      @Override
      public void componentResized(ComponentEvent e) {
        if (wr != null) {
          wr.compute(getWidth(), wr.start, wr.visibles);
          repaint();
        }
      }

      @Override
      public void componentMoved(ComponentEvent e) {
      }

      @Override
      public void componentShown(ComponentEvent e) {
      }

      @Override
      public void componentHidden(ComponentEvent e) {
      }

    });

    addMouseListener(mi);
    addMouseMotionListener(mi);
  }

  public void selectPixels(int xFirst, int xLast) {
    if (wr == null) return;

    int s1 = wr.sampleFromPixel(xFirst);
    int s2 = wr.sampleFromPixel(xLast);
    if (s1 <= s2) {
      selected = new int[]{s1, s2};
    } else {
      selected = new int[]{s2, s1};
    }
    repaint();
  }

  public void selectPixel(int xOnly) {
    if (wr == null) return;

    int s = wr.sampleFromPixel(xOnly);
    selected = new int[]{s, s};
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

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Rectangle r = g.getClipBounds();
    g.setColor(COLOR_BACKGROUND);
    g.fillRect(r.x, r.y, r.width, r.height);

    if (wave != null && wr != null) {
      int chs = wave.getAudioFormat().getChannels();
      int height_2 = getHeight() / (2 * chs);

      int xMin = r.x;
      int xEnd = r.x + r.width;

      for (int x = xMin; x < xEnd; ++x) {
        int sampleNow = wr.sampleFromPixel(x);
        int sampleNext = wr.sampleFromPixel(x + 1);
        if (sampleNow < sampleNext) {

          if (playing != null && sampleNow <= playing && playing < sampleNext) {
            g.setColor(COLOR_PLAYING);
            g.drawLine(x, r.y, x, r.y + r.height);
          } else {
            int count = sampleNext - sampleNow;
            int start = 0;//sampleStart + sampleNow;

            int data[] = new int[count];

            if (selected != null && sampleNext > selected[0] && sampleNow <= selected[1]) {
              g.setColor(COLOR_SEL_BACKGROUND);
              g.drawLine(x, r.y, x, r.y + r.height);
              g.setColor(COLOR_SEL_DATA);
            } else {
              g.setColor(COLOR_DATA);
            }

            for (int ch = 0; ch < chs; ++ch) {

              double valueMin = wr.getChannelMin(ch)[x];
              double valueMax = wr.getChannelMax(ch)[x];

              int yMin = ch * getHeight() / chs;

//              wave.read(ch, data, start);
//              int valueMin = data[0];
//              int valueMax = data[0];
//              for (int i = 1; i < count; ++i) {
//                if (data[i] < valueMin) {
//                  valueMin = data[i];
//                }
//                if (data[i] > valueMax) {
//                  valueMax = data[i];
//                }
//              }
              int yTop = yMin + (int) Math.round(height_2 * (1.0 - valueMax / (double) Wave.MAX_VAL));
              int yBot = yMin + (int) Math.round(height_2 * (1.0 - valueMin / (double) Wave.MAX_VAL));

              g.drawLine(x, yTop, x, yBot);
            }
          }
        }
      }
    }
  }
}
