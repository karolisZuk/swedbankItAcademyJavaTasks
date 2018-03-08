package it.swedbank.academy.domain;

import java.util.Iterator;

public class LoanIterable implements Iterable<Loan> {
    private Loan[] arrayList;
    private int currentSize;

    public LoanIterable(Loan[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }


    @Override
    public Iterator<Loan> iterator() {
        Iterator<Loan> it = new Iterator<Loan>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Loan next() {
                return arrayList[currentIndex++];
            }
        };
        return it;
    }

}
