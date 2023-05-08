package model;

import java.util.Comparator;

public class CompareOrderUsingVariable implements Comparator<Order> {

    private String sortVariable;
    public CompareOrderUsingVariable(String sortVariable) {
        this.sortVariable = sortVariable;
    }

    @Override
    public int compare(Order o1, Order o2) {
        switch (sortVariable){
            case("buyername"):
                return sortGivenTwoValues(o1.getBuyerName(), o2.getBuyerName());
            case("totalPrice"):
                return sortGivenTwoNumericValues(Double.valueOf(o1.getTotalPrice()), Double.valueOf(o2.getTotalPrice()));
            case("datePurchase"):
                return sortGivenTwoValues(o1.getDatePurchase().toString(), o2.getDatePurchase().toString());
        }
        return 0;
    }
    private int sortGivenTwoValues(String ownValue, String otherValue){
        if(ownValue.compareTo(otherValue)<0){
            return -1;
        }else if(ownValue.compareTo(otherValue)>0){
            return 1;
        }else{
            return 0;
        }
    }

    private int sortGivenTwoNumericValues(Double ownValue, Double otherValue){
        if(ownValue.compareTo(otherValue)<0){
            return -1;
        }else if(ownValue.compareTo(otherValue)>0){
            return 1;
        }else{
            return 0;
        }
    }
}
