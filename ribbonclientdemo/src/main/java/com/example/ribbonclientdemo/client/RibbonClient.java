package com.example.ribbonclientdemo.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

public class RibbonClient {
    public static void main(String[] args) throws Exception {
        // 1.设置要请求的服务器(别名,命名空间,属性名称)
        // ConfigurationManager.loadPropertiesFromResources("application.properties");
        ConfigurationManager.getConfigInstance().setProperty(
                "sample-client.ribbon.listOfServers", "localhost:8081,localhost:8082,localhost:8083");

        // 2.设置REST请求客户端
        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");

        // 3.创建请求实例
        HttpRequest request = HttpRequest.newBuilder().uri("/user/users").build();

        // 4.连续发送20次请求到服务器
        for (int i=0;i<20;i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String content = response.getEntity(String.class);
            System.out.println(content);
        }
    }
}
