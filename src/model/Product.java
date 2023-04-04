package model;

public class Product implements Comparable<String> {

     private String name;
     private String description;
     private double price;
     private int amount;
     private String category;
     private int purchasedAmount;

    public Product(String name, String description, double price, int amount, int category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        setCategory(category);
        purchasedAmount = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(int category) {
        //// AQUI REALIZAMOS LA LOGICA DE LA CATEGORIA
        //this.category = category;
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(int purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    @Override
    public String toString(){
        return "name: "+name+" | price: "+price+" | Amount: "+amount;
    }

    @Override
    public int compareTo(String secondName) {
        if(name.compareTo(secondName)<0){
            return -1;
        }else if(name.compareTo(secondName)>0){
            return 1;
        }else{
            return 0;
        }
    }
}
