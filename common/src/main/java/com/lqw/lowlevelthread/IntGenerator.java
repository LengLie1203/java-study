package com.lqw.lowlevelthread;

/**
 * @author LQW
 * @date 2021-04-12 09:11
 **/
// lowlevel/IntGenerator.java

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class IntGenerator {
    private AtomicBoolean canceled =
            new AtomicBoolean();

    public abstract int next();

    public void cancel() {
        canceled.set(true);
    }

    public boolean isCanceled() {
        return canceled.get();
    }
}

