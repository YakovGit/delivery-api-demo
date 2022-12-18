package com.example.deliveryapidemo.rest.client;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class BaseApiClient {

    protected WebClient webClient;

    public BaseApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public abstract String buildUri(String path,String param1,String value1);

}
