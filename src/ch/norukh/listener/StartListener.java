package ch.norukh.listener;

import ch.norukh.TextBlaster;
import ch.norukh.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nico on 19.08.2015.
 */
public class StartListener implements ActionListener {

    private View view;
    private JButton button;
    private JLabel label;
    private JSlider slider;
    private TextBlaster textBlaster = null;

    public StartListener(View view, JButton button, JLabel label, JSlider slider) {
        this.label = label;
        this.button = button;
        this.view = view;
        this.slider = slider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.button.getText().equals(">")) {
            this.button.setText("||");
            this.slider.setEnabled(false);

            textBlaster = new TextBlaster(this.view, this.label);
            textBlaster.start();

        } else if (this.button.getText().equals("||") && textBlaster != null) {
            this.button.setText(">");
            textBlaster.stop();
            this.slider.setEnabled(true);
            this.label.setText("Open a txt file");
        }
    }
}
