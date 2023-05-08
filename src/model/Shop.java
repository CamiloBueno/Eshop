package model;

import java.util.ArrayList;

public class Shop {
    private String name;
    private Inventory inventory;
    private ArrayList<Order> ordersList;

    private final String[] OPTIONS ={"name", "price", "category", "purchasedAmount"};
    public Shop(String name) {
        this.name = name;
        inventory = new Inventory();
        ordersList = new ArrayList<Order>();
    }

    public void addProduct(Product product){
        inventory.addProduct(product);
    }

    public String deleteProduct(int productId){
        if (inventory.deleteProduct(productId) !=null)
            return "Product deleted successfully";
        else
            return "Error deleting product. Try again";
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

    public String increaseAmountOfProduct(int productIndex, int amount) {
        try{
            Product aux = inventory.getByIndex(productIndex-1);
            aux.increaseAmount(amount);
            return "Product edited successfully!" +
                    "\nNew product list:\n"+getProductsList();
        }catch(IndexOutOfBoundsException e){
            return "Indice incorrecto";
        }
    }

    public String simpleProductSearchNumericValue(int variableSearch, Double valueToSearch, int[] sortOptions) {
        String variableToSearchTransformed = OPTIONS[variableSearch-1];
        return "\n RESULTS: \n"+inventory.simpleProductSearchNumericValue(variableToSearchTransformed, valueToSearch, sortOptions);
    }

    public String simpleProductSearchStringValue(int variableSearch, String valueToSearch, int[] sortOptions, boolean isCategory) {
        String variableToSearchTransformed = OPTIONS[variableSearch-1];
        return "\n RESULTS: \n"+inventory.simpleProductSearchStringValue(variableToSearchTransformed, valueToSearch, sortOptions);
    }

    public String complexSearchUsingNumericRange(int variableToSearch, int minValue, int maxValue, int[] searchOptions) {
        return null;
    }

    public String searchOrderUsingNumericValue(int typeSearch, int valueToSearch) {
        return null;
    }

    public String searchOrderUsingStringValue(int typeSearch, String valueToSearch) {
        return null;
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
