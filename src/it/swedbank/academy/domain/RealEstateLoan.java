package it.swedbank.academy.domain;

public class RealEstateLoan extends Loan {

    private int area;
    private String district;
    private RealEstatePurpose purpose;

    public void setArea(int area) {
        this.area = area;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPurpose(RealEstatePurpose purpose) {
        this.purpose = purpose;
    }

    public RealEstatePurpose getPurpose() {
        return purpose;
    }

    public String getDistrict() {
        return district;
    }

    public int getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealEstateLoan)) return false;
        if (!super.equals(o)) return false;

        RealEstateLoan that = (RealEstateLoan) o;

        if (area != that.area) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        return purpose == that.purpose;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + area;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
        return result;
    }
}
