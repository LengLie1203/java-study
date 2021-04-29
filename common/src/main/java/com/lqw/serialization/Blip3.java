package com.lqw.serialization;

/**
 * @author LQW
 * @date 2021-04-15 21:35
 **/
// serialization/Blip3.java
// Reconstructing an externalizable object

import java.io.*;

public class Blip3 implements Externalizable {
    private int i;
    private String s; // No initialization
    private String c;

    public Blip3() {
        System.out.println("Blip3 Constructor");
        // s, i not initialized
    }

    public Blip3(String x, String y, int a) {
        System.out.println("Blip3(String x, int a)");
        s = x;
        i = a;
        c = y;
        // s & i initialized only in non-no-arg constructor.
    }

    @Override
    public String toString() {
        return s + c + i;
    }

    @Override
    public void writeExternal(ObjectOutput out)
            throws IOException {
        System.out.println("Blip3.writeExternal");
// You must do this:
        out.writeObject(s);
        out.writeInt(i);
        out.writeObject(c);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        // You must do this:
        s = (String) in.readObject();
        i = in.readInt();
        c = (String) in.readObject();
    }

    public static void main(String[] args) {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", "yyyyyyy", 47);
        System.out.println(b3);
        try (
                ObjectOutputStream o = new ObjectOutputStream(
                        new FileOutputStream("Blip3.serialized"))
        ) {
            System.out.println("Saving object:");
            o.writeObject(b3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
// Now get it back:
        System.out.println("Recovering b3:");
        try (
                ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream("Blip3.serialized"))
        ) {
            b3 = (Blip3) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(b3);
    }
}

