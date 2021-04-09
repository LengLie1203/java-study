package com.lqw.concurrent;

import com.lqw.Timer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author LQW
 * @date 2021-04-09 09:25
 **/
public class CompletablePizza {
    static final int QUANTITY = 5;

    public static CompletableFuture<Pizza> make(Pizza pizza){
        CompletableFuture<Pizza> future = CompletableFuture.completedFuture(pizza)
                .thenApplyAsync(Pizza::roll)
                .thenApplyAsync(Pizza::sauce)
                .thenApplyAsync(Pizza::cheese)
                .thenApplyAsync(Pizza::toppings)
                .thenApplyAsync(Pizza::bake)
                .thenApplyAsync(Pizza::slice)
                .thenApplyAsync(Pizza::box);
        return future;
    }

    public static void show(CompletableFuture<Pizza> future){
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<CompletableFuture<Pizza>> futures = IntStream.range(0, QUANTITY).mapToObj(Pizza::new)
                .map(CompletablePizza::make).collect(Collectors.toList());
        Timer timer=new Timer();
        futures.forEach(CompletablePizza::show);

        System.out.println(timer.duration());

        System.out.println("***********************");
        //这个写法里面stream是单线程的，所以直接终端操作相当于串行执行每一个任务，show方法中的get会阻塞，导致实际效果是单线程
        //如果想并行则对stream使用parallel()。或者像上面一样生成list，在执行
        System.out.println(Timer.duration(() -> IntStream.range(0, QUANTITY).mapToObj(Pizza::new)
//                .parallel()
                .map(CompletablePizza::make).forEach(CompletablePizza::show)));
    }
}
