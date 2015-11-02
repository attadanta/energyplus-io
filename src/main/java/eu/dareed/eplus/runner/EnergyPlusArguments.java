package eu.dareed.eplus.runner;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class EnergyPlusArguments {
    protected final File outputDirectory;
    protected final File dataDictionary;
    protected final File weatherFile;
    protected final File inputFile;

    protected String outputPrefix;

    public EnergyPlusArguments(String outputDirectory, String dataDictionary, String weatherFile, String inputFile) {
        this.outputDirectory = new File(outputDirectory);
        this.dataDictionary = new File(dataDictionary);
        this.weatherFile = new File(weatherFile);
        this.inputFile = new File(inputFile);
    }

    public String getOutputPrefix() {
        return outputPrefix;
    }

    public void setOutputPrefix(String outputPrefix) {
        this.outputPrefix = outputPrefix;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public boolean createOutputDirectory() {
        return outputDirectory.mkdir();
    }

    public File getDataDictionary() {
        return dataDictionary;
    }

    public File getWeatherFile() {
        return weatherFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public boolean validate() {
        return outputDirectoryExists()
                && dataDictionaryExists()
                && weatherFileExists()
                && inputFileExists();
    }

    public boolean outputDirectoryExists() {
        return outputDirectory.exists();
    }

    public boolean dataDictionaryExists() {
        return dataDictionary.exists();
    }

    public boolean weatherFileExists() {
        return weatherFile.exists();
    }

    public boolean inputFileExists() {
        return inputFile.exists();
    }

    public List<String> getArgumentsList() {
        return Arrays.asList("-d", outputDirectory.getPath(), "-i", dataDictionary.getPath(),
                "-w", weatherFile.getPath(), "-p", outputPrefix == null ? "eplus" : outputPrefix,
                inputFile.getPath());
    }
}
