package com.example.ribbonclientdemo;

import com.example.ribbonclientdemo.rule.MyRule;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.HttpClientRequest;
import com.netflix.niws.client.http.RestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RibbonclientdemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RibbonclientdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // 初始化负载均衡器
        BaseLoadBalancer lb = new BaseLoadBalancer();

        // 设置负载均衡器规则
        MyRule rule = new MyRule();
        rule.setLoadBalancer(lb);
        lb.setRule(rule);

        // 服务列表
        List<Server> servers = new ArrayList<Server>();
        servers.add(new Server("localhost",8081));
        servers.add(new Server("localhost",8082));
        servers.add(new Server("localhost",8083));
        lb.addServers(servers);

        // 访问服务
        for (int i = 0; i < 20; i ++) {
            Server s = lb.chooseServer(null);
            System.out.println(s);
        }

//        // 1、设置请求的服务器
//        // 名称，命名空间，属性
//        ConfigurationManager.getConfigInstance().setProperty(
//                "sample-client.ribbon.listOfServers", "localhost:8081,localhost:8082, localhost:8083");
//
//        // 3、获取 REST 请求客户端
//        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");
//
//        // 4、创建请求实例
//        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/user/users")).build();
//
//        // 5、发 送 20 次请求到服务器中
//        for (int i = 0; i < 20; i ++) {
//            HttpResponse response = client.executeWithLoadBalancer(request); // 4
//            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus() + "  :" + response.getEntity(String.class));
//        }
    }
}
