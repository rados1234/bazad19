package bazad;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DualStream extends PrintStream {
    public PrintStream consoleOutput = null;
    public PrintStream fileOutput = null;

    public DualStream(final PrintStream consoleOutput, final PrintStream fileOutput) throws FileNotFoundException {
        super(fileOutput, true);
        this.consoleOutput = consoleOutput;
        this.fileOutput = fileOutput;
    }

    @Override
    public void println() {
        consoleOutput.println();
        super.println();
    }

    @Override
    public void println(final Object output) {
       consoleOutput.println(output);
        super.println(output);
    }

    @Override
    public void println(final String output) {
        consoleOutput.println(output);
        super.println(output);
    }

    @Override
    public PrintStream printf(final String output, final Object... variables) {
        consoleOutput.printf(output, variables);
        super.printf(output, variables);
        return this;
    }
}
