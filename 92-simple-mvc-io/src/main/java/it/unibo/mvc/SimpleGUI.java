package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    /**
     * Create a new SimpleGUI
     */
    public SimpleGUI() {
        JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screenSize.getWidth();
        final int sh = (int) screenSize.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Launch the application
     * @param args unused
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }

}
