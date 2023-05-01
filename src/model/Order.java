package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String buyerName;
    private ArrayList<Product> productsList;
    private double totalPrice;
    private LocalDateTime datePurchase;
    public Order(String buyerName, ArrayList<Product> productsList, LocalDateTime datePurchase) {
        this.buyerName = buyerName;
        this.productsList = productsList;
        this.datePurchase = datePurchase;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDateTime datePurchase) {
        this.datePurchase = datePurchase;
    }
}
