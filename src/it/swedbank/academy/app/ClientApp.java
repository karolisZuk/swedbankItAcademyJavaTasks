package it.swedbank.academy.app;


import it.swedbank.academy.domain.Loan;
import it.swedbank.academy.domain.LoanIterable;
import it.swedbank.academy.domain.LoanRiskType;
import it.swedbank.academy.service.DomainInitializer;
import it.swedbank.academy.service.LoanService;
import it.swedbank.academy.service.Task2DomainInitializer;

import java.util.Collections;

public class ClientApp {

    public static void main(String[] args) {

        //  task1();
        task2();

        // task3();
    }


    public static DomainInitializer getInitializer() {
        return new Task2DomainInitializer();
    }

    private static void task1(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);
        //output
        System.out.println("There are " + service.calculateHighRiskLoans());
        System.out.println("Average loan cost: " + service.calculateAverageLoanCost());
        System.out.println("LOW RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.LOW_RISK));
        System.out.println("NORMAL RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.NORMAL_RISK));
        System.out.println("HIGH RISK: " + service.calculateAverageLoanCostByRiskType(LoanRiskType.HIGH_RISK));
        System.out.println("AVERAGE PRICE OF HIGH RISK LOANS: " + service.calculateAverageCostOfHighRiskLoans());
        System.out.println("MAXIMUM PRICE OF NON EXPIRED LOANS: " + service.calculateMaximumPriceOfNonExpiredLoans());
    }

    private static void task2(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);
        //out
        service.calculateMaximumAgeOfLowRiskLoanedVehicles();
        service.calculatePersonalRealEstateLoans();
        System.out.println("There are: " + service.calculateNormalRiskVehicleLoans().size());
        System.out.println(service.calculateMaximumAgeOfLowRiskLoanedVehicles());
        System.out.println("There are: " + service.getPersonalRealEstateLoans().size());
        System.out.println("There is " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().size() + "," +
                " and highest duration is " + Collections.max(service.getExpiredHighRiskVehicleLoansOfHighestDuration()));
    }


    private static void task3(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanIterable loanList = new LoanIterable(loans);
        LoanService service = new LoanService(loanList);

        service.calculateLowRiskHarvesterLoans();
        service.calculateExpiredLandLoansInReservation();
        service.calculateLoansOfHigherThanAverageDepreciation();

        System.out.println("There are " + service.getLowRiskHarvesterLoans().size());
        System.out.println("There is " + service.getExpiredLandLoansInReservation().size());
        System.out.println("There is " + service.getLoansOfHigherThanAverageDepreciation().size() + " average deprecation is " + service.getAverageVehicleDepreciation());

    }

}
