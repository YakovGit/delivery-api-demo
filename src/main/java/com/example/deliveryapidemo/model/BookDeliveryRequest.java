package com.example.deliveryapidemo.model;

public class BookDeliveryRequest {
    private String user;
    private Integer timeslotId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }
}
