import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.nio.file.Path;
import java.util.ArrayList;

class Bean {
    @Argument(required = true, multiValued = true, usage = "Input files")
    ArrayList<Path> files = new ArrayList<>();

    @Option(name = "-h", usage = "Return the file as normal form (B, KB ...)")
    boolean h;

    @Option(name = "-c", usage = "Sum files size")
    boolean c;

    @Option(name = "--si", usage = "Use powers of 1000")
    boolean si;
}
