package model;

import java.util.ArrayList;

public class Shop {
    private String name;
    private Inventory inventory;
    private ArrayList<Order> ordersList;

    private SearchEngineOrder searchEngineOrder;

    private final String[] OPTIONS ={"name", "price", "category", "purchasedAmount"};
    private final String[] OPTIONS_ORDER ={"buyername", "totalprice", "date"};

    private final String[] OPTIONS_RANGE ={ "price", "amount", "purchasedAmount"};

    public Shop(String name) {
        this.name = name;
        inventory = new Inventory();
        ordersList = new ArrayList<Order>();
        this.searchEngineOrder = new SearchEngineOrder(ordersList);
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
        if(ordersList == null){
            ordersList =  new ArrayList<>();
        }
        order.setTotalPrice(totalPrice);
        order.setAmountOfProducts(productsAmount);
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
        return "\nRESULTS: \n"+inventory.simpleProductSearchStringValue(variableToSearchTransformed, valueToSearch, sortOptions);
    }

    public String complexSearchUsingNumericRange(int variableToSearch, Double minValue, Double maxValue, int[] sortOptions) {
        String variableToSearchTransformed = OPTIONS_RANGE[variableToSearch-1];
        return "\n RESULTS: \n"+inventory.complexProductSearchUsingNumericValues(variableToSearchTransformed, minValue, maxValue, sortOptions);
    }

    public String searchOrderUsingNumericValue(int typeSearch, Double valueToSearch) {
        searchEngineOrder.setOrderlist(ordersList);
        String variableSearch = OPTIONS_ORDER[typeSearch-1];
        return searchEngineOrder.binarySearchOfOrderUsingNumericValue(valueToSearch, variableSearch).toString();
    }

    public String searchOrderUsingStringValue(int typeSearch, String valueToSearch) {
        searchEngineOrder.setOrderlist(ordersList);
        String variableSearch = OPTIONS_ORDER[typeSearch-1];
        return "RESULTS: "+ searchEngineOrder.binarySearchOfOrderUsingStringValue(valueToSearch, variableSearch).toString();
    }
    public String getProductsList(){
        ArrayList<Product> inventoryAux = inventory.getProductsList();
        String list ="LIST OF PRODUCTS\n";
        int index=0;
        for (int i = 0; i < inventoryAux.size(); i++) {
            index = i+1;
            list += index+". "+inventoryAux.get(i).toString();
        }
        return list;
    }

    public String getOrderList(){
        String list ="LIST OF ORDERS\n";
        int index=0;
        for (int i = 0; i < ordersList.size(); i++) {
            index = i+1;
            list += index+". "+ordersList.get(i).toString()+"\n";
            list+="---------------------------------------------------------------\n";
            list+= ordersList.get(i).getProductList();
            list+="################################################################\n";
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

    public String getProductsListExcluidingProducts(ArrayList excludedProducts) {
        String list ="";
        for (int i = 0; i < inventory.getProductsList().size(); i++) {
            if(!excludedProducts.contains(inventory.getProductsList().get(i))){
                list+= (i+1) +"."+inventory.getProductsList().get(i).toString();
            }
        }
        return list;
    }
}
