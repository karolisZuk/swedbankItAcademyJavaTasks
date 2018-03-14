package it.swedbank.academy.domain;


import java.math.BigDecimal;
import java.util.Date;

public class Loan {
    private Date creationDate;
    private int termInYears;
    private String name;
    private BigDecimal interestRate;
    private BigDecimal price;
    private LoanRiskType riskType;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public LoanRiskType getLoanRiskType(){
        return this.riskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "creationDate=" + creationDate +
                ", termInYears=" + termInYears +
                ", name='" + name + '\'' +
                '}';
    }


    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getPrice() {
       return price;
    }


    public BigDecimal getTotalLoanCost(){
        return price.add(calculateInterest());
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal calculateInterest(){
        return price.multiply(interestRate.divide(new BigDecimal(100)));
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    //Is this generated, or you wrote it by yourself? :)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;

        Loan loan = (Loan) o;

        if (termInYears != loan.termInYears) return false;
        if (creationDate != null ? !creationDate.equals(loan.creationDate) : loan.creationDate != null) return false;
        if (name != null ? !name.equals(loan.name) : loan.name != null) return false;
        if (interestRate != null ? !interestRate.equals(loan.interestRate) : loan.interestRate != null) return false;
        if (price != null ? !price.equals(loan.price) : loan.price != null) return false;
        return riskType == loan.riskType;
    }

    //Is this generated, or you wrote it by yourself? :)
    @Override
    public int hashCode() {
        int result = creationDate != null ? creationDate.hashCode() : 0;
        result = 31 * result + termInYears;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (interestRate != null ? interestRate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (riskType != null ? riskType.hashCode() : 0);
        return result;
    }


}
