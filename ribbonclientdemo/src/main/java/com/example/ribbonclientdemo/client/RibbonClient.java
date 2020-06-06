package com.example.ribbonclientdemo.client;

import com.netflix.config.ConfigurationManager;

public class RibbonClient {
    public static void main(String[] args) {
        ConfigurationManager.getConfigInstance().setProperty(
                "sample-client.ribbon.listOfServers", "www.linkedin.com:80,www.google.com:80");

    }
}
