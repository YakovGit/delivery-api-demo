package com.example.deliveryapidemo.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class HolidayapiClient extends BaseApiClient {

    @Autowired
    public HolidayapiClient(WebClient webClient){
        super(webClient);
    }

    @Value("${holidayapi.base-url}")
    private String apiBaseUrl;

    @Value("${holidayapi.token}")
    private String token;


    //TODO refactor to use uriBuilder and change params
    public String buildUri(String path,String param1,String value1){
        return apiBaseUrl + path + "?pretty&" +param1 + "=" + value1 + "&year=2021&key=" +token;
    }
}
