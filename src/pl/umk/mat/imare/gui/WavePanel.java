/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WavePanel.java
 *
 * Created on 2010-04-16, 15:04:27
 */

package pl.umk.mat.imare.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import pl.umk.mat.imare.io.Wave;
import pl.umk.mat.imare.reco.Recognizer;
import pl.umk.mat.imare.reco.WindowFunctionBlackman;

/**
 *
 * @author morti
 */
public class WavePanel extends javax.swing.JPanel implements PlayListener {

	private Wave wave = null;
	private JPanel cursorPanel = null;
	private JPanel wavePanel = null;
    private BufferedImage waveImage = null, img = null, timeImage = null;
    private Play odt = null;
    private int startX, endX, xPress, posStart, posEnd, position, start, stop, selStart, selEnd;
    private double length, wsp = 1;
    private boolean test = true;
	private MainGUI mainWindow;

    /** Creates new form WavePanel */
    public WavePanel() {
        initComponents();

		wavePanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if(waveImage != null)
					g.drawImage(waveImage, 0, 0, null);
			}
		};

		wavePanel.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				wavePanelMousePressed(evt);
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				wavePanelMouseClicked(evt);
			}

		});

        wavePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                wavePanelMouseDragged(evt);
            }
        });

		cursorPanel = new JPanel();
		cursorPanel.setMaximumSize(new Dimension(1, 0));
		cursorPanel.setMinimumSize(new Dimension(1, 0));
		cursorPanel.setSize(1, containingPanel.getHeight());
		cursorPanel.setBackground(Color.red);

		wavePanel.setBounds(0, 0, layerPane.getWidth(), layerPane.getHeight());
		cursorPanel.setLocation(-1, 0);
		layerPane.add(wavePanel, new Integer(0));
		layerPane.add(cursorPanel, new Integer(1));
		rysuj();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        toolBar = new javax.swing.JToolBar();
        playButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        recognizeButton = new javax.swing.JButton();
        startTime = new javax.swing.JSpinner();
        endTime = new javax.swing.JSpinner();
        fftButton = new javax.swing.JButton();
        zoomIn = new javax.swing.JButton();
        zoomOut = new javax.swing.JButton();
        volumeSlider = new javax.swing.JSlider();
        infoLabel = new javax.swing.JLabel();
        containingPanel = new javax.swing.JPanel();
        timePanel = new javax.swing.JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(timeImage, 0, 0, null);
            }};
            layerPane = new javax.swing.JLayeredPane();
            scrollBar = new javax.swing.JScrollBar();

            addAncestorListener(new javax.swing.event.AncestorListener() {
                public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                }
                public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                    formAncestorAdded(evt);
                }
                public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                }
            });
            setLayout(new java.awt.GridBagLayout());

            toolBar.setFloatable(false);
            toolBar.setRollover(true);

            playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/play.png"))); // NOI18N
            playButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    playButtonActionPerformed(evt);
                }
            });
            toolBar.add(playButton);

            pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/pause.png"))); // NOI18N
            pauseButton.setEnabled(false);
            pauseButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    pauseButtonActionPerformed(evt);
                }
            });
            toolBar.add(pauseButton);

            stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/stop.png"))); // NOI18N
            stopButton.setEnabled(false);
            stopButton.setFocusable(false);
            stopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            stopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            stopButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    stopButtonActionPerformed(evt);
                }
            });
            toolBar.add(stopButton);
            toolBar.add(jSeparator1);

            recognizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/recognize.png"))); // NOI18N
            recognizeButton.setFocusable(false);
            recognizeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            recognizeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            recognizeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    recognizeButtonActionPerformed(evt);
                }
            });
            toolBar.add(recognizeButton);

            startTime.setMaximumSize(new java.awt.Dimension(75, 20));
            startTime.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    startTimeStateChanged(evt);
                }
            });
            toolBar.add(startTime);

            endTime.setMaximumSize(new java.awt.Dimension(75, 20));
            endTime.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    endTimeStateChanged(evt);
                }
            });
            toolBar.add(endTime);

            fftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/fft.png"))); // NOI18N
            fftButton.setFocusable(false);
            fftButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            fftButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            fftButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    fftButtonActionPerformed(evt);
                }
            });
            toolBar.add(fftButton);

            zoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/zoom_in.png"))); // NOI18N
            zoomIn.setFocusable(false);
            zoomIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            zoomIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            zoomIn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    zoomInActionPerformed(evt);
                }
            });
            toolBar.add(zoomIn);

            zoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/umk/mat/imare/gui/gfx/zoom_out.png"))); // NOI18N
            zoomOut.setFocusable(false);
            zoomOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            zoomOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            zoomOut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    zoomOutActionPerformed(evt);
                }
            });
            toolBar.add(zoomOut);

            volumeSlider.setMinimum(50);
            volumeSlider.setValue(100);
            volumeSlider.setMaximumSize(new java.awt.Dimension(100, 25));
            volumeSlider.setOpaque(false);
            volumeSlider.setPreferredSize(new java.awt.Dimension(50, 25));
            volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    volumeSliderStateChanged(evt);
                }
            });
            toolBar.add(volumeSlider);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            add(toolBar, gridBagConstraints);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            add(infoLabel, gridBagConstraints);

            javax.swing.GroupLayout timePanelLayout = new javax.swing.GroupLayout(timePanel);
            timePanel.setLayout(timePanelLayout);
            timePanelLayout.setHorizontalGroup(
                timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 470, Short.MAX_VALUE)
            );
            timePanelLayout.setVerticalGroup(
                timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 20, Short.MAX_VALUE)
            );

            layerPane.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    layerPaneComponentResized(evt);
                }
            });

            scrollBar.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
            scrollBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
                public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                    scrollBarAdjustmentValueChanged(evt);
                }
            });

            javax.swing.GroupLayout containingPanelLayout = new javax.swing.GroupLayout(containingPanel);
            containingPanel.setLayout(containingPanelLayout);
            containingPanelLayout.setHorizontalGroup(
                containingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(timePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addComponent(layerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            );
            containingPanelLayout.setVerticalGroup(
                containingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containingPanelLayout.createSequentialGroup()
                    .addComponent(timePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(layerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(scrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            add(containingPanel, gridBagConstraints);
        }// </editor-fold>//GEN-END:initComponents

	private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed

		playButton.setEnabled(false);
		pauseButton.setEnabled(true);
		stopButton.setEnabled(true);

		if(odt == null){
			Double d1 = (Double)startTime.getValue(), d2 = (Double)endTime.getValue();

			if(d1.equals(d2)){
                            if(cursorPanel.getLocation().x==-1){
                                start = 0;
                                stop = wave.getSampleCount();
                            }else{
                                start = (int) (scrollBar.getValue() + (cursorPanel.getLocation().x / (double) wavePanel.getWidth())*(posEnd-posStart));
                                stop = wave.getSampleCount();
                            }
			} else{
				start = (int) (d1 / length * wave.getSampleCount());
				stop =  (int) (d2 / length * wave.getSampleCount());
			}
			odt = new Play(wave, start, stop);
			odt.addPlayListener(this);
		} else {
			odt.play();
		}
	}//GEN-LAST:event_playButtonActionPerformed

	private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
		playButton.setEnabled(true);
		pauseButton.setEnabled(false);
		odt.pause();
	}//GEN-LAST:event_pauseButtonActionPerformed

	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
		playButton.setEnabled(true);
		pauseButton.setEnabled(false);
		stopButton.setEnabled(false);

		odt.stop();
		containingPanel.repaint();
}//GEN-LAST:event_stopButtonActionPerformed

	private void recognizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recognizeButtonActionPerformed
            try {
                final float rate = wave.getAudioFormat().getSampleRate();
                int iStart = (int) Math.round((Double) (startTime.getValue()) * rate);
                int iEnd = (int) Math.round((Double) (endTime.getValue()) * rate);
                if (iStart >= iEnd) {
                    iStart = 0;
                    iEnd = wave.getSampleCount();
                }
                Recognizer recognizer = new Recognizer(wave,iStart,iEnd);
                RecognitionProgress recogProg = new RecognitionProgress(wave.filename, recognizer, true);
                mainWindow.addFrame(recogProg, true);
                recognizer.start();
            } catch (Exception ex) {
                Logger.getLogger(FFTFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Wrong size exception caught!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
}//GEN-LAST:event_recognizeButtonActionPerformed

	private void startTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startTimeStateChanged
		Double d1 = (Double)startTime.getValue(), d2 = (Double)endTime.getValue();
		if(d1<0) endTime.setValue(0);
		if(d1>d2) startTime.setValue(endTime.getValue());
		else if(test) paintSelected();
}//GEN-LAST:event_startTimeStateChanged

	private void endTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endTimeStateChanged
		Double d1 = (Double)startTime.getValue(), d2 = (Double)endTime.getValue();
		if(d2>length) startTime.setValue(length);
		if(d1>d2) endTime.setValue(startTime.getValue());
		else if(test) paintSelected();
}//GEN-LAST:event_endTimeStateChanged

	private void fftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fftButtonActionPerformed

		Double d1 = (Double)startTime.getValue(), d2 = (Double)endTime.getValue();

		int elen = (int) ((d2 - d1)/length * wave.getSampleCount());

		if(elen<512) elen = 512;

		int len = 2;
		while (len<=elen && len<=1<<14) len<<=1;
		len>>=1;

		int data[] = new int[len];
		wave.readMono(data, (int)(d1/length * wave.getSampleCount()));
		FFTFrame frame = new FFTFrame(data,(int) wave.getAudioFormat().getFrameRate(), wave.getAudioFormat().getSampleSizeInBits());
		mainWindow.addFrame(frame, true);
}//GEN-LAST:event_fftButtonActionPerformed

	private void zoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInActionPerformed
            double d1 = (Double) startTime.getValue(), d2 = (Double) endTime.getValue();

            if (d1 == d2) {
                int delta = (posEnd - posStart)/8;
                posStart += delta;
                posEnd -= delta;
            } else {
                posStart = (int) (d1 / length * wave.getSampleCount());
                posEnd = (int) (d2 / length * wave.getSampleCount());
            }

            wsp = (posEnd - posStart) / (double) wave.getSampleCount();
            if (wsp < 1) {
                scrollBar.setVisible(true);
            }
            test = false;
            scrollBar.setVisibleAmount(posEnd - posStart);
            scrollBar.setValue(posStart);
            test = true;
            rysuj();
            paintSelected();
}//GEN-LAST:event_zoomInActionPerformed

	private void zoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutActionPerformed

            int delta = (posEnd - posStart) / 6;
            posStart = Math.max(posStart - delta, 0);
            posEnd = Math.min(posEnd + delta, wave.getSampleCount());
            wsp = (posEnd - posStart) / (double) wave.getSampleCount();
            if (wsp >= 1) {
                wsp = 1;
                scrollBar.setVisible(false);
            }
            test = false;
            scrollBar.setValue(posStart);
            scrollBar.setVisibleAmount(posEnd - posStart);
            test = true;

            rysuj();
            paintSelected();
}//GEN-LAST:event_zoomOutActionPerformed

	private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
		if(odt!=null){
			odt.setVolume(volumeSlider.getValue()/100.0f);
		}
}//GEN-LAST:event_volumeSliderStateChanged

	private void layerPaneComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_layerPaneComponentResized
		if(wavePanel != null) {
			scrollBar.setVisibleAmount((posEnd-posStart));
			rysuj();
		}
		if(cursorPanel != null)
			cursorPanel.setSize(1, containingPanel.getHeight());

		if(img!=null) paintSelected();
}//GEN-LAST:event_layerPaneComponentResized

	private void scrollBarAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollBarAdjustmentValueChanged
		if(scrollBar.getValueIsAdjusting()){
			posStart = scrollBar.getValue();
			posEnd = (int) (scrollBar.getValue() + scrollBar.getVisibleAmount());
		}
		if(test){
			rysuj();
			if(!startTime.getValue().equals(endTime.getValue())) paintSelected();
		}
}//GEN-LAST:event_scrollBarAdjustmentValueChanged

	private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
		Component c = getParent();
		while(!(c instanceof MainGUI) && c != null) c = c.getParent();

		if(c != null) {
			mainWindow = (MainGUI)c;
			mainWindow.showProjectPane(true);
		}
	}//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containingPanel;
    private javax.swing.JSpinner endTime;
    private javax.swing.JButton fftButton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLayeredPane layerPane;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton recognizeButton;
    private javax.swing.JScrollBar scrollBar;
    private javax.swing.JSpinner startTime;
    private javax.swing.JButton stopButton;
    private javax.swing.JPanel timePanel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JSlider volumeSlider;
    private javax.swing.JButton zoomIn;
    private javax.swing.JButton zoomOut;
    // End of variables declaration//GEN-END:variables

	public void setWave(Wave wave) {
		this.wave = wave;

		if(wave != null) {
			length = wave.getSampleCount()/wave.getAudioFormat().getSampleRate();
			posStart = 0;
			posEnd = wave.getSampleCount();

                        SpinnerModel model = new SpinnerNumberModel(0.0, 0.0, length, 0.01);
                        startTime.setModel(model);
                        model = new SpinnerNumberModel(0, 0.0, length, 0.01);
                        endTime.setModel(model);


			scrollBar.setMaximum(wave.getSampleCount());
			scrollBar.setVisible(false);

			String info = "", str = "";
			DecimalFormat numberFormat = new DecimalFormat("00.000");
			AudioFormat format = wave.getAudioFormat();
			double l = length;
			str = Integer.toString((int)l/60)+":";
			l %= 60;

			info += "<html><b>Ilość kanałów:</b> " + format.getChannels();
			info += " <b>Częstotliwość:</b> " + format.getSampleRate();
			info += " <b>Rozmiar próbki:</b> " + format.getFrameSize();
			info += " <b>BPS:</b> " + format.getSampleSizeInBits();
			info += " <b>Długość:</b> " + str + numberFormat.format(l) + "</html>";
			infoLabel.setText(info);
		} else {
			infoLabel.setText("");
		}
	}

	public void paintSelected(){
		if(wave == null) return;
		if(wavePanel == null) return;

		Double d1 = (Double)startTime.getValue(), d2 = (Double)endTime.getValue();

		int x = (int) (d1.doubleValue() / length * wave.getSampleCount());

		if(x>=posEnd) return;
		else if(x<=posStart) startX = 0;
		else {
			startX = (int) ((x - posStart) / (double) (posEnd-posStart) * wavePanel.getWidth());
		}

		x = (int) (d2.doubleValue() / length * wave.getSampleCount());
		if(x>=posEnd) endX = wavePanel.getWidth();
		else{
			endX = (int) ((x - posStart) / (double) (posEnd-posStart) * wavePanel.getWidth());
		}

		Graphics2D grafImage = (Graphics2D) waveImage.getGraphics();
		grafImage.drawImage(img, 0, 0, null);

		for(int i = startX; i<endX; i++)
			for(int j = 0; j<wavePanel.getHeight(); j++){
				waveImage.setRGB(i, j, waveImage.getRGB(i,j)*-1);
			}

		wavePanel.repaint();
	}

	private void rysuj(){
		if(wave == null) return;
		if(wavePanel == null) return;
//		if(waveImage == null) return;
       try {
            if(containingPanel.getWidth() <= 0 || containingPanel.getHeight() <= 0) return;
            wavePanel.setSize(layerPane.getSize());
            waveImage = new BufferedImage(wavePanel.getWidth(), wavePanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            img = new BufferedImage(waveImage.getWidth(), waveImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) waveImage.getGraphics();

            // rysowanie osi czasu

            timePanel.setSize(wavePanel.getWidth(), timePanel.getHeight());
            timeImage = new BufferedImage(wavePanel.getWidth(), timePanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D timeGraph = (Graphics2D)timeImage.getGraphics();
            timeGraph.setColor(Color.WHITE);
            timeGraph.fillRect(0, 0, timeImage.getWidth(), timeImage.getHeight());
            timeGraph.setColor(Color.BLACK);

            timeGraph.drawLine(0, timeImage.getHeight()-1, timeImage.getWidth(), timeImage.getHeight()-1);

            int n = 0, posX;
            double space, time = (posEnd-posStart)/(double)wave.getSampleCount()*length;

            space = time/(double)(wavePanel.getWidth()/50);

            while(space<1){
                space*=10;
                n++;
            }

            space = Math.round(space);

            for(int i=0; i<n; i++){
                space/=10;
            }

            double first=0.0;
            first = space * Math.floor((posStart/(double)wave.getSampleCount()*length)/space);
            while(first<posStart/(double)wave.getSampleCount()*length){
                first+=space;
            }

            posX = (int) ((first-(posStart/(double)wave.getSampleCount())*length) / time * wavePanel.getWidth());

            int spaceX = (int) (space * wavePanel.getWidth() / ((posEnd - posStart) / (double) wave.getSampleCount() * length));

            if(posX-spaceX/2!=0) timeGraph.drawLine(posX-spaceX/2, 7*timeImage.getHeight()/8, posX-spaceX/2, timeImage.getHeight());

            //NumberFormat nf = NumberFormat.getInstance();
            String format = "0";
            if(n>0) format += ".";
            for(int i = 0; i<n; i++) format+="0";
            DecimalFormat df = new DecimalFormat(format);

            String str = null;

            while(posX<wavePanel.getWidth()){
                timeGraph.drawLine(posX, 3*timeImage.getHeight()/4, posX, timeImage.getHeight());
                if(first>=60){
                    int t1 = (int) (first / 60);
                    String tmp = Integer.toString(t1)+":";
                    double t2 = first - t1*60;
                    if(t2<10) tmp+="0";
                    //str = tmp+nf.format(t2, new StringBuffer(), new FieldPosition(n)).toString();
                    str = tmp+df.format(t2);
                }else{
                    str = df.format(first);
                }
                timeGraph.drawString(str, posX-str.length()*6/2, 13);
                timeGraph.drawLine(posX+spaceX/2, 7*timeImage.getHeight()/8, posX+spaceX/2, timeImage.getHeight());
                posX+=spaceX;
                first+=space;

            }
            timePanel.repaint();

            //koniec rysowania osi czasu

            // wave background
            g.setColor(new Color(235, 255, 255));
            g.fillRect(0, 0, wavePanel.getWidth(), wavePanel.getHeight());

            int sampleCount = posEnd-posStart;
            int samplesPerPixel = sampleCount / wavePanel.getWidth();
            int centerPos = 0;

            g.setColor(Color.blue);

			int min, max;

                        for(int chann = 0; chann<wave.getAudioFormat().getChannels(); chann++){

                            if(samplesPerPixel<=1){

                                int data[] = new int[sampleCount];
                                wave.read(chann, data, posStart);
                                int prevX, prevY, x=0, y;
                                centerPos = (int) (wavePanel.getHeight() * ((2 * chann + 1) / (double) (2 * wave.getAudioFormat().getChannels())));

                                prevX = 0;
                                prevY = -centerPos;


                                for(int i=0; i < sampleCount; i++){
                                    x = (int)(i/(double)sampleCount*wavePanel.getWidth());
                                    y = -data[i];
                                    y = (int)((double)y / (Wave.MAX_VAL) * (wavePanel.getHeight()-5)/(2.0*wave.getAudioFormat().getChannels())) + centerPos;
                                    g.drawLine(prevX, prevY, x, y);
                                    prevX = x;
                                    prevY = y;
                                }

                                g.setColor(new Color(10, 10, 200));
                                g.drawLine(0, centerPos, wavePanel.getWidth(), centerPos);
                                g.setColor(Color.BLUE);

                            }

                            else{

                                int[] data = new int [samplesPerPixel];
                                centerPos = (int) (wavePanel.getHeight() * ((2 * chann + 1) / (double) (2 * wave.getAudioFormat().getChannels())));

                                for(int x=0; x < wavePanel.getWidth(); x++) {
                                    wave.read(chann, data, posStart+x*samplesPerPixel);

                                    min = Integer.MAX_VALUE;
                                    max = Integer.MIN_VALUE;
                                    for(int i : data) {
                                            if(i < min) min = i;
                                            if(i > max) max = i;
                                    }

                                    min = -min;
                                    max = -max;

                                    min = (int)((double)min / (Wave.MAX_VAL) * (wavePanel.getHeight()-5)/(2.0*wave.getAudioFormat().getChannels())) + centerPos;
                                    max = (int)((double)max / (Wave.MAX_VAL) * (wavePanel.getHeight()-5)/(2.0*wave.getAudioFormat().getChannels())) + centerPos;
                                    g.drawLine(x, min, x, max);
                                }

                                g.setColor(new Color(10, 10, 200));
                                g.drawLine(0, centerPos, wavePanel.getWidth(), centerPos);
                                g.setColor(Color.BLUE);
                            }
                        }
						wavePanel.repaint();
                        Graphics2D graf = (Graphics2D) img.getGraphics();
                        graf.drawImage(waveImage, 0, 0, null);
        } catch (Exception ex) {
            Logger.getLogger(OldWavePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void positionChanged(int newPosition) {
		if(newPosition >= stop) stopButtonActionPerformed(null);
            else{
                if(newPosition<posStart || newPosition>posEnd){
                    test = false;
                    scrollBar.setValue(newPosition);
                    test = true;
                    posStart = newPosition;
                    posEnd = (int) (newPosition + scrollBar.getVisibleAmount());
                    if(posEnd>wave.getSampleCount()){
                        posStart -= (posEnd-wave.getSampleCount());
                        posEnd = wave.getSampleCount();
                    }
                    rysuj();
                    if(!startTime.getValue().equals(endTime.getValue())) paintSelected();
                }
                position = (int) (wavePanel.getWidth() * ((newPosition-posStart) / (double) (posEnd-posStart)));
                cursorPanel.setLocation(position, 0);
            }
	}

	@Override
	public void playingFinished() {
		odt = null;
        playButton.setEnabled(true);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);

		cursorPanel.setLocation(-1, 0);
	}

	@Override
	public void setVolume() {
		if(odt!=null){
			odt.setVolume(volumeSlider.getValue()/100.0f);
		}
	}

	public void wavePanelMousePressed(java.awt.event.MouseEvent evt){
		xPress = evt.getX();
	}
	public void wavePanelMouseClicked(java.awt.event.MouseEvent evt){
		Graphics2D grafImage = (Graphics2D) waveImage.getGraphics();
		grafImage.drawImage(img, 0, 0, null);

		startX = 0;
		endX = wavePanel.getWidth();
		test = false;
		startTime.setValue(0.0);
		endTime.setValue(0.0);
		test = true;
                cursorPanel.setLocation(evt.getX(), 0);
		wavePanel.repaint();
	}
	public void wavePanelMouseDragged(java.awt.event.MouseEvent evt){

		Graphics2D grafImage = (Graphics2D) waveImage.getGraphics();
		grafImage.drawImage(img, 0, 0, null);
		int x = evt.getX();

		if(x<0) {
			x = 0;
		}
		else if(x>wavePanel.getWidth()){
			x = wavePanel.getWidth();
		}

		startX = Math.min(xPress, x);
		endX = Math.max(xPress, x);

		int max, min;

		max = (int) Math.max(selStart, x/(double)wavePanel.getX()*(posEnd-posStart));
		min = (int) Math.min(selStart, x/(double)wavePanel.getX()*(posEnd-posStart));

		test = false;
		x = (int) (posStart+(posEnd-posStart)/(double)wavePanel.getWidth()*startX);
		startTime.setValue((double)(x/(double)wave.getSampleCount()*length));
		x = (int)(posStart+(posEnd-posStart)/(double)wavePanel.getWidth()*endX);
		endTime.setValue((double)(x/(double)wave.getSampleCount()*length));
		test = true;

		for(int i = startX; i<endX; i++)
			for(int j = 0; j<wavePanel.getHeight(); j++){
				waveImage.setRGB(i, j, waveImage.getRGB(i,j)*-1);
			}

		wavePanel.repaint();
	}

	public void close() {
		if(odt != null) odt.stop();
	}

	public void stopWavePlayback() {
		playButton.setEnabled(true);
		pauseButton.setEnabled(false);
		stopButton.setEnabled(false);

		if(odt != null) odt.stop();
		containingPanel.repaint();
	}
}
