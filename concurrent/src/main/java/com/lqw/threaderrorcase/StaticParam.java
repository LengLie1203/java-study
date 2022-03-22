package com.lqw.threaderrorcase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 *
 *
 * 生产环境遇到的问题，HttpClientUtil 创建时用到了全局变量apiURL，
 * 会导致某个线程创建的httpclient 被其他线程篡改
 *
 *
 *
 *
 * public HttpClientUtil(String url) {
 * 		if (url != null) {
 * 			this.apiURL = url;
 *                }
 * 		if (apiURL != null) {
 * 			httpClient = HttpClients.createDefault();
 * 			method = new HttpPost(apiURL);
 * 			logger.info("HttpClient URL:" + apiURL);
 *        }* 	}
 */
public class StaticParam {

    // 接口地址
    private static String apiURL = "";


    private String fUrl;

    public StaticParam(String url) {
        if (url != null) {
            this.apiURL = url;
        }
        if (apiURL != null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fUrl = apiURL;
            System.out.println("HttpClient URL: want:" + url + ", real:" + apiURL);
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                new StaticParam("URL" + finalI);

            });
        }

        executorService.shutdown();
    }
}


