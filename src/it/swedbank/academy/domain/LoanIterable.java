package it.swedbank.academy.domain;

import java.util.Iterator;

public class LoanIterable implements Iterable<Loan> {
    //Naming! "arrayList" is not an "ArrayList"
    private Loan[] arrayList;
    //You can use "arrayList.length" directly in your iterator code. "length" in arrays is a field/property and not a calculated
    // value, so you don't need to save it here.
    private int currentSize;

    public LoanIterable(Loan[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }


    @Override
    public Iterator<Loan> iterator() {
        //You can return "new Iterator<Loan>()..." directly. "it" is not needed here.
        Iterator<Loan> it = new Iterator<Loan>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                //When "arrayList[currentIndex]" can be null? :)
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
