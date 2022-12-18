package com.example.deliveryapidemo.model;

import java.util.List;

public class GeoapifySearchResponse {

    private List<Address> results;

    public List<Address> getResults() {
        return results;
    }

    public void setResults(List<Address> results) {
        this.results = results;
    }
}
