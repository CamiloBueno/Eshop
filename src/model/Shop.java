package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Shop {
    private String name;
    private Inventory inventory;
    private ArrayList<Order> ordersList;

    public Shop(String name) {
        this.name = name;
        inventory = new Inventory();
        ordersList = new ArrayList<Order>();
    }

    public void addProduct(Product product){
        inventory.addProduct(product);
    }

    public void deleteProduct(String id){

    }

    public void createOrder(String buyername, LocalDateTime date, ArrayList<Product> productsList, ArrayList<Integer> productsAmount){
        /// calcular el precio
        double totalprice = 0;
        for (int i = 0; i < productsList.size(); i++) {
            totalprice += productsList.get(i).getPrice() * productsAmount.get(i).intValue();
        }
        /// Disminuir productos
        for (int i = 0; i < productsList.size(); i++) {
            inventory.decreaseProduct(productsList.get(i).getName(), productsAmount.get(i));
        }
    }

    public String getProductsList(){
        ArrayList<Product> inventoryAux = inventory.getProductsList();
        String list ="List of products\n";
        int index=0;
        for (int i = 0; i < inventoryAux.size(); i++) {
            index = i+1;
            list += index+". "+inventoryAux.get(i).getName()+"\n";
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Product getProductByIndex(int option) {
        return inventory.getByIndex(option-1);
    }
}
