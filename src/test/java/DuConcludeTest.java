import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DuConcludeTest {
    private DuConclude test = new DuConclude();
    private long size;
    private long e;

    @Test
    void c() throws IOException {
        Path first = Paths.get("/home/maratdin7/study_language/Java/CubeEfford/src");
        Path second = Paths.get("/home/maratdin7/study_language/Java/CubeEfford/target");
        ArrayList<Path> files = new ArrayList<>();
        files.add(first);
        files.add(second);
        size = test.size(first) + test.size(second);
        e = size / 3;
        long filesSize = test.c(files);
        assertTrue(abs(filesSize - size) <= e);
    }

    @Test()
    void size() throws IOException {
        Path file = Paths.get("/home/maratdin7/study_language/Java/CubeEfford");
        size = 105676;
        e = size / 3;
        long fileSize = test.size(file);
        assertTrue(abs(fileSize - size) <= e);

        Path throwFile = Paths.get("dfsfsjfios");
        assertThrows(IllegalArgumentException.class, () -> test.size(throwFile));
    }
}