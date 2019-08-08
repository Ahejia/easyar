package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class UpdateTarget {
//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    /**
     * @describe 修改目标数据
     * @param targetId 识别图id
     * @param type 类型
     * @param name 名称
     * @param size 大小
     * @param image 图片地址
     * @param meta
     * @return java.lang.String
     */
    public static String updateTarget(String targetId,String type,String name,String size,String image,String meta)throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);

        OkHttpClient client = builder.build();
        //json中包含image,type,name,size,meta
        JSONObject params = new JSONObject();
        params.put("type",type);
        params.put("name", name);
        params.put("size", size);
        params.put("image", Base64.getEncoder().encodeToString(
                Files.readAllBytes(Paths.get(image))));

//        params.put("meta", Base64.getEncoder().encodeToString(
//                //这是用于存储AR内容的自定义字段。e.x。: base64(2D图片)小于2MB或URL的3D模型对象文件
//                Files.readAllBytes(Paths.get("http://my.com/my-3d-model-example"))));
        Auth.signParam(params, APP_KEY, APP_SECRET);
        //设置编码格式
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , params.toString());
        Request request = new Request.Builder()
                .url(HOST+"/target/"+targetId)
                .put(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception {
        String imageTarget = updateTarget("41c97b0a-321e-4090-873c-0f54c45c5ebe", "ImageTarget", "java-sdk-test", "100", "C:\\Users\\Administrator\\Desktop\\image\\W020110904511988688441.jpg", "");
        System.out.println(imageTarget);
    }
}
