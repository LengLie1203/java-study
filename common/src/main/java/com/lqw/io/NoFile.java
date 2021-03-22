package com.lqw.io;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NoFile {

    public static void main(String[] args) throws IOException {
        Path p = Paths.get("NoFile.java").toAbsolutePath();

        System.out.println(p.toString());
        System.out.println(Files.exists(p));
    }
}
