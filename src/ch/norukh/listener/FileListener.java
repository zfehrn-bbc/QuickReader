package ch.norukh.listener;

import ch.norukh.FileLoader;
import ch.norukh.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Nico on 17.08.2015.
 */
public class FileListener implements ActionListener {


    private View view;
    private File file;

    public FileListener(View view) {
       this.setView(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        int rVal = chooser.showOpenDialog(this.getView());
        if (rVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            view.getTextLabel().setText("Loaded: " + file.getName());
            FileLoader.loadFile(file);
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
