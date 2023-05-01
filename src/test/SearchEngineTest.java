package test;

import model.Inventory;
import model.Product;
import model.SearchEngineProduct;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchEngineTest {
    private ArrayList<Product> productsList;
    private Inventory inventory;
    private SearchEngineProduct searchEngine;

    /// Este escenario crea productos y los añade al arreglo de productList
    public void setUp1(){
        inventory = new Inventory();
        productsList = new ArrayList<Product>();
        ///String name, String description, double price, int amount, int category
        productsList.add(new Product ("Jabon", "Para pieles limpias", 2500, 10, 8));
        productsList.add(new Product ("Shampoo", "Para cabellos sedosos", 12500, 10, 8));
        productsList.add(new Product ("Mascarilla", "Para caras sedosas", 10000, 10, 8));
        productsList.add(new Product ("Exfoliante", "Para pieles sedosas", 2500, 10, 8));
        productsList.add(new Product ("Crema", "Para caras sedosas", 15000, 10, 8));
        inventory.setProductsList(productsList);
        searchEngine = new SearchEngineProduct(inventory.getProductsList());
    }

    @Test
    public void sortUsingNameTest(){
        setUp1();
        searchEngine.sortUsingAVariable("name",true);

        assertEquals("Crema", inventory.getByIndex(0).getName());
        assertEquals("Exfoliante", inventory.getByIndex(1).getName());
        assertEquals("Jabon", inventory.getByIndex(2).getName());
        assertEquals("Mascarilla", inventory.getByIndex(3).getName());
        assertEquals("Shampoo", inventory.getByIndex(4).getName());
    }

    @Test
    public void sortUsingPriceTest(){
        setUp1();
        searchEngine.sortUsingAVariable("price", true);
        System.out.println(inventory.getProductsList().toString());
        assertEquals(2500, inventory.getByIndex(0).getPrice());
        assertEquals(2500, inventory.getByIndex(1).getPrice());
        assertEquals(10000, inventory.getByIndex(2).getPrice());
        assertEquals(12500, inventory.getByIndex(3).getPrice());
        assertEquals(15000, inventory.getByIndex(4).getPrice());
    }

    @Test
    public void binarySearchGivingNameTest(){
        setUp1();
        assertNull(searchEngine.binarySearchOfProductUsingStringValue("Crema dental", "name"));
        assertEquals("Shampoo", searchEngine.binarySearchOfProductUsingStringValue("Shampoo", "name").getName());
        assertEquals("Jabon", searchEngine.binarySearchOfProductUsingStringValue("Jabon", "name").getName());
        assertEquals("Exfoliante", searchEngine.binarySearchOfProductUsingStringValue("Exfoliante", "name").getName());
    }

    @Test
    public void binarySearchOfProductUsingNumericValue(){
        setUp1();
        assertEquals("Jabon", searchEngine.binarySearchOfProductUsingNumericValue(2500.0, "price").getName());
        assertEquals("Shampoo", searchEngine.binarySearchOfProductUsingNumericValue(12500.0, "price").getName());
        assertEquals("Crema", searchEngine.binarySearchOfProductUsingNumericValue(15000.0, "price").getName());
    }
}
