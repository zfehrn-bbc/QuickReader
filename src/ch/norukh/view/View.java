package ch.norukh.view;

import ch.norukh.listener.FileListener;
import ch.norukh.listener.StartListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class View extends JFrame {

    public static int speed = 90;
    private JPanel content;
    private JButton toggleButton;
    private JLabel textLabel;
    private JSlider speedSlider;

    public View() {
        this.setTitle("Java QuickReader");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmOpen = new JMenuItem("Open file");
        mntmOpen.addActionListener(new FileListener(this));
        mnFile.add(mntmOpen);

        JMenuItem mntmExit = new JMenuItem("Exit");
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

        toggleButton.addActionListener(new StartListener(this, toggleButton, textLabel, speedSlider));
    }

    private void loadControls(JPanel controls) {
        toggleButton = new JButton(">");
        controls.add(toggleButton);
    }

    private void loadTextview(JPanel controls) {
        textLabel = new JLabel("Open a txt file");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        controls.add(Box.createHorizontalGlue());
        controls.add(textLabel);
        controls.add(Box.createHorizontalGlue());
    }

    private void loadSliderview(JPanel controls) {
        speedSlider = new JSlider();
        speedSlider.setMinimum(80);
        speedSlider.setMaximum(200);

        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);

        // Schiebebalken wird auf den Wert 9 gesetzt
        speedSlider.setValue(90);

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speed = speedSlider.getValue();
            }
        });

        controls.add(speedSlider);
    }

    public JLabel getTextLabel() {
        return textLabel;
    }
}
