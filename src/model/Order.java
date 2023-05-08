package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String buyerName;
    private ArrayList<Product> productsList;
    private ArrayList<Integer> amountOfProducts;

    private double totalPrice;
    private LocalDateTime datePurchase;
    public Order(String buyerName, ArrayList<Product> productsList, LocalDateTime datePurchase) {
        this.buyerName = buyerName;
        this.productsList = productsList;
        this.datePurchase = datePurchase;
        this.amountOfProducts = new ArrayList<>();
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


    public void setAmountOfProducts(ArrayList amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDateTime datePurchase) {
        this.datePurchase = datePurchase;
    }

    public String transformDate(){
        return datePurchase.toString().substring(0,10).replace("-","/");
    }
    @Override
    public String toString(){
        return "Buyer name: "+ buyerName + " | Total price: "+totalPrice+" | Date: " + transformDate();
    }

    public void changeAmountToList(int index, int amount){
        productsList.get(index).setAmount(amount);
    }

    public String getProductList() {
        String products="List of products purchased:\n";
        for (int i = 0; i < productsList.size(); i++) {
            products += "- "+productsList.get(i).toStringToBuyers()+" | Amount Purchased: " + amountOfProducts.get(i) +" | Total: " +(amountOfProducts.get(i)*productsList.get(i).getPrice())+"\n";
        }
        return products;
    }
}
