package com.lqw.thread;


public class TwoThreadExe {

    public static void main(String[] args) {
        MyRunnable2 myRunnable = new MyRunnable2();
        Thread a = new Thread(myRunnable);

        Thread b = new Thread(myRunnable);


        a.start();
        b.start();
    }

    public static class MyRunnable2 implements Runnable {
        private static int i = 1;

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            synchronized (this) {
                while (i <= 100) {
                    notify();
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class MyRunnable implements Runnable {
        private int number = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    notify();
                    if (number <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        try {
                            wait(); //wait()阻塞并释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static class OverloadingVarargs2 {
        static void f(float i, Character... args) {
            System.out.println("first");
        }

        static void f(Character... args) {
            System.out.println("second");
        }

        static void f() {
            System.out.println("second");
        }

        public static void main(String[] args) {
//            f(10000, 'a');
//            f('a','b');
        }
    }
}
