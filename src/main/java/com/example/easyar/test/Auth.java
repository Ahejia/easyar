package com.example.easyar.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.stream.Collectors;

/**
 * 授权
 */
public class Auth {

    private static final String KEY_DATE = "timestamp";
    private static final String KEY_APP_KEY = "appKey";
    private static final String KEY_SIGNATURE = "signature";

    private static String generateSignature(JSONObject jso, String appSecret) {
        String paramStr = jso.keySet().stream()
                .sorted()
                .map(key -> key + String.valueOf(jso.get(key)))
                .collect(Collectors.joining());
        return DigestUtils.sha256Hex(paramStr + appSecret);
    }

    public static JSONObject signParam(JSONObject param, String appKey, String appSecret) {
        param.put(KEY_DATE, String.valueOf(System.currentTimeMillis()));
        param.put(KEY_APP_KEY, appKey);
        param.put(KEY_SIGNATURE, generateSignature(param, appSecret));
        return param;
    }

    public static void main(String[] args) {
//        final String appKey = "test_app_key";
//        final String appSecret = "test_app_secret";
        final String appKey = "5f42ee04e4ce5233ddbbef7b568e0ec9";
        final String appSecret = "KhYlO7nN810FWXtQVqWOOeNRQbBCeahrh6h1ipdLTPbdjPqGgcQOTQ5WUR2lzIs8jX3Y9ZhaEst9eG4G0Wl2r8abOKjRydd9Tpnm3uFZZnmqCZDLUZ3NFgPKSGVXY2Iw";

        JSONObject param = new JSONObject();
        param.put("name", "java-sdk-test");
        param.put("meta", "AR picture to display with base64 format");
        param.put(KEY_DATE, String.valueOf(System.currentTimeMillis()));
        param.put(KEY_APP_KEY, appKey);
        System.out.println(generateSignature(param, appSecret));

        signParam(param, appKey, appSecret);
        System.out.println(param);
    }

}
