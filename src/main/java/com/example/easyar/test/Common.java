package com.example.easyar.test;


import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public class Common {
    /**
     * @describe 拼接参数
     * @param jso 拼接参数的数据
     * @return java.lang.String
     */
    public static String  toParam(JSONObject jso) {
        Set<String>  keys = jso.keySet();
        StringBuffer sb   = new StringBuffer();
        for (String key : keys){
            sb.append(key);
            sb.append("=");
            sb.append(jso.getString(key));
            sb.append("&");
        }
        return sb.substring(0,sb.length()-1);
    }
}
