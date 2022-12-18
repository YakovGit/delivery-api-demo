package com.example.deliveryapidemo.controller;


import com.example.deliveryapidemo.model.*;
import com.example.deliveryapidemo.service.TimelotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TimeslotsController {

    private TimelotsService timelotsService;

    @Autowired
    public TimeslotsController(TimelotsService timelotsService){
        this.timelotsService = timelotsService;
    }

    @PostMapping("/resolve-address")
    public Mono<Address> resolveAddress(@RequestBody ResolveAddressRequest request){
        return timelotsService.formatAddress(request.getSearchTerm());
    }

    @PostMapping("timeslots")
    public Flux<Timeslot> findAvailableTimeslots(@RequestBody AvailableTimeslotsRequest request){
        return timelotsService.findAvailableTimeslots(request.getAddress()).flatMapIterable(ts ->ts);
    }

}
