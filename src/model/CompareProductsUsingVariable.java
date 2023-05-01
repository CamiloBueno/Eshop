package model;

import java.util.Comparator;

public class CompareProductsUsingVariable implements Comparator<Product> {
    private String sortVariable;
    public CompareProductsUsingVariable(String sortVariable) {
        this.sortVariable=  sortVariable;
    }

    @Override
    public int compare(Product p1, Product p2) {
        switch (sortVariable){
            case("name"):
                return sortGivenTwoValues(p1.getName(), p2.getName());
            case("category"):
                return sortGivenTwoValues(p1.getCategory().toString(), p2.getCategory().toString());
            case("price"):
                return sortGivenTwoNumericValues(Double.valueOf(p1.getPrice()), Double.valueOf(p2.getPrice()));
            case("purchasedAmount"):
                return sortGivenTwoNumericValues(Double.valueOf(p1.getPurchasedAmount()), Double.valueOf(p2.getPurchasedAmount()));
            case("amount"):
                return sortGivenTwoNumericValues(Double.valueOf(p1.getAmount()), Double.valueOf(p2.getAmount()));
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

