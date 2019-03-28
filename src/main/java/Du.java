import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;
import java.nio.file.Path;

public class Du {
    public static void main(String[] args) throws CmdLineException, IOException {
        Bean bean = new Bean();
        DuConclude du = new DuConclude();
        CmdLineParser parser = new CmdLineParser(bean);
        parser.parseArgument(args);
        try {
            if (bean.si) du.si();
            if (bean.c) {
                du.printer(bean, "all files", du.c(bean.files));
            } else {
                for (Path file : bean.files) {
                    du.printer(bean, du.name(file), du.size(file));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " not found");
        }
    }

}
