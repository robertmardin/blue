/*
 * blue - object composition environment for csound
 * Copyright (C) 2013
 * Steven Yi <stevenyi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package blue.score.layers.audio.ui;

import blue.score.ScoreObjectEvent;
import blue.score.ScoreObjectListener;
import blue.score.TimeState;
import blue.score.layers.audio.core.AudioClip;
import blue.ui.core.score.ScoreObjectView;
import blue.ui.core.score.ScoreTopComponent;
import blue.ui.utilities.BlueGradientFactory;
import blue.ui.utilities.audio.AudioWaveformCache;
import blue.ui.utilities.audio.AudioWaveformData;
import blue.ui.utilities.audio.AudioWaveformListener;
import blue.ui.utilities.audio.AudioWaveformUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.WindowManager;

/**
 *
 * @author stevenyi
 */
public class AudioClipPanel extends JPanel
        implements PropertyChangeListener, ScoreObjectListener,
        ScoreObjectView<AudioClip>, LookupListener {

    protected static final AudioWaveformCache waveformCache
            = AudioWaveformCache.getInstance();

    private final AudioClip audioClip;
    private final TimeState timeState;
    boolean selected = false;

    protected static Color selectedBgColor = new Color(255, 255, 255, 128);

    protected static Color selectedBorder1 = selectedBgColor.brighter()
            .brighter();

    protected static Color selectedBorder2 = selectedBgColor.darker().darker();

    protected static Color selectedFontColor = Color.darkGray;
    
    Lookup.Result<AudioClip> result = null;

    AudioWaveformData waveData = null;

    public AudioClipPanel(AudioClip audioClip, TimeState timeState) {
        this.audioClip = audioClip;
        this.timeState = timeState;

        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);

        reset();
        this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    }

    @Override
    public void addNotify() {
        super.addNotify();

        audioClip.addScoreObjectListener(this);
        timeState.addPropertyChangeListener(this);

        ScoreTopComponent scoreTopComponent = (ScoreTopComponent) WindowManager.getDefault().findTopComponent(
                "ScoreTopComponent");
        result = scoreTopComponent.getLookup().lookupResult(AudioClip.class);

        result.addLookupListener(this);

        Collection<? extends AudioClip> soundObjects = result.allInstances();
        setSelected(soundObjects.contains(this.audioClip));
        updateWaveformData();
    }

    @Override
    public void removeNotify() {
        audioClip.removeScoreObjectListener(this);
        timeState.removePropertyChangeListener(this);
        result.removeLookupListener(this);
        result = null;

        this.waveData = null;

        super.removeNotify();
    }

    public void setSelected(boolean selected) {
        if (this.selected == selected) {
            return;
        }
        this.selected = selected;

        repaint();
    }

    public boolean isSelected() {
        return selected;
    }

    private boolean isBright(Color c) {
        return c.getRed() + c.getGreen() + c.getBlue() > (128 * 3);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        int w = getWidth();
        int h = getHeight();

        Color bgColor;
        Color border1;
        Color border2;
        Color fontColor;
        Color waveColor;

        if (isSelected()) {
            bgColor = selectedBgColor;
            border1 = selectedBorder1;
            border2 = selectedBorder2;
            fontColor = selectedFontColor;
        } else {
            bgColor = audioClip.getBackgroundColor();
            border1 = bgColor.brighter().brighter();
            border2 = bgColor.darker().darker();

            fontColor = isBright(bgColor) ? Color.BLACK : Color.WHITE;
        }

        if(isBright(bgColor)) {
            waveColor = bgColor.brighter().brighter();
        } else {
            waveColor = bgColor.darker().darker();
        }
                
        g.setPaint(BlueGradientFactory.getGradientPaint(bgColor));

        g.fillRect(0, 2, w, h - 4);

        g.setColor(border1);
        g.drawLine(0, 2, w - 1, 2);
        g.drawLine(0, 2, 0, h - 4);

        g.setColor(border2);
        g.drawLine(0, h - 3, w, h - 3);
        g.drawLine(w - 1, h - 3, w - 1, 2);


        g.setColor(waveColor);

        g.translate(1, 2);

        AudioWaveformUI.paintWaveForm((Graphics2D) g, this.getHeight(), waveData);

        g.translate(-1, -2);

        g.setColor(fontColor);
        g.drawString(audioClip.getName(), 5, 15);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == this.timeState) {
            switch (evt.getPropertyName()) {
                case "pixelSecond": {
                    if(timeState.getPixelSecond() != waveData.pixelSeconds) {
                        updateWaveformData();
                    }
                    reset();
                    break;
                }
            }
        } 
    }

    private void updateWaveformData() {
        String absFilePath = audioClip.getAudioFile().getAbsolutePath();
        waveData = waveformCache.getAudioWaveformData(
                absFilePath,
                timeState.getPixelSecond());
        
        if (waveData.percentLoadingComplete < 1.0) {
            waveformCache.addAudioWaveformListener(
                    new AudioWaveformListener(absFilePath, this));
        }
    }

    protected void reset() {
        int pixelSecond = timeState.getPixelSecond();
        double x = audioClip.getStartTime() * pixelSecond;
        double width = audioClip.getSubjectiveDuration() * pixelSecond;
        setBounds((int) x, getY(), (int) width, getHeight());
    }

    @Override
    public AudioClip getScoreObject() {
        return audioClip;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection<? extends AudioClip> soundObjects = result.allInstances();
        boolean newSelected = soundObjects.contains(this.audioClip);

        setSelected(newSelected);
    }

    @Override
    public void scoreObjectChanged(ScoreObjectEvent event) {
        if (event.getScoreObject() == this.audioClip) {
            switch (event.getPropertyChanged()) {
                case ScoreObjectEvent.START_TIME:
                case ScoreObjectEvent.DURATION:
                    reset();
                    break;
            }
        }
    }
}
