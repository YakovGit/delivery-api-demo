package com.example.deliveryapidemo.service;

import com.example.deliveryapidemo.model.*;
import com.example.deliveryapidemo.rest.client.GeoapifyClient;
import com.example.deliveryapidemo.rest.client.HolidayapiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TimelotsServiceImpl implements TimelotsService {

    //TODO create services and interfaces for:
    private GeoapifyClient geoapifyClient;
    private HolidayapiClient holidayapiClient;


    @Autowired
    public TimelotsServiceImpl(GeoapifyClient geoapifyClient, HolidayapiClient holidayapiClient) {
        this.geoapifyClient = geoapifyClient;
        this.holidayapiClient = holidayapiClient;
    }

    //TODO handle exceptions
    public Mono<Address> formatAddress(String unformattedAddress) {
        String url = geoapifyClient.buildUri("/v1/geocode/search", "text", unformattedAddress);
        return geoapifyClient.getWebClient().get()
                .uri(url).retrieve()
                .bodyToMono(GeoapifySearchResponse.class)
                .map(GeoapifySearchResponse::getResults)
                .flatMapMany(Flux::fromIterable)
                .elementAt(0);
    }

    public Mono<List<Timeslot>> findAvailableTimeslots(Address address) {
        Mono<List<Holiday>> holidays = getHolidays(address.getCountryCode());
        Mono<List<Timeslot>> fileAvailableTimeslots = Mono.fromCallable(this::getFileAvailableTimeslots);

        return holidays.zipWith(fileAvailableTimeslots).map(tuple -> {
            List<Date> holidayDates = tuple.getT1().stream().map(Holiday::getDate).collect(Collectors.toList());
            return tuple.getT2().stream().filter(timeslot -> timeslot.getSupportedAddresses().contains(address.getLine1()))
                    .filter(timeslot -> holidayDates.contains(timeslot.getStartDate())).collect(Collectors.toList());
        });

    }

    public List<Timeslot> getFileAvailableTimeslots() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Timeslot> availableTimeslots;
        try {
            availableTimeslots = objectMapper.readValue(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("internal.data/availableTimeslots.json")).getFile(),
                    new TypeReference<List<Timeslot>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return availableTimeslots;
    }

    private Mono<List<Holiday>> getHolidays(String countryCode) {
        String url = holidayapiClient.buildUri("/v1/holidays","country",countryCode);
        return holidayapiClient.getWebClient().get()
                .uri(url).retrieve()
                .bodyToMono(HolidayapiHolidaysResponse.class)
                .map(HolidayapiHolidaysResponse::getHolidays);
    }
}
