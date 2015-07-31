package eu.dareed.eplus.runner;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class OutputDirectory {
    protected final File directory;
    protected final EnergyPlusArguments arguments;

    public OutputDirectory(EnergyPlusArguments arguments) {
        this.directory = arguments.getOutputDirectory();
        this.arguments = arguments;
    }

    /**
     * Returns the eso file in the current working directory.
     *
     * @return a reference to the eso file as computed by the energy plus arguments.
     */
    public File getVariablesOutput() {
        String fileName = arguments.getOutputPrefix() + "out.eso";
        return Paths.get(directory.getPath(), fileName).toFile();
    }

    /**
     * Returns the output file of the requested meter data.
     *
     * @return a reference to a mtr file as computed by the energy plus arguments.
     */
    public File getMetersOutput() {
        String fileName = arguments.getOutputPrefix() + "out.mtr";
        return Paths.get(directory.getPath(), fileName).toFile();
    }

    public File getInput() {
        return arguments.getInputFile();
    }
}
