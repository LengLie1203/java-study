package com.lqw.enums;

/**
 * @author LQW
 * @date 2021-03-29 17:56
 **/

public enum Explore implements Runnable{
    HERE("HERE", "here"){
        @Override
        String getString() {
            return "getString="+getKey();
        }

    },
    THERE("THERE", "there"){
        @Override
        String getString() {
            return "getString="+getKey();
        }

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
            super.run();

            System.out.println("HHHHHHH");
        }
    };

    private String key;

    private String value;

    Explore(String key, String value) {
        this.key = key;
        this.value = value;
    }

    abstract String getString();

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    public static void main(String[] args) {
        System.out.println(THERE.getString());

        THERE.run();
    }


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
        System.out.println("hhhhh");
    }
}
