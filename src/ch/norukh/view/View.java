package ch.norukh.view;

import ch.norukh.listener.FileListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class View extends JFrame {

    private BufferedReader reader;
    private Timer timer;
    private JPanel content;
    private JButton toggleButton;
    private JLabel textLabel;

    public View() {
        this.setTitle("Java QuickReader");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("Datei");
        menuBar.add(mnFile);

        JMenuItem mntmOpen = new JMenuItem("\u00D6ffnen");
        mntmOpen.addActionListener(new FileListener(this));
        mnFile.add(mntmOpen);

        JMenuItem mntmPrint = new JMenuItem("Drucken");
        mnFile.add(mntmPrint);

        JMenuItem mntmExit = new JMenuItem("Beenden");
        mntmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnFile.add(mntmExit);

        content = new JPanel();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content);
        content.setLayout(new BorderLayout());

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER));
        loadControls(controls);
        content.add(controls, BorderLayout.NORTH);

        JPanel textview = new JPanel();
        textview.setLayout(new BoxLayout(textview, BoxLayout.X_AXIS));
        loadTextview(textview);
        content.add(textview, BorderLayout.CENTER);

        JPanel sliderview = new JPanel();
        sliderview.setLayout(new FlowLayout(FlowLayout.CENTER));
        loadSliderview(sliderview);
        content.add(sliderview, BorderLayout.SOUTH);

        class Prozess extends TimerTask {

            @Override
            public void run() {
                if (reader != null) {
                    renewWord();
                }
            }
        }

        // Thread starten für den Chat
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.getText().equals(">")) {
                    toggleButton.setText("||");
                    setTimer(new Timer());
                    getTimer().schedule(new Prozess(), 0, 1000);
                } else if (toggleButton.getText().equals("||")) {
                    toggleButton.setText(">");
                    getTimer().cancel();
                    textLabel.setText("Datei \u00F6ffnen");
                }
            }
        });
    }

    private void renewWord() {
        try {
            BufferedReader br = this.getReader();
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    Thread.sleep(100);
                    textLabel.setText(words[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadControls(JPanel controls) {
        toggleButton = new JButton(">");
        controls.add(toggleButton);
    }

    private void loadTextview(JPanel controls) {
        textLabel = new JLabel("Datei \u00F6ffnen");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        controls.add(Box.createHorizontalGlue());
        controls.add(textLabel);
        controls.add(Box.createHorizontalGlue());
    }

    private void loadSliderview(JPanel controls) {
        JSlider speedSlider = new JSlider();
        speedSlider.setMinimum(0);
        speedSlider.setMaximum(30);

        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);

        // Standardmarkierungen werden erzeugt
        speedSlider.createStandardLabels(1);

        // Zeichnen der Markierungen wird aktiviert
        speedSlider.setPaintTicks(true);

        // Zeichnen der Labels wird aktiviert
        speedSlider.setPaintLabels(true);

        // Schiebebalken wird auf den Wert 9 gesetzt
        speedSlider.setValue(9);

        controls.add(speedSlider);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

}
