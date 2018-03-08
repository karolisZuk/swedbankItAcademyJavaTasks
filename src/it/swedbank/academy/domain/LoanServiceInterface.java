package it.swedbank.academy.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {

    //1

    void calculateHighRiskLoans();

    void calculateAverageLoanCost();

    BigDecimal getAverageLoanCost();

    BigDecimal getAverageLoanCostByRiskType(LoanRiskType type);

    int getHighRiskLoans();

    BigDecimal getAverageCostOfHighRiskLoans();

    BigDecimal getMaximumPriceOfNonExpiredLoans();

    void calculateAverageLoanCostByRiskType();

    void calculateMaximumPriceOfNonExpiredLoans();

    Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType();

    //2

    Set<String> findVehicleModels();

    void calculateNormalRiskVehicleLoans();

    void calculateMaximumAgeOfLowRiskLoanedVehicles();

    void calculatePersonalRealEstateLoans();

    void calculateExpiredHighRiskVehicleLoansOfHighestDuration();

    Collection<Loan> getNormalRiskVehicleLoans();

    int getMaximumAgeOfLowRiskLoanedVehicles();

    Collection<Loan> getPersonalRealEstateLoans();

    Collection<Integer> getExpiredHighRiskVehicleLoansOfHighestDuration();

    //3

    Collection<Loan> getLowRiskHarvesterLoans();

    Collection<Loan> getExpiredLandLoansInReservation();

    Collection<Loan> getLoansOfHigherThanAverageDepreciation();

    void calculateLowRiskHarvesterLoans();

    void calculateExpiredLandLoansInReservation();

    void calculateLoansOfHigherThanAverageDepreciation();

}
