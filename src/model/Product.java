package model;

public class Product {
     private String name;
     private String description;
     private double price;
     private int amount;
     private int category;
     private Category enumCategory;
     private int purchasedAmount;

    public Product(String name, String description, double price, int amount, int purchasedAmount, int category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.purchasedAmount = purchasedAmount;
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

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Category getCategory() {
        return setCategory(category);
    }

    public Category setCategory(int category) {
        switch(category){
            case 1:
                return Category.BOOKS;
            case 2:
                return Category.ELECTRONICS;
            case 3:
                return Category.CLOTHES;
            case 4:
                return Category.ACCESORIES;
            case 5:
                return Category.FOODANDDRINK;
            case 6:
                return Category.STATIONERY;
            case 7:
                return Category.SPORTS;
            case 8:
                return Category.BEAUTYCARE;
            case 9:
                return Category.GAMESANDTOYS;
        }
        return null;
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(int purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    @Override
    public String toString(){
        return "Name: "+name+" | Price: "+price+" | Amount: "+amount +" | Description: "+ description+" | Purchased times: " +purchasedAmount + " | Category: "+ category+"\n";
    }

    public String toStringToBuyers(){
        return "Name: "+name+" | Price: "+price+ " | Description: "+ description;
    }

    public void applyChangesWithPurchase(int decreaseAmount) {
        amount -= decreaseAmount;
        purchasedAmount += decreaseAmount;
    }

    public void increaseAmount(int amountToIncrease) {
        amount += amountToIncrease;
    }
}
