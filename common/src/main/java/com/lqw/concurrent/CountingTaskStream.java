package com.lqw.concurrent;

import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-02 09:40
 **/
public class CountingTaskStream {
    public static void main(String[] args) {
        Integer result = IntStream.range(0, 10).parallel().mapToObj(CountingTask::new)
                .map(CountingTask::call).reduce(0, Integer::sum);

        System.out.println(result);
    }
}
