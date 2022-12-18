package com.example.deliveryapidemo.service;

import com.example.deliveryapidemo.model.BookDeliveryRequest;
import com.example.deliveryapidemo.model.Delivery;
import reactor.core.publisher.Mono;

public interface DeliveryService {

    Mono<Delivery> createDelivery(BookDeliveryRequest request);
    Mono<Delivery> getDelivery(String deliveryId);
    Mono<Delivery> updateDelivery(Delivery delivery);
}
