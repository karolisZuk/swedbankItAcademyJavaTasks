package it.swedbank.academy.app;


import it.swedbank.academy.domain.Loan;
import it.swedbank.academy.domain.LoanRiskType;
import it.swedbank.academy.service.DomainInitializer;
import it.swedbank.academy.service.LoanService;
import it.swedbank.academy.service.Task3DomainInitializer;

import java.util.Collections;

public class ClientApp {

    public static void main(String[] args) {

       // task1();
       // task2();
        task3();
    }


    public static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

    private static void task1(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);
        //run calculations
        service.calculateHighRiskLoans();
        service.calculateAverageLoanCost();
        service.calculateAverageLoanCostByRiskType();
        service.calculateMaximumPriceOfNonExpiredLoans();
        //output
        System.out.println("There are "+service.getHighRiskLoans());
        System.out.println("Average loan cost: "+service.getAverageLoanCost());

        System.out.println("LOW RISK: "+service.getAverageLoanCostByRiskType(LoanRiskType.LOW_RISK));
        System.out.println("NORMAL RISK: "+service.getAverageLoanCostByRiskType(LoanRiskType.NORMAL_RISK));
        System.out.println("HIGH RISK: "+service.getAverageLoanCostByRiskType(LoanRiskType.HIGH_RISK));

        System.out.println("AVERAGE PRICE OF HIGH RISK LOANS: "+service.getAverageCostOfHighRiskLoans());
        System.out.println("MAXIMUM PRICE OF NON EXPIRED LOANS: "+service.getMaximumPriceOfNonExpiredLoans());
    }

    private static void task2(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.calculateNormalRiskVehicleLoans();
        service.calculateMaximumAgeOfLowRiskLoanedVehicles();
       // service.calculatePersonalRealEstateLoans();
        service.calculateExpiredHighRiskVehicleLoansOfHighestDuration();

        System.out.println("There are: "+service.getNormalRiskVehicleLoans().size());
        System.out.println(service.getMaximumAgeOfLowRiskLoanedVehicles());
      //  System.out.println("There are: "+service.getPersonalRealEstateLoans().size());
        System.out.println("There is "+service.getExpiredHighRiskVehicleLoansOfHighestDuration().size()+", and highest duration is "+ Collections.max(service.getExpiredHighRiskVehicleLoansOfHighestDuration()));
    }


    private static void task3(){
        //init
        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.calculateLowRiskHarvesterLoans();
        service.calculateExpiredLandLoansInReservation();
        service.calculateLoansOfHigherThanAverageDepreciation();

        System.out.println("There are " + service.getLowRiskHarvesterLoans().size());
        System.out.println("There is " + service.getExpiredLandLoansInReservation().size());
        System.out.println("There is " + service.getLoansOfHigherThanAverageDepreciation().size() + " average deprecation is " + service.getAverageVehicleDepreciation());

    }

}
