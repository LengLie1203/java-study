package com.lqw.bean;

public class DataResponse<T> {

    private String responseCode;

    private String responseMessage;

    private T data;

    public DataResponse() {
    }

    public DataResponse(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static <T> DataResponse<T> success(T data){
        return new DataResponse<>("success","请求成功",data);
    }

    public static <T> DataResponse<T> failure(T data){
        return new DataResponse<>("failure","请求出错",data);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public T getData() {
        return data;
    }
}
