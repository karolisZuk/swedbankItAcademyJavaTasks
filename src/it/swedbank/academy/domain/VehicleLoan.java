package it.swedbank.academy.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleLoan extends Loan {

    private Date manufactured;
    private int maximumAge;
    private String model;
    private Date manufacturedDate;
    private BigDecimal interestRate;

    public void VehicleLoan(){

    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public void setModel(String model) {
        this.model = model;
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

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleLoan)) return false;
        if (!super.equals(o)) return false;

        VehicleLoan that = (VehicleLoan) o;

        if (maximumAge != that.maximumAge) return false;
        if (manufactured != null ? !manufactured.equals(that.manufactured) : that.manufactured != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return manufacturedDate != null ? manufacturedDate.equals(that.manufacturedDate) : that.manufacturedDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (manufactured != null ? manufactured.hashCode() : 0);
        result = 31 * result + maximumAge;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (manufacturedDate != null ? manufacturedDate.hashCode() : 0);
        return result;
    }

}
