package com.example.deliveryapidemo.controller;


import com.example.deliveryapidemo.enums.DeliveryStatus;
import com.example.deliveryapidemo.model.BookDeliveryRequest;
import com.example.deliveryapidemo.model.Delivery;
import com.example.deliveryapidemo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class DeliveryController {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }


    @PostMapping("/deliveries")
    public Mono<Delivery> bookDelivery(@RequestBody BookDeliveryRequest request){
        return deliveryService.createDelivery(request);
    }

    @PostMapping("/deliveries/{deliveryId}/complete")
    public Mono<Delivery> completeDelivery(@PathVariable("deliveryId") String deliveryId){
        Mono<Delivery> mono = deliveryService.getDelivery(deliveryId);
        return mono.map(delivery -> {
            delivery.setStatus(DeliveryStatus.COMPLETED);
            return delivery;
        }).flatMap(deliveryService::updateDelivery);
    }

    @DeleteMapping ("/deliveries/{deliveryId}")
    public Mono<Delivery> cancelDelivery(@PathVariable("deliveryId") String deliveryId){
        Mono<Delivery> mono = deliveryService.getDelivery(deliveryId);
        return mono.map(delivery -> {
            delivery.setStatus(DeliveryStatus.CANCELED);
            return delivery;
        }).flatMap(deliveryService::updateDelivery);
    }
}
