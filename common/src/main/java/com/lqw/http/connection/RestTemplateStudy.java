package com.lqw.http.connection;

import com.lqw.bean.DataRequest;
import com.lqw.bean.DataResponse;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class RestTemplateStudy {

    private static RestTemplate restTemplate=new RestTemplate();


    public static void main(String[] args) {
        httpGet();

        httpPost();
    }

    private static void httpGet() {
        Object forObject = restTemplate.getForObject("http://localhost:8888/hello/sayHello", String.class, new HashMap<>());

        System.out.println(forObject);
    }

    private static void httpPost(){
        DataRequest<String> request=new DataRequest<>("LQW");
        DataResponse<String> dataResponse = restTemplate.postForObject("http://localhost:8888/hello/sayHelloToSome", request, DataResponse.class);

        System.out.println(dataResponse.getData());
    }
}
