package com.lqw;

public class TimingRunnable implements Runnable{

    protected int i;

    public TimingRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run()  {
        for (int j = 0; j < i; j++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }finally {

                Thread.currentThread().interrupt();
            }
        }
    }
}
