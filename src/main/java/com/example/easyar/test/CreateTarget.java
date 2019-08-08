package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class CreateTarget {


//    private static final String HOST       = "http://your_uuid.cn1.crs.easyar.com:8888";
//    private static final String APP_KEY    = "--here is your crs image space's key--";
//    private static final String APP_SECRET = "--here is your crs image space's secret--";
    private static final String HOST       = "http://24f73f6ea40c0c5e17f9636d8887c522.cn1.crs.easyar.com:8888";
    private static final String APP_KEY    = "5f42ee04e4ce5233ddbbef7b568e0ec9";
    private static final String APP_SECRET = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

    /**
     * @describe 创建数据
     * @param type 上传数据的类型
     * @param name 名称
     * @param size 大小
     * @param image 图片地址
     * @param meta
     * @return java.lang.String 创建完成的数据信息
     */
    public static String createTarget(String type,String name,String size,String image,String meta)throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS);

        OkHttpClient client = builder.build();
        //json中包含image,type,name,size,meta
        JSONObject params = new JSONObject();
//        String image = params.getString("image");
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
                .url(HOST+"/targets")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception {
        String target = createTarget("ImageTarget","测试04","512","C:\\Users\\Administrator\\Desktop\\image\\59c9e6d957dc6.jpg","");
        //若存在相似图片，返回的statusCode:419  则表示上传失败
        System.out.println(target);
    }
}
