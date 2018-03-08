package it.swedbank.academy.domain;

public class LandLoan extends RealEstateLoan {

    private boolean inReservation;

    public void setInReservation(boolean inReservation) {
        this.inReservation = inReservation;
    }

    public boolean getInReservation() {
        return inReservation;
    }
}
