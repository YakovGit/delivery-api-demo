package com.example.deliveryapidemo.service;

import com.example.deliveryapidemo.model.Address;
import com.example.deliveryapidemo.model.Timeslot;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TimelotsService {

    Mono<Address> formatAddress(String unformattedAddress);
    Mono<List<Timeslot>> findAvailableTimeslots(Address address);
    List<Timeslot> getFileAvailableTimeslots();
}
