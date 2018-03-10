package it.swedbank.academy.app;


import it.swedbank.academy.domain.Loan;
import it.swedbank.academy.domain.LoanIterable;
import it.swedbank.academy.domain.LoanRiskType;
import it.swedbank.academy.service.*;

import java.util.Collections;

public class ClientApp {

    public static void main(String[] args) {

        task1();
        task2();
        task3();
    }


    public static DomainInitializer getInitializer(int initialiser) {
        switch (initialiser) {
            case 1:
                return new Task1DomainInitializer();
            case 2:
                return new Task2DomainInitializer();
            default:
                return new Task3DomainInitializer();
        }

    }

    private static void task1() {
        Loan[] loans = getInitializer(1).initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);
        System.out.println("TASK 1");
        System.out.println("There are " + service.calculateHighRiskLoans());
        System.out.println("Average loan cost: " + service.calculateAverageLoanCost());
        System.out.println("LOW RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.LOW_RISK));
        System.out.println("NORMAL RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.NORMAL_RISK));
        System.out.println("HIGH RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.HIGH_RISK));
        System.out.println("AVERAGE PRICE OF HIGH RISK LOANS: " + service.calculateAverageCostOfHighRiskLoans());
        System.out.println("MAXIMUM PRICE OF NON EXPIRED LOANS: " + service.calculateMaximumPriceOfNonExpiredLoans());
    }

    private static void task2() {
        Loan[] loans = getInitializer(2).initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);
        System.out.println("TASK 2");
        System.out.println("There are: " + service.calculateNormalRiskVehicleLoans().size());
        System.out.println("Maximum age of low risk loaned vehicles:" + service.calculateMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println("There are: " + service.calculatePersonalRealEstateLoans().size());
        System.out.println("There is " + service.calculateExpiredHighRiskVehicleLoansOfHighestDuration().size() + ", and highest duration is " + Collections.max(service.calculateExpiredHighRiskVehicleLoansOfHighestDuration()));
    }

    private static void task3() {
        Loan[] loans = getInitializer(3).initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);
        System.out.println("TASK 3");
        System.out.println("There are " + service.calculateLowRiskHarvesterLoans().size());
        System.out.println("There is " + service.calculateExpiredLandLoansInReservation().size());
        System.out.println("There is " + service.calculateLoansOfHigherThanAverageDepreciation().size() + " average deprecation is " + service.calculateAverageDepreciation());

    }

}
