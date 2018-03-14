package it.swedbank.academy.service;

import it.swedbank.academy.domain.*;
import it.swedbank.academy.util.DateUtil;
import it.swedbank.academy.util.LoanUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private LoanIterable loans;

    public LoanService(LoanIterable loan) {
        this.loans = loan;
    }

    //1
    public int calculateHighRiskLoans() {
        int count = 0;
        for (Loan loan : this.loans) {
            if (loan.getLoanRiskType() == LoanRiskType.HIGH_RISK) {
                count++;
            }
        }
        return count;
    }

    public BigDecimal calculateAverageLoanCost() {
        BigDecimal sum = BigDecimal.ZERO;
        //You already know what the "count" is (hint: count = loans.size()), so you don't need to calculate it.
        int count = 0;
        for (Loan loan : this.loans) {
            sum = sum.add(loan.getTotalLoanCost());
            count++;
        }
        return sum.divide(new BigDecimal(count), 3, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateAverageCostOfHighRiskLoans() {
        //Use BigDecimal.ZERO here
        BigDecimal sum = new BigDecimal(0);
        int counter = 0;
        for (Loan loan : this.loans) {
            if (loan.getLoanRiskType() == LoanRiskType.HIGH_RISK) {
                sum = sum.add(loan.getTotalLoanCost());
                counter++;
            }
        }
        return sum.divide(new BigDecimal(counter));
    }

    public BigDecimal calculateAverageLoanCostByRiskType(LoanRiskType riskType) {
        //Use BigDecimal.ZERO here
        BigDecimal averageCost = new BigDecimal(0);
        int counter = 0;
        for (Loan loan : this.loans) {
            if (loan.getLoanRiskType() == riskType) {
                averageCost = averageCost.add(loan.getTotalLoanCost());
                counter++;
            }
        }
        return averageCost.divide(new BigDecimal(counter));
    }

    public BigDecimal calculateMaximumPriceOfNonExpiredLoans() {
        //Since Java 1.7 - "...ArrayList<BigDecimal>" -> "...ArrayList<>"
        List<BigDecimal> notExpiredList = new ArrayList<BigDecimal>();
        for (Loan loan : this.loans) {
            //I think this check should be implemented in "Loan.isValid()"
            if (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).before(new Date())) {
                notExpiredList.add(loan.getTotalLoanCost());
            }
        }
        //Good solution! Nice job :)
        return Collections.max(notExpiredList);
    }

    /*
    public Map<LoanRiskType,Collection<Loan>>  groupLoansByRiskType(){
        Map<LoanRiskType,Collection<Loan>> loansByRiskType = new TreeMap<>();
        for(Loan loan:loans){
            if (!loansByRiskType.containsKey(loan.getLoanRiskType())) {
                loansByRiskType.put(loan.getLoanRiskType(), new ArrayList<Loan>());
                //You can optimize this "for" block by moving line below outside "if" statement and removing "else" block.
                loansByRiskType.get(loan.getLoanRiskType()).add(loan);
            } else {
                loansByRiskType.get(loan.getLoanRiskType()).add(loan);
            }
        }
        return loansByRiskType;
    }
    */
    //Never return concrete collection types (use "Collection<...>"/"List<...>" instead)
    public ArrayList<Loan> calculateNormalRiskVehicleLoans() {
        //Use "Collection<...>"/"List<...>" instead of concrete type.
        ArrayList<Loan> normalRiskVehicleLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan && loan.getLoanRiskType() == LoanRiskType.NORMAL_RISK) {
                normalRiskVehicleLoans.add(loan);
            }
        }
        return normalRiskVehicleLoans;
    }

    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        //Use "Collection<...>"/"List<...>" instead of concrete type.
        ArrayList<Integer> lowRiskLoanedVehicles = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan && loan.getLoanRiskType() == LoanRiskType.LOW_RISK) {
                lowRiskLoanedVehicles.add(((VehicleLoan) loan).getMaximumAge());
            }
        }
        //External brackets are not needed here
        return (Collections.max(lowRiskLoanedVehicles));
    }

    //This is what I mean do the same for "calculateNormalRiskVehicleLoans"
    public Collection<Loan> calculatePersonalRealEstateLoans() {
        Collection<Loan> personalRealEstateLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan && ((RealEstateLoan) loan).getPurpose() == RealEstatePurpose.PERSONAL) {
                personalRealEstateLoans.add(loan);
            }
        }
        return personalRealEstateLoans;
    }

    public Collection<Integer> calculateExpiredHighRiskVehicleLoansOfHighestDuration() {
        Collection<Integer> expiredHighRiskVehicleLoansOfHighestDuration = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan && loan.getLoanRiskType() == LoanRiskType.HIGH_RISK) {
                //I think this check should be implemented in "Loan.isValid()"
                if (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).before(new Date())) {
                    expiredHighRiskVehicleLoansOfHighestDuration.add(((VehicleLoan) loan).getMaximumAge());
                }
            }
        }
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    //This is not finished yet, I guess
    public Collection<Loan> getPersonalRealEstateLoans() {
        Collection<Loan> personalRealEstateLoans = new ArrayList<>();
        return personalRealEstateLoans;
    }

    @Override
    public Collection<Loan> calculateLowRiskHarvesterLoans() {
        Collection<Loan> lowRiskHarvesterLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getLoanRiskType() == LoanRiskType.LOW_RISK && loan instanceof HarvesterLoan) {
                lowRiskHarvesterLoans.add(loan);
            }
        }
        return lowRiskHarvesterLoans;
    }

    @Override
    public Collection<Loan> calculateExpiredLandLoansInReservation() {
        //Use "Collection<...>"/"List<...>" instead of concrete type.
        ArrayList<Loan> expiredLandLoansInReservation = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof LandLoan && ((LandLoan) loan).getInReservation()) {
                //I think this check should be implemented in "Loan.isValid()"
                if (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date())) {
                    expiredLandLoansInReservation.add(loan);
                }
            }
        }
        return expiredLandLoansInReservation;
    }

    @Override
    public Collection<Loan> calculateLoansOfHigherThanAverageDepreciation() {
        //Use "Collection<...>"/"List<...>" instead of concrete type.
        ArrayList<Loan> loansOfHigherThanAverageDepreciation = new ArrayList<>();
        //Initialization of "averageVehicleDepreciation" is not needed. Do this instead:
        //BigDecimal averageVehicleDepreciation = calculateAverageDepreciation();
        BigDecimal averageVehicleDepreciation = new BigDecimal(0);
        averageVehicleDepreciation = calculateAverageDepreciation();
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                BigDecimal loanDeprecation = LoanUtil.calculateVehicleDepreciation(loan.getPrice(), ((VehicleLoan) loan).getManufactured(), ((VehicleLoan) loan).getMaximumAge());
                if (loanDeprecation.compareTo(averageVehicleDepreciation) > 0) {
                    loansOfHigherThanAverageDepreciation.add(loan);
                }
            }
        }
        return loansOfHigherThanAverageDepreciation;
    }

    public BigDecimal calculateAverageDepreciation() {
        BigDecimal averageVehicleDepreciation = new BigDecimal(0);
        int counter = 0;
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                averageVehicleDepreciation = averageVehicleDepreciation.add(
                        LoanUtil.calculateVehicleDepreciation(loan.getPrice(),
                                ((VehicleLoan) loan).getManufactured(),
                                ((VehicleLoan) loan).getMaximumAge()));
                counter++;
            }
        }
        return averageVehicleDepreciation.divide(new BigDecimal(counter), 2, RoundingMode.HALF_UP);
    }

}

