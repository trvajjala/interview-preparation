package com.innominds.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesOneJava {

    public static void main(String[] args) throws IOException, URISyntaxException {

        final Stream<Path> stream = Files.list(Paths.get("/Volumes/DATA"));

        stream.forEach(file -> {
            System.out.println(file);
        });

        stream.close();
        final Stream<String> s = Files.lines(Paths.get(new URI("file:/Volumes/DATA/Main.java")));
        final List<?> li = s.collect(Collectors.toList());
        System.out.println(li);

        final BufferedReader breader = new BufferedReader(new InputStreamReader(new FileInputStream("/Volumes/DATA/Main.java")));
        breader.lines().forEach(System.err::print);
        breader.close();

        final Runnable f = FilesOneJava::colon;
        f.run();

    }

    static void colon() {
        System.out.println("Colon syntax  verified ");
    }
}

interface Some {

    String hello();
}
