package com.lqw;

import java.util.concurrent.Callable;

public class TimingCallable extends TimingRunnable implements Callable<String> {
    public TimingCallable(int i) {
        super(i);
    }

    @Override
    public String call() throws Exception {
       super.run();
        return i+"success";
    }
}
