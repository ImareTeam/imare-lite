/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RecognizerOptionsPanel.java
 *
 * Created on 2010-03-26, 10:26:34
 */
package pl.umk.mat.imare.gui;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import pl.umk.mat.imare.io.Wave;
import pl.umk.mat.imare.reco.Recognizer;
import pl.umk.mat.imare.reco.WindowFunction;
import pl.umk.mat.imare.reco.WindowFunctionBlackman;
import pl.umk.mat.imare.reco.WindowFunctionBlackmanNutall;
import pl.umk.mat.imare.reco.WindowFunctionFlatTop;
import pl.umk.mat.imare.reco.WindowFunctionHamming;

/**
 *
 * @author Bartek, morti
 */
public class RecognizerOptionsPanel extends javax.swing.JPanel {

    private ValueWatcher[] watchers = new ValueWatcher[9];
    public static final int TIME_MAX_SEPARATION       = 0;
    public static final int FREQ_LOW_BOUND            = 1;
    public static final int FREQ_HIGH_BOUND           = 2;
    public static final int TIME_SHIFT_APPROX         = 3;
    public static final int NOISE_SIGMA_PARAMETER     = 4;
    public static final int TIME_MIN_DURATION         = 5;
    public static final int OVERTONE_SENSITIVITY      = 6;
    public static final int REPETITION_SENSITIVITY    = 7;
    public static final int MAX_TO_MIN_RATIO          = 8;

    /** Creates new form RecognizerOptionsPanel */
    public RecognizerOptionsPanel() {
        initComponents();
        watchers[0] = new ValueWatcher(timeMaxSeparationValue, timeMaxSeparationSlider, 1000.0, "s");
        watchers[1] = new ValueWatcher(freqLowBoundValue, freqLowBoundSlider, "Hz");
        watchers[2] = new ValueWatcher(freqHighBoundValue, freqHighBoundSlider, "Hz");
        watchers[3] = new ValueWatcher(timeShiftApproxValue, timeShiftApproxSlider, 10000.0, "s");
        watchers[4] = new ValueWatcher(noiseSigmaParameterValue, noiseSigmaParameterSlider);
        watchers[5] = new ValueWatcher(timeMinDurationValue, timeMinDurationSlider, 100.0, "s");
        watchers[6] = new ValueWatcher(overtoneSensitivityValue, overtoneSensitivitySlider, 10.0, "");
        watchers[7] = new ValueWatcher(repetitionSensitivityValue, repetitionSensitivitySlider, 10.0, "");
        watchers[8] = new ValueWatcher(maxToMinRatioValue, maxToMinRatioSlider);

		restoreDefaults();
    }

    /**
     * Zwraca wartosc opcji podanego typu
     * @param type Typ opcji.
     * @return Wartość dla podanego typu.
     */
    public double getValue(int type) {
        return watchers[type].getValue();
    }

    /**
     * Zwraca wybraną funkcję okna.
     * @return Funkcja okna.
     */
    public WindowFunction getWindowFunction() {
        switch (windowFunctionCombo.getSelectedIndex()) {
            case 0:
                return null;

            case 1:
                return new WindowFunctionBlackman();

            case 2:
                return new WindowFunctionBlackmanNutall();

            case 3:
                return new WindowFunctionHamming();

            case 4:
                return new WindowFunctionFlatTop();
        }

        return null;
    }

    /**
     * Tworzy i zwraca recognizera z odpowiednimi ustawieniami.
     * @param wave Plik audio do rozpoznawania.
     * @return Zwraca nowo utworzonego recognizera.
     */
    public Recognizer getRecognizer(Wave wave) {
        Recognizer reco = new Recognizer(wave);
        reco.setWindowFunction(getWindowFunction());

        reco.setTimeMaxSeparation(getValue(TIME_MAX_SEPARATION));
        reco.setFreqLowBound(getValue(FREQ_LOW_BOUND));
        reco.setFreqHighBoundApprox(getValue(FREQ_HIGH_BOUND));
        reco.setTimeShiftApprox(getValue(TIME_SHIFT_APPROX));
        reco.setNoiseMedianParameter(getValue(NOISE_SIGMA_PARAMETER));
        reco.setTimeMinDuration(getValue(TIME_MIN_DURATION));
        reco.setOvertoneSensitivity(getValue(OVERTONE_SENSITIVITY));
        reco.setRepetitionSensitivity(getValue(REPETITION_SENSITIVITY));
        reco.setMaxToMinRatio(getValue(MAX_TO_MIN_RATIO));

        return reco;
    }

    public Recognizer getRecognizer(Wave wave, int start, int end) {
        Recognizer reco = new Recognizer(wave, start, end);
        reco.setWindowFunction(getWindowFunction());

        reco.setTimeMaxSeparation(getValue(TIME_MAX_SEPARATION));
        reco.setFreqLowBound(getValue(FREQ_LOW_BOUND));
        reco.setFreqHighBoundApprox(getValue(FREQ_HIGH_BOUND));
        reco.setTimeShiftApprox(getValue(TIME_SHIFT_APPROX));
        reco.setNoiseMedianParameter(getValue(NOISE_SIGMA_PARAMETER));
        reco.setTimeMinDuration(getValue(TIME_MIN_DURATION));
        reco.setOvertoneSensitivity(getValue(OVERTONE_SENSITIVITY));
        reco.setRepetitionSensitivity(getValue(REPETITION_SENSITIVITY));
        reco.setMaxToMinRatio(getValue(MAX_TO_MIN_RATIO));

        return reco;
    }

    public final void restoreDefaults() {
        windowFunctionCombo.setSelectedIndex(0);

        timeMaxSeparationSlider.setValue(50);
        freqLowBoundSlider.setValue(110);
        freqHighBoundSlider.setValue(10000);
        timeShiftApproxSlider.setValue(100);
        noiseSigmaParameterSlider.setValue(20);
        timeMinDurationSlider.setValue(15);
        overtoneSensitivitySlider.setValue(10);
        repetitionSensitivitySlider.setValue(10);
        maxToMinRatioSlider.setValue(50);
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

    windowFunctionPanel = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    windowFunctionCombo = new javax.swing.JComboBox();
    timeMaxSeparationPanel = new javax.swing.JPanel();
    jLabel22 = new javax.swing.JLabel();
    timeMaxSeparationValue = new javax.swing.JLabel();
    jPanel7 = new javax.swing.JPanel();
    jLabel23 = new javax.swing.JLabel();
    timeMaxSeparationSlider = new javax.swing.JSlider();
    jLabel24 = new javax.swing.JLabel();
    freqLowBoundPanel = new javax.swing.JPanel();
    jLabel25 = new javax.swing.JLabel();
    freqLowBoundValue = new javax.swing.JLabel();
    jPanel9 = new javax.swing.JPanel();
    jLabel26 = new javax.swing.JLabel();
    freqLowBoundSlider = new javax.swing.JSlider();
    jLabel27 = new javax.swing.JLabel();
    freqHighBoundPanel = new javax.swing.JPanel();
    jLabel28 = new javax.swing.JLabel();
    freqHighBoundValue = new javax.swing.JLabel();
    jPanel12 = new javax.swing.JPanel();
    jLabel29 = new javax.swing.JLabel();
    freqHighBoundSlider = new javax.swing.JSlider();
    jLabel30 = new javax.swing.JLabel();
    timeShiftApproxPanel = new javax.swing.JPanel();
    jLabel31 = new javax.swing.JLabel();
    timeShiftApproxValue = new javax.swing.JLabel();
    jPanel14 = new javax.swing.JPanel();
    jLabel32 = new javax.swing.JLabel();
    timeShiftApproxSlider = new javax.swing.JSlider();
    jLabel33 = new javax.swing.JLabel();
    noiseSigmaParameterPanel = new javax.swing.JPanel();
    jLabel34 = new javax.swing.JLabel();
    noiseSigmaParameterValue = new javax.swing.JLabel();
    jPanel16 = new javax.swing.JPanel();
    jLabel35 = new javax.swing.JLabel();
    noiseSigmaParameterSlider = new javax.swing.JSlider();
    jLabel36 = new javax.swing.JLabel();
    timeMinDurationPanel = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    timeMinDurationValue = new javax.swing.JLabel();
    jPanel18 = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    timeMinDurationSlider = new javax.swing.JSlider();
    jLabel37 = new javax.swing.JLabel();
    overtoneSensitivityPanel = new javax.swing.JPanel();
    jLabel41 = new javax.swing.JLabel();
    overtoneSensitivityValue = new javax.swing.JLabel();
    jPanel21 = new javax.swing.JPanel();
    jLabel42 = new javax.swing.JLabel();
    overtoneSensitivitySlider = new javax.swing.JSlider();
    jLabel43 = new javax.swing.JLabel();
    repetitionSensitivityPanel = new javax.swing.JPanel();
    jLabel44 = new javax.swing.JLabel();
    repetitionSensitivityValue = new javax.swing.JLabel();
    jPanel22 = new javax.swing.JPanel();
    jLabel45 = new javax.swing.JLabel();
    repetitionSensitivitySlider = new javax.swing.JSlider();
    jLabel46 = new javax.swing.JLabel();
    maxToMinRatioPanel = new javax.swing.JPanel();
    jLabel38 = new javax.swing.JLabel();
    maxToMinRatioValue = new javax.swing.JLabel();
    jPanel20 = new javax.swing.JPanel();
    jLabel39 = new javax.swing.JLabel();
    maxToMinRatioSlider = new javax.swing.JSlider();
    jLabel40 = new javax.swing.JLabel();

    windowFunctionPanel.setLayout(new java.awt.GridBagLayout());

    jLabel4.setText("Funkcja okna:");
    windowFunctionPanel.add(jLabel4, new java.awt.GridBagConstraints());

    windowFunctionCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dirichlet", "Blackman", "Blackman-Nutall", "Hamming", "Flat top" }));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    windowFunctionPanel.add(windowFunctionCombo, gridBagConstraints);

    timeMaxSeparationPanel.setLayout(new java.awt.GridBagLayout());

    jLabel22.setText("Maksymalna nieciągłość dźwięku:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    timeMaxSeparationPanel.add(jLabel22, gridBagConstraints);

    timeMaxSeparationValue.setText("0");
    timeMaxSeparationPanel.add(timeMaxSeparationValue, new java.awt.GridBagConstraints());

    jPanel7.setLayout(new java.awt.GridBagLayout());

    jLabel23.setText("0.1 s");
    jLabel23.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel7.add(jLabel23, gridBagConstraints);

    timeMaxSeparationSlider.setMinimum(10);
    timeMaxSeparationSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel7.add(timeMaxSeparationSlider, gridBagConstraints);

    jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel24.setText("0.01 s");
    jLabel24.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel7.add(jLabel24, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    timeMaxSeparationPanel.add(jPanel7, gridBagConstraints);

    freqLowBoundPanel.setLayout(new java.awt.GridBagLayout());

    jLabel25.setText("Minimalna częstotliwość:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    freqLowBoundPanel.add(jLabel25, gridBagConstraints);

    freqLowBoundValue.setText("0");
    freqLowBoundPanel.add(freqLowBoundValue, new java.awt.GridBagConstraints());

    jPanel9.setLayout(new java.awt.GridBagLayout());

    jLabel26.setText("300 Hz");
    jLabel26.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel9.add(jLabel26, gridBagConstraints);

    freqLowBoundSlider.setMaximum(300);
    freqLowBoundSlider.setMinimum(50);
    freqLowBoundSlider.setValue(110);
    freqLowBoundSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel9.add(freqLowBoundSlider, gridBagConstraints);

    jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel27.setText("50 Hz");
    jLabel27.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel9.add(jLabel27, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    freqLowBoundPanel.add(jPanel9, gridBagConstraints);

    freqHighBoundPanel.setLayout(new java.awt.GridBagLayout());

    jLabel28.setText("Maksymalna częstotliwość:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    freqHighBoundPanel.add(jLabel28, gridBagConstraints);

    freqHighBoundValue.setText("0");
    freqHighBoundPanel.add(freqHighBoundValue, new java.awt.GridBagConstraints());

    jPanel12.setLayout(new java.awt.GridBagLayout());

    jLabel29.setText("16 kHz");
    jLabel29.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel12.add(jLabel29, gridBagConstraints);

    freqHighBoundSlider.setMaximum(16000);
    freqHighBoundSlider.setMinimum(5000);
    freqHighBoundSlider.setValue(10000);
    freqHighBoundSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel12.add(freqHighBoundSlider, gridBagConstraints);

    jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel30.setText("5 kHz");
    jLabel30.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel12.add(jLabel30, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    freqHighBoundPanel.add(jPanel12, gridBagConstraints);

    timeShiftApproxPanel.setLayout(new java.awt.GridBagLayout());

    jLabel31.setText("Krok czasowy:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    timeShiftApproxPanel.add(jLabel31, gridBagConstraints);

    timeShiftApproxValue.setText("0");
    timeShiftApproxPanel.add(timeShiftApproxValue, new java.awt.GridBagConstraints());

    jPanel14.setLayout(new java.awt.GridBagLayout());

    jLabel32.setText("0.02 s");
    jLabel32.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel14.add(jLabel32, gridBagConstraints);

    timeShiftApproxSlider.setMaximum(200);
    timeShiftApproxSlider.setMinimum(10);
    timeShiftApproxSlider.setValue(100);
    timeShiftApproxSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel14.add(timeShiftApproxSlider, gridBagConstraints);

    jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel33.setText("0.001 s");
    jLabel33.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel14.add(jLabel33, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    timeShiftApproxPanel.add(jPanel14, gridBagConstraints);

    noiseSigmaParameterPanel.setLayout(new java.awt.GridBagLayout());

    jLabel34.setText("Próg rejestrowanego sygnału:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    noiseSigmaParameterPanel.add(jLabel34, gridBagConstraints);

    noiseSigmaParameterValue.setText("0");
    noiseSigmaParameterPanel.add(noiseSigmaParameterValue, new java.awt.GridBagConstraints());

    jPanel16.setLayout(new java.awt.GridBagLayout());

    jLabel35.setText("50");
    jLabel35.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel16.add(jLabel35, gridBagConstraints);

    noiseSigmaParameterSlider.setMaximum(50);
    noiseSigmaParameterSlider.setMinimum(1);
    noiseSigmaParameterSlider.setValue(20);
    noiseSigmaParameterSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel16.add(noiseSigmaParameterSlider, gridBagConstraints);

    jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel36.setText("1");
    jLabel36.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel16.add(jLabel36, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    noiseSigmaParameterPanel.add(jPanel16, gridBagConstraints);

    timeMinDurationPanel.setLayout(new java.awt.GridBagLayout());

    jLabel5.setText("Minimalna długość dźwięku:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    timeMinDurationPanel.add(jLabel5, gridBagConstraints);

    timeMinDurationValue.setText("0");
    timeMinDurationPanel.add(timeMinDurationValue, new java.awt.GridBagConstraints());

    jPanel18.setLayout(new java.awt.GridBagLayout());

    jLabel6.setText("0.5 s");
    jLabel6.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel18.add(jLabel6, gridBagConstraints);

    timeMinDurationSlider.setMaximum(50);
    timeMinDurationSlider.setMinimum(1);
    timeMinDurationSlider.setValue(15);
    timeMinDurationSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel18.add(timeMinDurationSlider, gridBagConstraints);

    jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel37.setText("0.01 s");
    jLabel37.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel18.add(jLabel37, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    timeMinDurationPanel.add(jPanel18, gridBagConstraints);

    overtoneSensitivityPanel.setLayout(new java.awt.GridBagLayout());

    jLabel41.setText("Czułość wykrywania dźwięków wyższych rzędów:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    overtoneSensitivityPanel.add(jLabel41, gridBagConstraints);

    overtoneSensitivityValue.setText("0");
    overtoneSensitivityPanel.add(overtoneSensitivityValue, new java.awt.GridBagConstraints());

    jPanel21.setLayout(new java.awt.GridBagLayout());

    jLabel42.setText("5.0");
    jLabel42.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel21.add(jLabel42, gridBagConstraints);

    overtoneSensitivitySlider.setMaximum(50);
    overtoneSensitivitySlider.setMinimum(1);
    overtoneSensitivitySlider.setValue(10);
    overtoneSensitivitySlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel21.add(overtoneSensitivitySlider, gridBagConstraints);

    jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel43.setText("0.1");
    jLabel43.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel21.add(jLabel43, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    overtoneSensitivityPanel.add(jPanel21, gridBagConstraints);

    repetitionSensitivityPanel.setLayout(new java.awt.GridBagLayout());

    jLabel44.setText("Czułość wykrywania repetycji:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    repetitionSensitivityPanel.add(jLabel44, gridBagConstraints);

    repetitionSensitivityValue.setText("0");
    repetitionSensitivityPanel.add(repetitionSensitivityValue, new java.awt.GridBagConstraints());

    jPanel22.setLayout(new java.awt.GridBagLayout());

    jLabel45.setText("5.0");
    jLabel45.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel22.add(jLabel45, gridBagConstraints);

    repetitionSensitivitySlider.setMaximum(50);
    repetitionSensitivitySlider.setMinimum(1);
    repetitionSensitivitySlider.setValue(10);
    repetitionSensitivitySlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel22.add(repetitionSensitivitySlider, gridBagConstraints);

    jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel46.setText("0.1");
    jLabel46.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel22.add(jLabel46, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    repetitionSensitivityPanel.add(jPanel22, gridBagConstraints);

    maxToMinRatioPanel.setLayout(new java.awt.GridBagLayout());

    jLabel38.setText("Stosunek najgłośniejszego do najcichszego dźwięku:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
    maxToMinRatioPanel.add(jLabel38, gridBagConstraints);

    maxToMinRatioValue.setText("0");
    maxToMinRatioPanel.add(maxToMinRatioValue, new java.awt.GridBagConstraints());

    jPanel20.setLayout(new java.awt.GridBagLayout());

    jLabel39.setText("200");
    jLabel39.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel20.add(jLabel39, gridBagConstraints);

    maxToMinRatioSlider.setMaximum(200);
    maxToMinRatioSlider.setMinimum(1);
    maxToMinRatioSlider.setName("slTimeMinDuration"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel20.add(maxToMinRatioSlider, gridBagConstraints);

    jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel40.setText("1");
    jLabel40.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel20.add(jLabel40, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 320;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    maxToMinRatioPanel.add(jPanel20, gridBagConstraints);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(windowFunctionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(timeMaxSeparationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(freqLowBoundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(freqHighBoundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(timeShiftApproxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(noiseSigmaParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(timeMinDurationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(overtoneSensitivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(repetitionSensitivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(maxToMinRatioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(windowFunctionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(timeMaxSeparationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(freqLowBoundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(freqHighBoundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(timeShiftApproxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(noiseSigmaParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(timeMinDurationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(overtoneSensitivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(repetitionSensitivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(maxToMinRatioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel freqHighBoundPanel;
  private javax.swing.JSlider freqHighBoundSlider;
  private javax.swing.JLabel freqHighBoundValue;
  private javax.swing.JPanel freqLowBoundPanel;
  private javax.swing.JSlider freqLowBoundSlider;
  private javax.swing.JLabel freqLowBoundValue;
  private javax.swing.JLabel jLabel22;
  private javax.swing.JLabel jLabel23;
  private javax.swing.JLabel jLabel24;
  private javax.swing.JLabel jLabel25;
  private javax.swing.JLabel jLabel26;
  private javax.swing.JLabel jLabel27;
  private javax.swing.JLabel jLabel28;
  private javax.swing.JLabel jLabel29;
  private javax.swing.JLabel jLabel30;
  private javax.swing.JLabel jLabel31;
  private javax.swing.JLabel jLabel32;
  private javax.swing.JLabel jLabel33;
  private javax.swing.JLabel jLabel34;
  private javax.swing.JLabel jLabel35;
  private javax.swing.JLabel jLabel36;
  private javax.swing.JLabel jLabel37;
  private javax.swing.JLabel jLabel38;
  private javax.swing.JLabel jLabel39;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel40;
  private javax.swing.JLabel jLabel41;
  private javax.swing.JLabel jLabel42;
  private javax.swing.JLabel jLabel43;
  private javax.swing.JLabel jLabel44;
  private javax.swing.JLabel jLabel45;
  private javax.swing.JLabel jLabel46;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel12;
  private javax.swing.JPanel jPanel14;
  private javax.swing.JPanel jPanel16;
  private javax.swing.JPanel jPanel18;
  private javax.swing.JPanel jPanel20;
  private javax.swing.JPanel jPanel21;
  private javax.swing.JPanel jPanel22;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JPanel jPanel9;
  private javax.swing.JPanel maxToMinRatioPanel;
  private javax.swing.JSlider maxToMinRatioSlider;
  private javax.swing.JLabel maxToMinRatioValue;
  private javax.swing.JPanel noiseSigmaParameterPanel;
  private javax.swing.JSlider noiseSigmaParameterSlider;
  private javax.swing.JLabel noiseSigmaParameterValue;
  private javax.swing.JPanel overtoneSensitivityPanel;
  private javax.swing.JSlider overtoneSensitivitySlider;
  private javax.swing.JLabel overtoneSensitivityValue;
  private javax.swing.JPanel repetitionSensitivityPanel;
  private javax.swing.JSlider repetitionSensitivitySlider;
  private javax.swing.JLabel repetitionSensitivityValue;
  private javax.swing.JPanel timeMaxSeparationPanel;
  private javax.swing.JSlider timeMaxSeparationSlider;
  private javax.swing.JLabel timeMaxSeparationValue;
  private javax.swing.JPanel timeMinDurationPanel;
  private javax.swing.JSlider timeMinDurationSlider;
  private javax.swing.JLabel timeMinDurationValue;
  private javax.swing.JPanel timeShiftApproxPanel;
  private javax.swing.JSlider timeShiftApproxSlider;
  private javax.swing.JLabel timeShiftApproxValue;
  private javax.swing.JComboBox windowFunctionCombo;
  private javax.swing.JPanel windowFunctionPanel;
  // End of variables declaration//GEN-END:variables

    private DecimalFormat decFormat = new DecimalFormat("0.###");

    /**
     * Klasa monitoruje pozycję konkretnego suwaka
     * i ustawia odpowiadającą mu etykietę na aktualną wartość.
     * @author: morti
     */
    private class ValueWatcher implements ChangeListener {

        private JLabel label;
        private JSlider slider;
        private double value, divisor;
        private String suffix = "";

        /**
         * Tworzy nowy obiekt klasy ValueWatcher.
         * @param label Etykieta dla suwaka
         * @param slider Suwak z wartościami
         */
        public ValueWatcher(JLabel label, JSlider slider) {
            init(label, slider, 1.0, "");
        }

        /**
         * Tworzy nowy obiekt klasy ValueWatcher.
         * @param label Etykieta dla suwaka
         * @param slider Suwak z wartościami
         * @param divisor Jeśli potrzebne są wartości niecałkowite
         * to należy ustawić odpowiedni dzielnik. Np. chcąc otrzymać
         * liczby z rzedziału [0,1] należy ustawić wartości suwaka na [0, 100]
         * oraz podać divisor = 100.0.
         */
        public ValueWatcher(JLabel label, JSlider slider, double divisor) {
            init(label, slider, divisor, "");
        }

        /**
         * Tworzy nowy obiekt klasy ValueWatcher.
         * @param label Etykieta dla suwaka
         * @param slider Suwak z wartościami
         * @param suffix Suffiks dla etykiety. Zazwyczaj jednostka miary.
         */
        public ValueWatcher(JLabel label, JSlider slider, String suffix) {
            init(label, slider, 1.0, suffix);
        }

        /**
         * Tworzy nowy obiekt klasy ValueWatcher.
         * @param label Etykieta dla suwaka
         * @param slider Suwak z wartościami
         * @param divisor Jeśli potrzebne są wartości niecałkowite
         * to należy ustawić odpowiedni dzielnik. Np. chcąc otrzymać
         * liczby z rzedziału [0,1] należy ustawić wartości suwaka na [0, 100]
         * oraz podać divisor = 100.0.
         * @param suffix Suffiks dla etykiety. Zazwyczaj jednostka miary.
         */
        public ValueWatcher(JLabel label, JSlider slider, double divisor, String suffix) {
            init(label, slider, divisor, suffix);
        }

        /**
         * Inicjuje obiekt odpowiednimi wartościami.
         * Metoda używana wewnętrznie przez klasę.
         * @param label Etykieta dla suwaka
         * @param slider Suwak z wartościami
         * @param divisor Jeśli potrzebne są wartości niecałkowite
         * to należy ustawić odpowiedni dzielnik. Np. chcąc otrzymać
         * liczby z rzedziału [0,1] należy ustawić wartości suwaka na [0, 100]
         * oraz podać divisor = 100.0.
         * @param suffix Suffiks dla etykiety. Zazwyczaj jednostka miary.
         */
        private void init(JLabel label, JSlider slider, double divisor, String suffix) {
            this.slider = slider;
            this.label = label;
            this.divisor = divisor;
            this.suffix = suffix;

            slider.addChangeListener(this);
            update();
        }

        /**
         * Wylicza wartość oraz ustawia tekst etykiety.
         */
        private void update() {
            value = (double) slider.getValue() / divisor;
            label.setText(decFormat.format(value)
                    + (suffix.isEmpty() ? "" : " " + suffix));
        }

        /**
         * Nasłuchuje zmian od suwaka.
         * @param e Nie używane.
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            update();
        }

        /**
         * Pobiera aktualną wartość (już wyliczoną).
         * @return Zwraca wartość opcji.
         */
        public double getValue() {
            return value;
        }
    }
}
