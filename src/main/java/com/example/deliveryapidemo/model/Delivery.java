package com.example.deliveryapidemo.model;

import com.example.deliveryapidemo.enums.DeliveryStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "delivery")
public class Delivery {

    @Id
    private String id;
    private DeliveryStatus status;
    private Timeslot selectedTimeslot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Timeslot getSelectedTimeslot() {
        return selectedTimeslot;
    }

    public void setSelectedTimeslot(Timeslot selectedTimeslot) {
        this.selectedTimeslot = selectedTimeslot;
    }
}
