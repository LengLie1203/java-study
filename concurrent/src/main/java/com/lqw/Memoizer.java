package com.lqw;

import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 简单的缓存功能
 *
 * @author LQW
 * @date 2021-06-09 15:40
 **/
public class Memoizer<K, V> implements Computable<K, V> {

    private final Computable<K, V> computable;

    private final Map<K, FutureTask<V>> cache = new ConcurrentHashMap<>();

    public Memoizer(Computable<K, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(K key) throws InterruptedException {
        while (true) {
            FutureTask<V> f = cache.get(key);

            if (f == null) {
                FutureTask<V> future = new FutureTask<>(() -> computable.compute(key));

                //不存在时put数据，有数据时返回已有结果
                f = cache.putIfAbsent(key, future);
                if (f == null) {
                    f = future;
                    future.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(key, f);
            } catch (ExecutionException e) {
//            e.printStackTrace();
                throw new NullPointerException();
            }
        }
    }
}
