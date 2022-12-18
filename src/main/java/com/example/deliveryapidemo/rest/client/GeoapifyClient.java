package com.example.deliveryapidemo.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class GeoapifyClient extends BaseApiClient {

    @Autowired
    public GeoapifyClient(WebClient webClient){
        super(webClient);
    }

    @Value("${geoapify.base-url}")
    private String apiBaseUrl;

    @Value("${geoapify.token}")
    private String token;


    //TODO refactor to use uriBuilder and change params
    public String buildUri(String path,String param1,String value1){
        return apiBaseUrl + path + "?" +param1 + "=" + value1 + "&lang=en&limit=5&format=json&apiKey=" +token;
    }

}
