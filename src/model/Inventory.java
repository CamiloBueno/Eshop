package model;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> productsList;

    public Inventory() {
        productsList = new ArrayList<Product>();
    }
    //// C R U D

    public void addProduct(Product product){
    //// Buscar que no esté repetido
        productsList.add(product);
    }

    public Product binarySearchDemo(String name){
        Product aux = null;
        double low = 0;
        double high= productsList.size()-1;
        int mid = (int)Math.floor(high/2);
        boolean found = false;
        while(low<high && !found){
           /////
        }
        return aux;
    };

    public void deleteProduct(String id){

    }

    public Product getByIndex(int index){
        return productsList.get(index);
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

}
