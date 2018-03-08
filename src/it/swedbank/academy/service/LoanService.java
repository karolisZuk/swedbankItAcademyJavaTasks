package it.swedbank.academy.service;

import it.swedbank.academy.domain.*;
import it.swedbank.academy.util.DateUtil;
import it.swedbank.academy.util.LoanUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private LoanIterable loans;
    private Collection<Loan> normalRiskVehicleLoans;
    private int maximumAgeOfLowRiskLoanedVehicles;
    private Collection<Loan> personalRealEstateLoans;
    private Collection<Integer> expiredHighRiskVehicleLoansOfHighestDuration;
    private Collection<Loan> lowRiskHarvesterLoans;
    private ArrayList<Loan> expiredLandLoansInReservation;
    private ArrayList<Loan> loansOfHigherThanAverageDepreciation;
    private BigDecimal averageVehicleDepreciation;

    public LoanService(LoanIterable loan) {
        this.loans = loan;
    }
    //1
    public int calculateHighRiskLoans() {
        int count=0;
        for(Loan loan: this.loans){
            if(loan.getLoanRiskType()==LoanRiskType.HIGH_RISK){
                count++;
            }
        }
        return count;
    }

    public BigDecimal calculateAverageLoanCost() {
        BigDecimal sum = BigDecimal.ZERO;
        int count=0;
        for(Loan loan: this.loans){
            sum=sum.add(loan.getTotalLoanCost());
            count++;
        }
        return sum.divide(new BigDecimal(count), 3, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateAverageCostOfHighRiskLoans() {
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
        BigDecimal averageCost = new BigDecimal(0);
        int counter = 0;
        for(Loan loan: this.loans) {
            if (loan.getLoanRiskType() == riskType) {
                averageCost = averageCost.add(loan.getTotalLoanCost());
                counter++;
            }
        }
        return averageCost.divide(new BigDecimal(counter));
    }

    public BigDecimal calculateMaximumPriceOfNonExpiredLoans() {
        List<BigDecimal> notExpiredList = new ArrayList<BigDecimal>();
        for(Loan loan: this.loans) {
            if(DateUtil.addYears(loan.getCreationDate(),loan.getTermInYears()).before(new Date())){
                notExpiredList.add(loan.getTotalLoanCost());
            }
        }
        return Collections.max(notExpiredList);
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

    public ArrayList<Loan> calculateNormalRiskVehicleLoans() {
        ArrayList<Loan> normalRiskVehicleLoans = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.NORMAL_RISK && loan instanceof VehicleLoan){
                normalRiskVehicleLoans.add(loan);
            }
        }
        return normalRiskVehicleLoans;
    }

    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        maximumAgeOfLowRiskLoanedVehicles=0;
        ArrayList<Integer> lowRiskLoanedVehicles = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.LOW_RISK && loan instanceof VehicleLoan){
                lowRiskLoanedVehicles.add(((VehicleLoan) loan).getMaximumAge());
            }
        }
        return (Collections.max(lowRiskLoanedVehicles).equals(0) ? 0 : Collections.max(lowRiskLoanedVehicles));
    }

    public void calculatePersonalRealEstateLoans() {
        personalRealEstateLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (((RealEstateLoan) loan).getPurpose() == RealEstatePurpose.PERSONAL && loan instanceof RealEstateLoan) {
                personalRealEstateLoans.add(loan);
            }
        }
    }

    public Collection<Integer> calculateExpiredHighRiskVehicleLoansOfHighestDuration() {
        expiredHighRiskVehicleLoansOfHighestDuration = new ArrayList<>();
        for(Loan loan:loans){
            if(loan.getLoanRiskType()==LoanRiskType.HIGH_RISK && loan instanceof VehicleLoan){
                if (DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date())) {
                    expiredHighRiskVehicleLoansOfHighestDuration.add(((VehicleLoan) loan).getMaximumAge());
                }
            }
        }
        return expiredHighRiskVehicleLoansOfHighestDuration;
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
        Set<Loan> sortedResult = new LinkedHashSet<>();


        return sortedResult;

    }
}

