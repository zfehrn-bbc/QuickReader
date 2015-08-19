package ch.norukh;

import ch.norukh.view.View;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Nico on 19.08.2015.
 */
public class TextBlaster implements Runnable {

    private View view;
    private JLabel label;
    Thread thread;

    public TextBlaster(View view, JLabel textLabel) {
        this.setView(view);
        this.setLabel(textLabel);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                BufferedReader br;
                br = FileLoader.br;
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (int i = 0; i < words.length; i++) {
                        if(thread!=null) {
                            Thread.sleep(500 - (View.speed * 2));
                            this.getLabel().setText(words[i]);
                        } else {
                            this.getLabel().setText("Open a txt file");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        thread = null;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
