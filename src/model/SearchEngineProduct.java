package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchEngineProduct {
    private ArrayList<Product> productsList;
    public SearchEngineProduct(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public ArrayList<Product> sortUsingAVariable(String sortVariable, boolean order) {
        Collections.sort(productsList, new CompareProductsUsingVariable(sortVariable));
        if(!order)
            Collections.reverse(productsList);
        return productsList;
    }

    public Product binarySearchOfProductUsingStringValue(String value, String typeVariable){
        sortUsingAVariable( typeVariable, true);
        int low = 0;
        int high= productsList.size()-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            String valueToCompare = getStringValueFromProductList(mid, typeVariable);
            if(value.compareTo(valueToCompare) > 0){
                low = mid+1;
            }else if(value.compareTo(valueToCompare) < 0){
                high = mid-1;
            }else{
                return productsList.get(mid);
            }
        }
        return null;
    }
    public List<Product> sliceArrayList(int start, int end){
        return productsList.subList(start, end);
    }

    private String getStringValueFromProductList(int index, String value){
        switch (value){
            case "name":
                return productsList.get(index).getName();
            case "category":
                return productsList.get(index).getCategory().toString();
            default:
                return "";
        }
    }
    private Double getNumericValueFromProductList(int index, String value){
        switch (value){
            case "price":
                return productsList.get(index).getPrice();
            case "purchasedAmount":
                return (double)productsList.get(index).getPurchasedAmount();
            case "amount":
                return (double)productsList.get(index).getAmount();
            default:
                return -1.0;
        }
    }

    public void setProductList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }
}
