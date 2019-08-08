package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class GradeTracking {

//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    /**
     * @describe 追踪？
     * @param image 图片地址
     * @return java.lang.String
     */
    public static String gradeTracking(String image)throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        JSONObject params = new JSONObject();
        params.put("image", Base64.getEncoder().encodeToString(
                Files.readAllBytes(Paths.get("test_target_image.jpg"))));

        Auth.signParam(params, APP_KEY, APP_SECRET);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , params.toString());
        Request request = new Request.Builder()
                .url(HOST+"/grade/tracking")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception {
        String s = gradeTracking("test_target_image.jpg");
        System.out.println(s);
    }
}
