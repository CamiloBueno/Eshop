package model;

import java.util.Comparator;

public class CompareWithName  implements Comparator<Product> {
    public CompareWithName() {
    }

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

