package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i=1;i<3;i++){
            int s=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                        for (int j = 0; j < 5; j++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(s);
                        }
                    throw new RuntimeException();
                }
            });
        }

        executorService.shutdown();
    }
}
