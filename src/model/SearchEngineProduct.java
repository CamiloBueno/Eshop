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

    public Product binarySearchOfProductUsingNumericValue( Double value, String typeVariable){
        sortUsingAVariable(typeVariable, true);
        int low = 0;
        int high= productsList.size()-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            Double valueToCompare = getNumericValueFromProductList(mid, typeVariable);
            if(valueToCompare!=-1){
                if(value.compareTo(valueToCompare) > 0){
                    low = mid+1;
                }else if(value.compareTo(valueToCompare) < 0){
                    high = mid-1;
                }else{
                    return productsList.get(mid);
                }
            }else{
                break;
            }
        }
        return null;
    }

    public  ArrayList<Product> bSOfCoincidencesUsingSuffixOrPrefix( boolean criteria, String value){
        sortUsingAVariable("name", true);
        ArrayList<Product> coincidences = new ArrayList<>();
        int low = 0;
        int high= productsList.size()-1;
        while(low<=high){
            int mid = low + (high-low/2);
            String currentWord = productsList.get(mid).getName();
            if(criteria){
                if (currentWord.startsWith(value)){
                    coincidences.add(productsList.get(mid));
                }
            }else{
                if (currentWord.endsWith(value)){
                    coincidences.add(productsList.get(mid));
                }
                int endIndex = currentWord.length()-1;
                int beginIndex = endIndex-value.length();
                currentWord = currentWord.substring(beginIndex, endIndex);
            }

            if (currentWord.compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return coincidences;
    }

    public List<Product> sliceArrayList(int start, int end){
        return productsList.subList(start, end);
    }

    public List<Product> bsProductUsingRangeOfNumericValue(Double min, Double max, String typeVariable){
        int start = binarySearchOfIndexGivingANumericValue( min, typeVariable);
        int end = binarySearchOfIndexGivingANumericValue( max, typeVariable);
        return sliceArrayList(start, end);
    }

    public int binarySearchOfIndexGivingANumericValue(Double value, String typeVariable){
        sortUsingAVariable(typeVariable, true);
        int low = 0;
        int high= productsList.size()-1;
        int mid = 0;
        while(low<=high){
            mid = low + (high-low)/2;
            Double valueToCompare = getNumericValueFromProductList(mid, typeVariable);
            if(value.compareTo(valueToCompare) < 0){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return mid;
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
