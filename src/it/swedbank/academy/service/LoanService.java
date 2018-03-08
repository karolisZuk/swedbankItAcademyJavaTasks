package it.swedbank.academy.service;

import it.swedbank.academy.domain.*;
import it.swedbank.academy.util.DateUtil;
import it.swedbank.academy.util.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private BigDecimal averageLoanCost;
    private Loan[] loans;
    private BigDecimal maximumPriceOfNonExpiredLoans;
    private BigDecimal highRiskAverage, normalRiskAverage, lowRiskAverage;
    private int highRiskLoanCount=0;
    private Collection<Loan> normalRiskVehicleLoans;
    private int maximumAgeOfLowRiskLoanedVehicles;
    private Collection<Loan> personalRealEstateLoans;
    private Collection<Integer> expiredHighRiskVehicleLoansOfHighestDuration;
    private Collection<Loan> lowRiskHarvesterLoans;
    private ArrayList<Loan> expiredLandLoansInReservation;
    private ArrayList<Loan> loansOfHigherThanAverageDepreciation;
    private BigDecimal averageVehicleDepreciation;

    //1

    public void calculateHighRiskLoans(){
        int count=0;
        for(Loan loan: this.loans){
            if(loan.getLoanRiskType()==LoanRiskType.HIGH_RISK){
                count++;
            }
        }
        highRiskLoanCount=count;
    }

    public void calculateAverageLoanCost(){
        BigDecimal sum = BigDecimal.ZERO;
        int count=0;
        for(Loan loan: this.loans){
            sum=sum.add(loan.getTotalLoanCost());
            count++;
        }
        averageLoanCost = sum.divide(new BigDecimal(count));
    }

    public LoanService(Loan[] loan){
        this.loans = loan;
    }

    public BigDecimal getAverageLoanCost(){
        return averageLoanCost;
    }

    public BigDecimal getAverageLoanCostByRiskType(LoanRiskType type){
        switch (type){
            case LOW_RISK: return lowRiskAverage;
            case NORMAL_RISK: return normalRiskAverage;
            case HIGH_RISK: return highRiskAverage;
        }
        return BigDecimal.ZERO;
    }

    public int getHighRiskLoans() {
        return highRiskLoanCount;
    }

    public BigDecimal getAverageCostOfHighRiskLoans(){
        return highRiskAverage;
    }

    public BigDecimal getMaximumPriceOfNonExpiredLoans(){
        return maximumPriceOfNonExpiredLoans;
    }

    public void calculateAverageLoanCostByRiskType() {
        BigDecimal lowRisk = BigDecimal.ZERO;
        int lowCounter=0;
        BigDecimal midRisk = BigDecimal.ZERO;
        int midCounter=0;
        BigDecimal highRisk = BigDecimal.ZERO;
        int highCounter=0;
        for(Loan loan: this.loans) {
            if (loan.getLoanRiskType() == LoanRiskType.LOW_RISK) {
                lowRisk = lowRisk.add(loan.getTotalLoanCost());
                lowCounter++;
            } else if (loan.getLoanRiskType() == LoanRiskType.NORMAL_RISK) {
                midRisk = midRisk.add(loan.getTotalLoanCost());
                midCounter++;
            } else if (loan.getLoanRiskType() == LoanRiskType.HIGH_RISK) {
                highRisk = highRisk.add(loan.getTotalLoanCost());
                highCounter++;
            }
        }
        this.lowRiskAverage = lowRisk.divide(new BigDecimal(lowCounter));
        this.normalRiskAverage = midRisk.divide(new BigDecimal(midCounter));
        this.highRiskAverage = highRisk.divide(new BigDecimal(highCounter));
    }

    public void calculateMaximumPriceOfNonExpiredLoans() {
        List<BigDecimal> notExpiredList = new ArrayList<BigDecimal>();

        for(Loan loan: this.loans) {
            if(DateUtil.addYears(loan.getCreationDate(),loan.getTermInYears()).before(new Date())){
                notExpiredList.add(loan.getTotalLoanCost());
            }
        }
        maximumPriceOfNonExpiredLoans=Collections.max(notExpiredList);
    }

    public Map<LoanRiskType,Collection<Loan>>  groupLoansByRiskType(){
        Map<LoanRiskType,Collection<Loan>> loansByRiskType = new TreeMap<>();
        for(Loan loan:loans){
            if (!loansByRiskType.containsKey(loan.getLoanRiskType())) {
                loansByRiskType.put(loan.getLoanRiskType(), new ArrayList<Loan>());
                loansByRiskType.get(loan.getLoanRiskType()).add(loan);
            } else {
                loansByRiskType.get(loan.getLoanRiskType()).add(loan);
            }
        }
        return loansByRiskType;
    }

    //2

    public Set<String> findVehicleModels(){
        Set<String> vehicleModels=new HashSet<String>();
        for (Loan loan : this.loans) {
            if (loan instanceof VehicleLoan) {
                vehicleModels.add(((VehicleLoan) loan).getModel());
            }
        }
        return vehicleModels;
    }

    public void calculateNormalRiskVehicleLoans(){
        normalRiskVehicleLoans = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.NORMAL_RISK && loan instanceof VehicleLoan){
                normalRiskVehicleLoans.add(loan);
            }
        }
    }

    public void calculateMaximumAgeOfLowRiskLoanedVehicles() {
        maximumAgeOfLowRiskLoanedVehicles=0;
        ArrayList<Integer> lowRiskLoanedVehicles = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.LOW_RISK && loan instanceof VehicleLoan){
                lowRiskLoanedVehicles.add(((VehicleLoan) loan).getMaximumAge());
            }
        }
        maximumAgeOfLowRiskLoanedVehicles=Collections.max(lowRiskLoanedVehicles);
    }

    public void calculatePersonalRealEstateLoans() {
        personalRealEstateLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (((RealEstateLoan) loan).getPurpose() == RealEstatePurpose.PERSONAL && loan instanceof RealEstateLoan) {
                personalRealEstateLoans.add(loan);
            }
        }
    }

    public void calculateExpiredHighRiskVehicleLoansOfHighestDuration() {
        expiredHighRiskVehicleLoansOfHighestDuration = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.HIGH_RISK && loan instanceof VehicleLoan){
                expiredHighRiskVehicleLoansOfHighestDuration.add(((VehicleLoan) loan).getMaximumAge());
                //if(DateUtil.addYears(loan.getCreationDate(),loan.getTermInYears()).after(new Date())) {
                //   expiredHighRiskVehicleLoansOfHighestDuration.add(((VehicleLoan) loan).getMaximumAge());
                //}
            }
        }
    }

    public Collection<Loan> getNormalRiskVehicleLoans() {
        return normalRiskVehicleLoans;
    }

    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        return maximumAgeOfLowRiskLoanedVehicles;
    }

    public Collection<Loan> getPersonalRealEstateLoans() {
        return personalRealEstateLoans;
    }

    public Collection<Integer> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    //3

    @Override
    public Collection<Loan> getLowRiskHarvesterLoans() {
        return lowRiskHarvesterLoans;
    }

    @Override
    public Collection<Loan> getExpiredLandLoansInReservation() {
        return expiredLandLoansInReservation;
    }

    @Override
    public Collection<Loan> getLoansOfHigherThanAverageDepreciation() {
        return loansOfHigherThanAverageDepreciation;
    }

    public BigDecimal getAverageVehicleDepreciation() {
        return averageVehicleDepreciation;
    }

    @Override
    public void calculateLowRiskHarvesterLoans() {
        lowRiskHarvesterLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getLoanRiskType() == LoanRiskType.LOW_RISK && loan instanceof HarvesterLoan) {
                lowRiskHarvesterLoans.add(loan);
            }
        }
    }

    @Override
    public void calculateExpiredLandLoansInReservation() {
        expiredLandLoansInReservation = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan instanceof LandLoan && ((LandLoan) loan).getInReservation()) {
                if (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date())) {
                    expiredLandLoansInReservation.add(loan);
                }
            }
        }
    }

    @Override
    public void calculateLoansOfHigherThanAverageDepreciation() {
        loansOfHigherThanAverageDepreciation = new ArrayList<>();
        averageVehicleDepreciation = new BigDecimal(0);
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
        averageVehicleDepreciation = averageVehicleDepreciation.divide(new BigDecimal(counter));
        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) {
                BigDecimal loanDeprecation = LoanUtil.calculateVehicleDepreciation(loan.getPrice(), ((VehicleLoan) loan).getManufactured(), ((VehicleLoan) loan).getMaximumAge());
                if (loanDeprecation.compareTo(averageVehicleDepreciation) > 0) {
                    loansOfHigherThanAverageDepreciation.add(loan);
                }
            }
        }
    }

    public Set<Loan> prioritizeLoans() {
        Set<Loan> result = new LinkedHashSet<>();

        return result;

    }
}

