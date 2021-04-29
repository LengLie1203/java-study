package com.lqw.lambda;


import java.util.function.IntSupplier;

public class Closure3 {
    public static  int INT=0;
    private int j;
    IntSupplier makeFun(int x) {
        int i = 0;
        // x++ 和 i++ 都会报错：
        return () -> x + j++ + i + INT;
    }

    IntSupplier makeFun2(int x) {
        int i = 0;
//        i++;
//        x++;
        return () -> x + i;
    }

    IntSupplier makeFun3(int x) {
        int i = 0;
        // 同样规则的应用:
//         i++; // 非等同 final 效果
//         x++; // 同上
        return new IntSupplier() {
            public int getAsInt() { return x + i; }
        };
    }

    public static void main(String[] args) {
        Closure3 closure3 = new Closure3();
        closure3.makeFun(0);
        closure3.makeFun2(2);
        IntSupplier intSupplier = closure3.makeFun3(2);

        System.out.println(intSupplier.getAsInt());
    }
}
