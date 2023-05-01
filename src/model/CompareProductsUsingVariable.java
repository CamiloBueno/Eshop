package model;

import java.util.Comparator;

public class CompareProductsUsingVariable implements Comparator<Product> {
    private String sortVariable;
    public CompareProductsUsingVariable(String sortVariable) {
        this.sortVariable=  sortVariable;
    }
    @Override
    public int compare(Product p1, Product p2) {
        return 0;
    }
}

