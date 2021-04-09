package com.lqw.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author LQW
 * @date 2021-04-08 21:22
 **/
public class StickHolder {

    public static class ChopStick{
       private int no;

        public ChopStick(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "ChopStick" + no ;
        }
    }

    private ChopStick chopStick;

    private BlockingQueue<ChopStick> queue=new ArrayBlockingQueue<>(1);

    public StickHolder(int no) {
        chopStick=new ChopStick(no);
        putDown();
    }

    public ChopStick pickup()  {
        try {
           return queue.take();
        } catch (InterruptedException e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    public void putDown(){
        try {
            queue.put(chopStick);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
