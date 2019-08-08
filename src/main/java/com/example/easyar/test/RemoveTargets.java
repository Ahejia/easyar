package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class RemoveTargets {

    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    /**
     * @describe 根据识别图编号删除多个数据
     * @param targetIds 识别图编号的集合，以","分隔
     * @return java.lang.String
     */
    public static String removeTargets(String targetIds)throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        JSONObject params = new JSONObject();

        params.put("targetId",targetIds);
        Auth.signParam(params, APP_KEY, APP_SECRET);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , params.toString());
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(HOST+"/targets")
                .delete(requestBody)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        return response.body().string();
    }


    public static void main(String[] args) throws Exception{
        String s = removeTargets("60bdf5ca-8e6d-4459-aca6-8036d3c9ce57,9920ba40-fa16-46c8-8cf4-2433cfae3694");
        System.out.println(s);
    }
}
