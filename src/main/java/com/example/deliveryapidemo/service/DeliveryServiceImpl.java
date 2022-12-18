package com.example.deliveryapidemo.service;

import com.example.deliveryapidemo.enums.DeliveryStatus;
import com.example.deliveryapidemo.model.BookDeliveryRequest;
import com.example.deliveryapidemo.model.Delivery;
import com.example.deliveryapidemo.persistence.DeliveryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    //TODO create services and interfaces for:
    private TimelotsServiceImpl timelotsService;
    private DeliveryMongoRepository deliveryMongoRepository;


    @Autowired
    public DeliveryServiceImpl(TimelotsServiceImpl timelotsService, DeliveryMongoRepository deliveryMongoRepository) {
        this.timelotsService = timelotsService;
        this.deliveryMongoRepository = deliveryMongoRepository;
    }

    public Mono<Delivery> createDelivery(BookDeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.PENDING);
        //TODO validate timeslot is not holiday
        //TODO validate max daily capacity hasn't reached -ConcurrentHashMap?
        timelotsService.getFileAvailableTimeslots().stream().filter(timeslot -> request.getTimeslotId().equals(timeslot.getId())).findFirst()
                .ifPresent(delivery::setSelectedTimeslot);
        return deliveryMongoRepository.save(delivery);
    }

    public Mono<Delivery> getDelivery(String deliveryId) {
        return deliveryMongoRepository.findById(deliveryId);
    }

    public Mono<Delivery> updateDelivery(Delivery delivery) {
        return deliveryMongoRepository.save(delivery);
    }
}
