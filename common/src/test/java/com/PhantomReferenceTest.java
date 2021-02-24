package com;

import java.lang.ref.*;

public class PhantomReferenceTest {

    public static void main(String[] args) {

        ReferenceQueue<String> referenceQueue=new ReferenceQueue<>();
        PhantomReference<String> prStr=new PhantomReference<>(new String("幻象引用"),referenceQueue);
        System.out.println(prStr.get());
        System.out.println(prStr.isEnqueued());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(prStr.isEnqueued());
    }
}
