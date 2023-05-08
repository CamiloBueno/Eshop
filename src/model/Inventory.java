package model;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Product> productsList;
    private SearchEngineProduct searchEngine;
    private final String[] OPTIONS ={"name", "price", "category", "purchasedAmount"};

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

    public Product deleteProduct(int productId){
        return productsList.remove(productId);
    }

    public Product getByIndex(int index){
        return productsList.get(index);
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = new ArrayList<>();
        this.productsList = productsList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public String simpleProductSearchNumericValue(String variableToSearchTransformed, Double valueToSearch, int[] sortOptions) {
        searchEngine.setProductList(productsList);
    Product product = searchEngine.binarySearchOfProductUsingNumericValue(valueToSearch, variableToSearchTransformed);
        if(product!=null){
            return product.toString();
        }else{
            return "Not found";
        }
    }
    public String simpleProductSearchStringValue(String variableToSearchTransformed, String valueToSearch, int[] sortOptions) {
        searchEngine.setProductList(productsList);
        Product product = searchEngine.binarySearchOfProductUsingStringValue(valueToSearch, variableToSearchTransformed);
        if(product!=null){
            return product.toString();
        }else{
            return "Not found";
        }    }

    public String complexProductSearchUsingNumericValues(String variableToSearch,Double min, Double max, int[] sortOptions){
        searchEngine.setProductList(productsList);
        List <Product> aux = searchEngine.bsProductUsingRangeOfNumericValue(min, max, variableToSearch);
        String result = "Not found";
        if(aux != null){
            searchEngine.setProductList(new ArrayList<>(aux));
            Boolean sort = (sortOptions[1] == 1 ? true: false);
            ///1. Ascendent (true) y 2. Descendent (false);
            List resultsList =  searchEngine.sortUsingAVariable(OPTIONS[sortOptions[0]-1], sort);
            result="";
                for (int i = 0; i < resultsList.size(); i++) {
                    result+=resultsList.get(i).toString();
                }
            }
        return result;
    }
}


