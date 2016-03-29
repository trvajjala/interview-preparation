package one.liner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class OneLiners {

    public static void main(String[] args) throws Exception {

        Files.lines(Paths.get("/Volumes/DATA/JoinExample.java")).forEach(System.out::println);

        new Random().ints().limit(2).forEach(System.out::println);

        "hello".chars().sorted().forEach(ch -> System.out.printf("%c  ", ch));
        System.out.println();
        Pattern.compile(" ").splitAsStream("a be cee").forEach(System.err::println);
    }
}
