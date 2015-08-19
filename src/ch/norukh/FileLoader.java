package ch.norukh;

import ch.norukh.view.View;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;

/**
 * Created by Nico on 17.08.2015.
 */
public class FileLoader {

    private static View view;

    public static void main(String[] args) {
        view = new View();
        view.setVisible(true);
    }

    public static BufferedReader loadFile(File file) {
        BufferedReader bufferedReader = null;
        try {
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Datei nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {
                FileReader fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }
}
