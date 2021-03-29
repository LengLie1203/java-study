package com.lqw.init;

public class Init extends InitP{

    static {
        System.out.println("init1");
    }

    {
        System.out.println("init2");
    }

    public Init() {

        System.out.println("init3");
    }

    public void to() {
        System.out.println("init4");
    }

    public static void main(String[] args) {
        Init init;
        System.out.println("just reference");
        Init init1 = new Init();
        System.out.println("init  new");
        init1.to();
        Init init2;
        System.out.println("init not new");
        init2=new Init();
    }
}

