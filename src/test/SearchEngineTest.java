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
    public void setUp0(){
        inventory = new Inventory();
    }

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

    public void setUp2(){
        setUp1();
        productsList.add(new Product ("Mascarilla Arroz", "Para pieles sedosas", 2500, 10, 8));
        productsList.add(new Product ("Masa de Arepas", "Para ricas arepas", 12500, 10, 8));
        productsList.add(new Product ("Mascara Halloween", "Para asustar a todos", 10000, 10, 8));
        productsList.add(new Product ("Crema coreana", "Para pieles sedosas", 2500, 10, 8));
        productsList.add(new Product ("Pan Mariana", "Para ricas arepas", 12500, 10, 8));
        productsList.add(new Product ("Cruz Cristiana", "Para asustar a todos", 10000, 10, 8));
        inventory.setProductsList(productsList);
        searchEngine = new SearchEngineProduct(inventory.getProductsList());

    }
    public void setUp3(){
        inventory = new Inventory();
        productsList = new ArrayList<Product>();
        ///String name, String description, double price, int amount, int category
        productsList.add(new Product ("Jabon", "Para pieles sedosas", 100, 10, 8));
        productsList.add(new Product ("Exfoliante Coco", "Para pieles sedosas", 2000, 10, 8));
        productsList.add(new Product ("Crema", "Para pieles sedosas", 750, 10, 8));
        productsList.add(new Product ("Crema Cicatrizante", "Para pieles sedosas", 910, 10, 8));
        productsList.add(new Product ("Mascarilla", "Para caras sedosas", 510, 10, 8));
        productsList.add(new Product ("Crema Bonita", "Para pieles sedosas", 832, 10, 8));
        productsList.add(new Product ("Crema Arawak", "Para pieles sedosas", 800, 10, 8));
        productsList.add(new Product ("Exfoliante Bakano", "Para pieles sedosas", 1024, 10, 8));
        productsList.add(new Product ("Exfoliante Arcilla", "Para pieles sedosas", 600, 10, 8));
        productsList.add(new Product ("Shampoo", "Para cabellos sedosos", 500, 10, 8));

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

    @Test
    public void binarySearchOfCoincidencesGivingNameTest(){
        setUp2();
        String prefix ="Mas";
        ArrayList<Product> productsFounded = searchEngine.bSOfCoincidencesUsingSuffixOrPrefix(true, prefix);
        assertEquals(4, productsFounded.size());
        for (int i = 0; i < productsFounded.size(); i++) {
            assertTrue(productsFounded.get(i).getName().startsWith(prefix));
        }

        /*
        String suffix = "ana";
        ArrayList<Product> productsFounded2 = inventory.bSOfCoincidencesUsingSuffixOrPrefix(false, suffix);
        assertEquals(3, productsFounded2.size());
        for (int i = 0; i < productsFounded2.size(); i++) {
            assertTrue(productsFounded2.get(i).getName().endsWith(suffix));
        }
        assertEquals(0,inventory.bSOfCoincidencesUsingSuffixOrPrefix(true,"Zo").size());
         */
    }

    @Test
    public void bsProductUsingRangeOfNumericValueTest() {
        setUp3();
        double min = 550.0;
        double max = 1000.0;
        String[] productNames = {"Exfoliante Arcilla", "Crema", "Crema Arawak", "Crema Bonita", "Crema Cicatrizante"};
        List<Product> results = searchEngine.bsProductUsingRangeOfNumericValue(min, max, "price");
        for (int i = 0; i < results.size(); i++) {
            Product aux = results.get(i);
            assertTrue(aux.getPrice() > min && aux.getPrice() < max);
            assertEquals(productNames[i], aux.getName());
        }
    }

    @Test
    public void simpleBinarySearchOfNumericValueTest() {
        setUp3();
        double min = 550.0;
        double max = 1000.0;
        int minIndex= searchEngine.binarySearchOfIndexGivingANumericValue(min, "price");
        int maxIndex =  searchEngine.binarySearchOfIndexGivingANumericValue(max, "price");
        assertEquals(3 , minIndex);
        assertEquals(8 , maxIndex);
        assertTrue(inventory.getByIndex(minIndex).getPrice()>min);
        assertTrue(inventory.getByIndex(maxIndex-1).getPrice()<max);
    }

    @Test
    public void sliceArrayListTest(){
        setUp3();
        List<Product> productsSlice1 = searchEngine.sliceArrayList(0,4);
        assertEquals("Jabon", productsSlice1.get(0).getName());
        assertEquals("Crema Cicatrizante", productsSlice1.get(3).getName());


        List<Product> productsSlice2 = searchEngine.sliceArrayList(4,7);
        assertEquals("Mascarilla", productsSlice2.get(0).getName());
        assertEquals("Crema Arawak", productsSlice2.get(4).getName());
    }
}
