package com.lqw.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Stream;

public class Property {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("common/src/main/java/com/lqw/io/Property.java");//.toAbsolutePath();

        System.out.println(path);
        System.out.println(Files.exists(path));


//        List<String> lines = Files.readAllLines(path);
//        lines.forEach(i-> System.out.println(i));


//        Files.write(Paths.get("Cheese.txt"), lines);

        System.out.println(Files.size(path));

        try(
                Stream<String> input =
                        Files.lines(path);
                PrintWriter output =
                        new PrintWriter("StreamInAndOut.txt")
        ) {
            input.map(String::toUpperCase)
                    .forEachOrdered(output::println);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }
}
