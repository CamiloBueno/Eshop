package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchEngineOrder {
    private ArrayList<Order> orderlist;
    public SearchEngineOrder(ArrayList<Order> orderlist) {
        this.orderlist = orderlist;
    }

    public ArrayList<Order> sortUsingAVariable(String sortVariable, boolean order) {
        Collections.sort(orderlist, new CompareOrderUsingVariable(sortVariable));
        if(!order)
            Collections.reverse(orderlist);
        return orderlist;
    }

    public Order binarySearchOfOrderUsingStringValue(String value, String typeVariable){
        sortUsingAVariable( typeVariable, true);
        int low = 0;
        int high= orderlist.size()-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            String valueToCompare = getStringValueFromProductList(mid, typeVariable);
            if(value.compareTo(valueToCompare) > 0){
                low = mid+1;
            }else if(value.compareTo(valueToCompare) < 0){
                high = mid-1;
            }else{
                return orderlist.get(mid);
            }
        }

        if(low == high && orderlist.size() == 1){
            if( value.compareTo(getStringValueFromProductList(0, typeVariable)) == 0){
                return orderlist.get(0);
            }
        }
        return null;
    }

    public Order binarySearchOfOrderUsingNumericValue(Double value, String typeVariable){
        sortUsingAVariable(typeVariable, true);
        int low = 0;
        int high= orderlist.size()-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            Double valueToCompare = getNumericValueFromProductList(mid, typeVariable);
            if(valueToCompare!=-1){
                if(value.compareTo(valueToCompare) > 0){
                    low = mid+1;
                }else if(value.compareTo(valueToCompare) < 0){
                    high = mid-1;
                }else{
                    return orderlist.get(mid);
                }
            }else{
                break;
            }
        }
        return null;
    }
    public void setOrderlist(ArrayList<Order> orderlist) {
        this.orderlist = orderlist;
    }

    private String getStringValueFromProductList(int index, String value){
        switch (value){
            case "buyername":
                return orderlist.get(index).getBuyerName();
            case "date":
                return orderlist.get(index).transformDate();
            default:
                return "";
        }
    }
    private Double getNumericValueFromProductList(int index, String value){
        switch (value){
            case "totalprice":
                return orderlist.get(index).getTotalPrice();
            default:
                return -1.0;
        }
    }
}
