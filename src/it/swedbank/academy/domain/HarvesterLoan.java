package it.swedbank.academy.domain;

import java.util.Date;

public class HarvesterLoan extends VehicleLoan {

    private Date manufactured;
    private int maximumAge;
    private String model;
    private int capacity;

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getManufactured() {
        return manufactured;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }
}
