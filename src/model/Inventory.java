package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private ArrayList<Product> productsList;

    public Inventory() {
        productsList = new ArrayList<>();
    }
    //// C R U D

    public void addProduct(Product product){
    //// Buscar que no esté repetido
        productsList.add(product);
    }

    public void sortUsingCollections() {

        //// sort recibe como parametros un Lista y una clase Comparator
        Collections.sort(productsList, new CompareWithName());
    }

    ///// Debemos usar la clase Comparable con los metodos CompareTo
    public Product binarySearchDemo(String name){
        sortUsingCollections();
        Product aux = null;
        int low = 0;
        int high= productsList.size()-1;
        int mid = (int) Math.ceil(productsList.size()/2);
        boolean found = false;
        while(low<high && high-low>1 && high!=mid && !found){
            //// name es mayor al name de la posicion mid
           if(name.compareTo(productsList.get(mid).getName()) > 0){
               low = mid;
               mid = (int)(low+high/2);
               //// name es menor al name de la posicion mid
           }else if(name.compareTo(productsList.get(mid).getName()) < 0){
               high = mid;
               mid = high/2;
               /// Son iguales :D
           }else{
               found = true;
               return productsList.get(mid);
           }
        }
        return aux;
    }

    public Product decreaseProduct(String id, int amountToDecrease){
        Product aux=null;

        return aux;
    }

    public void deleteProduct(String id){

    }

    public Product getByIndex(int index){
        return productsList.get(index);
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

}
