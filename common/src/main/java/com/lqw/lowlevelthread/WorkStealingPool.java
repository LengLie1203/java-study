package com.lqw.lowlevelthread;

/**
 * 工作窃取算法允许已经耗尽输入队列中的工作项的线程从其他队列“窃取”工作项。
 * 目标是在处理器之间分配工作项，从而最大限度地利用所有可用的处理器来完成计算密集型任务
 * @author LQW
 * @date 2021-04-10 16:23
 **/
// lowlevel/WorkStealingPool.java
import java.util.stream.*;
import java.util.concurrent.*;

class ShowThread implements Runnable {
    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName());
    }
}

public class WorkStealingPool {
    public static void main(String[] args)
            throws InterruptedException {
        System.out.println(
                Runtime.getRuntime().availableProcessors());
        ExecutorService exec =
                Executors.newWorkStealingPool();
        IntStream.range(0, 10)
                .mapToObj(n -> new ShowThread())
                .forEach(exec::execute);
        exec.awaitTermination(1, TimeUnit.SECONDS);
    }
}
/* Output:
8
ForkJoinPool-1-worker-2
ForkJoinPool-1-worker-1
ForkJoinPool-1-worker-2
ForkJoinPool-1-worker-3
ForkJoinPool-1-worker-2
ForkJoinPool-1-worker-1
ForkJoinPool-1-worker-3
ForkJoinPool-1-worker-1
ForkJoinPool-1-worker-4
ForkJoinPool-1-worker-2
*/

