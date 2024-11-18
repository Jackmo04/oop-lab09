package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    /**
     * Create a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel fileChooser = new JPanel(new BorderLayout());
        final JTextField pathField = new JTextField();
        pathField.setEditable(false);
        updatePathField(pathField);
        final JButton browse = new JButton("Browse...");
        fileChooser.add(pathField, BorderLayout.CENTER);
        fileChooser.add(browse, BorderLayout.LINE_END);
        canvas.add(fileChooser, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Handlers
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeToFile(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final var fc = new JFileChooser(controller.getFile());
                switch (fc.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(fc.getSelectedFile());
                        updatePathField(pathField);
                        break;

                    case JFileChooser.CANCEL_OPTION:
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                frame, "An error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
    }

    private void updatePathField(final JTextField field) {
        field.setText(this.controller.getPathString());
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
     * Launch the application.
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
