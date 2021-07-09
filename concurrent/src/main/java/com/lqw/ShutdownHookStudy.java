package com.lqw;

public class ShutdownHookStudy {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(null);



    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
