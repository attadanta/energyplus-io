package eu.dareed.eplus.runner;

import java.io.File;
import java.nio.file.Paths;

/**
 * Provides quick access to the simulation outputs.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class OutputDirectory {
    protected final File directory;
    protected final String outputPrefix;
    protected final File inputFile;

    public OutputDirectory(File directory, String outputPrefix, File inputFile) {
        this.directory = directory;
        this.outputPrefix = outputPrefix;
        this.inputFile = inputFile;
    }

    public OutputDirectory(EnergyPlusArguments arguments) {
        this.directory = arguments.getOutputDirectory();
        this.outputPrefix = arguments.getOutputPrefix();
        this.inputFile = arguments.getInputFile();
    }

    public File getDirectory() {
        return directory;
    }

    /**
     * Returns the eso file in this working directory.
     *
     * @return a reference to the eso file as computed by the energy plus arguments.
     */
    public File getVariablesOutput() {
        String fileName = outputPrefix + "out.eso";
        return Paths.get(directory.getPath(), fileName).toFile();
    }

    /**
     * Returns the output file of the requested meter data.
     *
     * @return a reference to a mtr file as computed by the energy plus arguments.
     */
    public File getMetersOutput() {
        String fileName = outputPrefix + "out.mtr";
        return Paths.get(directory.getPath(), fileName).toFile();
    }

    public File getInput() {
        return inputFile;
    }
}
