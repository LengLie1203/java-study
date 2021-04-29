package com.lqw.standardio;

/**
 * @author LQW
 * @date 2021-04-15 20:50
 **/
// onjava/OSExecute.java
// Run an operating system command
// and send the output to the console
import java.io.*;
class OSExecuteException extends RuntimeException {
    public OSExecuteException(String why) {
        super(why);
    }
}
public class  OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(
                    command.split(" ")).start();
            try (
                    BufferedReader results = new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()));
                    BufferedReader errors = new BufferedReader(
                            new InputStreamReader(
                                    process.getErrorStream()))
            ) {
                results.lines()
                        .forEach(System.out::println);
                err = errors.lines()
                        .peek(System.err::println)
                        .count() > 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (err)
            throw new OSExecuteException(
                    "Errors executing " + command);
    }
}

