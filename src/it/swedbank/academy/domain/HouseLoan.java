package it.swedbank.academy.domain;

import java.util.Date;

public class HouseLoan extends RealEstateLoan {

    private Date constructionDate;
    private int floorCount;

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }
}
