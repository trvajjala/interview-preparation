package buffers;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsExample {

    public static void main(String[] args) {

        final Path path = Paths.get("/Volume/DATA/Main.java");
        System.out.println(path.getFileName());
    }
}
