package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class GetTargets {

    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    /**
     * @describe 获取目标数据的集合
     * @param
     * @return java.lang.String
     */
    public static String getTargets() throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接时间，超时时间
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        JSONObject params = new JSONObject();
        Auth.signParam(params, APP_KEY, APP_SECRET);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(HOST+"/targets/infos?"+ Common.toParam(params))
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
    public static void main(String[] args) throws Exception{
        String targets = getTargets();
        System.out.println(targets);
    }

}
