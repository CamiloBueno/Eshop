package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private ArrayList<Product> productsList;
    private SearchEngineProduct searchEngine;

    public Inventory() {
        productsList = new ArrayList<>();
        searchEngine = new SearchEngineProduct(productsList);
    }

    public String addProduct(Product product){
        if(searchEngine.binarySearchOfProductUsingStringValue(product.getName(), "name") == null){
            productsList.add(product);
            return "Product added successfully";
        }
        return "This product already exist";
    }

    public Product purchaseProduct(String id, int amountToDecrease){
        searchEngine.setProductList(productsList);
        Product aux = searchEngine.binarySearchOfProductUsingStringValue(id, "name");
        aux.applyChangesWithPurchase(amountToDecrease);
        return aux;
    }


    public Product getByIndex(int index){
        return productsList.get(index);
    }

    public void setProductsList(ArrayList<Product> productsList) {
        if(productsList!=null)
            this.productsList = productsList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

}
