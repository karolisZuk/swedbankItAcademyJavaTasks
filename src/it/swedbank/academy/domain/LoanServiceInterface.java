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

    //3
    Collection<Loan> calculateLowRiskHarvesterLoans();

    Collection<Loan> calculateExpiredLandLoansInReservation();

    Collection<Loan> calculateLoansOfHigherThanAverageDepreciation();

}
