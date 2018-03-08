package it.swedbank.academy.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public interface LoanServiceInterface {

    //1
    int calculateHighRiskLoans();

    BigDecimal calculateAverageLoanCost();

    BigDecimal calculateAverageCostOfHighRiskLoans();

    BigDecimal calculateAverageLoanCostByRiskType(LoanRiskType type);

    BigDecimal calculateMaximumPriceOfNonExpiredLoans();
    //2
    ArrayList calculateNormalRiskVehicleLoans();

    int calculateMaximumAgeOfLowRiskLoanedVehicles();
    Collection<Integer> getExpiredHighRiskVehicleLoansOfHighestDuration();
    //3

    Collection<Loan> getLowRiskHarvesterLoans();

    Collection<Loan> getExpiredLandLoansInReservation();

    Collection<Loan> getLoansOfHigherThanAverageDepreciation();

    void calculateLowRiskHarvesterLoans();

    void calculateExpiredLandLoansInReservation();

    void calculateLoansOfHigherThanAverageDepreciation();

}
