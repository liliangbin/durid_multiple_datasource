package com.example.oracle.rdbs;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import springfox.documentation.spring.web.json.Json;

import javax.crypto.spec.PSource;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RestApiConnection {

    public static void main(String[] args) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        String url = "http://forcebing.top:8084/book5";
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.addParameter("name", "hello");
        HttpGet httpget = new HttpGet(uriBuilder.build());
        httpget.setHeader("accept", "text/plain;charset=utf-8");
        httpget.setHeader("Content-Type", "application/json");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("连接成功");
        }
        if (entity != null) {
            InputStream instream = entity.getContent();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);

            // 通过起始的第一个字符为 [  或是 { 来调整使用的格式。
            List<JSONObject> jsonObject = JSONObject.parseArray(result, JSONObject.class);
            System.out.println(jsonObject.size());


            try {
                // do something useful
            } finally {
                instream.close();
            }
        }
    }


}
