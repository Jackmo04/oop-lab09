package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private File file;

    /**
     * Creates a new Controller that initially works on the provided {@link File}.
     * 
     * @param file The file to work with
     * @throws NullPointerExeption If the provided file is <code>null</code>
     */
    public Controller(final File file) {
        this.file = Objects.requireNonNull(file);
    }

    /**
     * Creates a new Controller that initially works on the file
     * <code>output.txt</code>
     * in the home directory of the user.
     */
    public Controller() {
        this(new File(HOME_DIR + FILE_SEPARATOR + "output.txt"));
    }

    /**
     * Get the current {@link File}.
     * 
     * @return The current file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Set a new file as the current one.
     * 
     * @param file The file to be set as current
     * @throws NullPointerException If the provided file is <code>null</code>
     */
    public void setFile(final File file) {
        this.file = Objects.requireNonNull(file);
    }

    /**
     * Get the path in <code>String</code> format.
     * 
     * @return The path string
     */
    public String getPathString() {
        return this.file.getAbsolutePath();
    }

    /**
     * Write a <code>String</code> to the currently active file.
     * 
     * @param input The <code>String</code> to be written to the current file
     * @throws IOException
     */
    public void writeToFile(final String input) throws IOException {
        try (PrintStream ps = new PrintStream(this.file)) {
            ps.print(input);
        }
    }

}
