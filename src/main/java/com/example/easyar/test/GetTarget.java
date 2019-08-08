package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class GetTarget {

    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    private static final String TARGET_ID  = "0f69b3c2-3dd6-4d72-b12c-2cac43b7ca30";

    /**
     * @describe 获取单个数据的信息
     * @param targetId 目标id
     * @return java.lang.String
     */
    public static String getTarget(String targetId)throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        JSONObject params = new JSONObject();
        Auth.signParam(params, APP_KEY, APP_SECRET);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(HOST+"/target/"+targetId+"?"+ Common.toParam(params))
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception{
        String targetId  = "b52a7805-2c76-483a-901e-a024666c88bc";
        String target = getTarget(targetId);
        System.out.println(target);
    }

}
