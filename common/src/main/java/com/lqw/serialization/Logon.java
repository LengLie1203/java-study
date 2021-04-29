package com.lqw.serialization;

/**
 * @author LQW
 * @date 2021-04-15 21:44
 **/
// serialization/Logon.java
// Demonstrates the "transient" keyword
import com.lqw.concurrent.Nap;

import java.util.concurrent.*;
import java.io.*;
import java.util.*;
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;
    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }
    @Override
    public String toString() {
        return "logon info: \n username: " +
                username + "\n date: " + date +
                "\n password: " + password;
    }
    public static void main(String[] args) {
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.println("logon a = " + a);
        try(
                ObjectOutputStream o =
                        new ObjectOutputStream(
                                new FileOutputStream("Logon.dat"))
        ) {
            o.writeObject(a);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        new Nap(1);
// Now get them back:
        try(
                ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream("Logon.dat"))
        ) {
            System.out.println(
                    "Recovering object at " + new Date());
            a = (Logon)in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("logon a = " + a);
    }
}

