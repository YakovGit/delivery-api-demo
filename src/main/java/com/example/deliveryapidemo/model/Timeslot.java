package com.example.deliveryapidemo.model;

import java.util.Date;
import java.util.List;

public class Timeslot {

    private Integer id;
    private Date startDate;
    private Date endDate;
    private List<String> supportedAddresses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getSupportedAddresses() {
        return supportedAddresses;
    }

    public void setSupportedAddresses(List<String> supportedAddresses) {
        this.supportedAddresses = supportedAddresses;
    }
}
