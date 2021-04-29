package com.lqw.standardio;

import com.lqw.onjava8.TimedAbort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author LQW
 * @date 2021-04-15 20:17
 **/
public class Echo {

    public static void main(String[] args) {
//        reader();


        writer();
    }

    private static void writer() {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }

    private static void reader() {
        TimedAbort abort = new TimedAbort(2);


        new BufferedReader(new InputStreamReader(System.in))
                .lines()
                .peek(s->abort.restart()).forEach(System.out::println);
    }
}
