package model;

public class Product {
     private String name;
     private String description;
     private double price;
     private int amount;
     private Category category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(int category) {
        switch(category){
            case 1:
                this.category = Category.BOOKS;
                break;
            case 2:
                this.category = Category.ELECTRONICS;
                break;
            case 3:
                this.category = Category.CLOTHES;
                break;
            case 4:
                this.category = Category.ACCESORIES;
                break;
            case 5:
                this.category = Category.FOODANDDRINK;
                break;
            case 6:
                this.category = Category.STATIONERY;
                break;
            case 7:
                this.category = Category.SPORTS;
                break;
            case 8:
                this.category = Category.BEAUTYCARE;
                break;
            case 9:
                this.category = Category.GAMESANDTOYS;
                break;
        }
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(int purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    @Override
    public String toString(){
        return "Name: "+name+" | Price: "+price+" | Amount: "+amount +" | Description: "+ description+ "\n";
    }

    public void applyChangesWithPurchase(int decreaseAmount) {
        amount -= decreaseAmount;
        purchasedAmount += decreaseAmount;
    }

    public void increaseAmount(int amountToIncrease) {
        amount += amountToIncrease;
    }
}
