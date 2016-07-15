package eu.dareed.eplus.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class EnergyPlusRunner {
    protected final File energyPlusPath;
    protected final EnergyPlusArguments arguments;

    public EnergyPlusRunner(String energyPlusPath, EnergyPlusArguments arguments) {
        this.energyPlusPath = new File(energyPlusPath);
        this.arguments = arguments;
    }

    public OutputDirectory getOutputs() {
        return new OutputDirectory(arguments);
    }

    public boolean canExecute() {
        return energyPlusPath.exists() && energyPlusPath.canExecute();
    }

    public Process start() throws IOException {
        List<String> command = new ArrayList<>();
        command.add(energyPlusPath.getPath());
        command.addAll(arguments.getArgumentsList());

        return new ProcessBuilder(command).start();
    }
}
