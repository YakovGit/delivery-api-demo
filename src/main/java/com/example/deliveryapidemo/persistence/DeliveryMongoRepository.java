package com.example.deliveryapidemo.persistence;

import com.example.deliveryapidemo.model.Delivery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface DeliveryMongoRepository extends ReactiveMongoRepository<Delivery, String> {

}
