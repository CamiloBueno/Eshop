package model;

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

    public void createOrder(Order order, ArrayList<Integer> productsAmount){
        /// Calcular el precio y disminuir productos
        ArrayList<Product> productList = order.getProductsList();
        double totalPrice = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            totalPrice += currentProduct.getPrice() * productsAmount.get(i);
            inventory.purchaseProduct(currentProduct.getName(), productsAmount.get(i));
        }
        order.setTotalPrice(totalPrice);
        ordersList.add(order);
    }

    public String getProductsList(){
        ArrayList<Product> inventoryAux = inventory.getProductsList();
        String list ="";
        int index=0;
        for (int i = 0; i < inventoryAux.size(); i++) {
            index = i+1;
            list += index+". "+inventoryAux.get(i).toString();
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Product getProductByIndex(int option) {
        return inventory.getByIndex(option);
    }

    public ArrayList<Order> getOrders(){
        return ordersList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.ordersList = orderList;
    }
}
