package it.swedbank.academy.domain;

public class CarLoan extends VehicleLoan implements Comparable<CarLoan> {


    private float enginePower;


    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public int compareTo(CarLoan o) {
        //This is a good approach from code structure point of view (nesting "if" statements I mean), the only drawback to this,
        // is you need to compare everything twice and this could be a hit on performance, so keep that in mind.
        //You can remove "this" keywords, they are not needed here. Here is a good explanation, when "this" should be used -
        // https://stackoverflow.com/questions/2411270/when-should-i-use-this-in-a-class
        if (this.getPrice().compareTo(o.getPrice()) == 0) {
            if (this.enginePower == o.enginePower) {
                return this.getInterestRate().compareTo(o.getInterestRate());
                //External brackets (...) are not needed here
            } else return (-Float.compare(this.enginePower, o.enginePower));
        }
        //External brackets (...) are not needed here
        return (-this.getPrice().compareTo(o.getPrice()));
    }


}
