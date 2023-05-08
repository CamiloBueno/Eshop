package test;

import model.Inventory;
import model.Product;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private ArrayList<Product> productsList;
    private Inventory inventory;

    public void setUp1() {
        inventory = new Inventory();
    }

    public void setUp2() {
        inventory = new Inventory();
        productsList = new ArrayList<Product>();
        productsList.add(new Product("Jabon", "Para pieles limpias", 2500, 10, 0,8));
        productsList.add(new Product("Shampoo", "Para cabellos sedosos", 12500, 10,0, 8));
        productsList.add(new Product("Mascarilla", "Para caras sedosas", 10000, 10, 0,8));
        productsList.add(new Product("Exfoliante", "Para pieles sedosas", 2500, 10,0, 8));
        productsList.add(new Product("Crema", "Para caras limpias", 15000, 10, 0,8));
        inventory.setProductsList(productsList);
    }

    @Test
    public void addProductTest(){
        setUp1();
        inventory.addProduct(new Product ("Jabon", "Para pieles sedosas", 100, 10, 0,8));
        inventory.addProduct(new Product ("Shampoo", "Para cabellos sedosos", 500, 10,0, 8));
        inventory.addProduct(new Product ("Mascarilla", "Para caras sedosas", 510, 10, 0,8));

        assertEquals("Jabon", inventory.getByIndex(0).getName());
        assertEquals("Shampoo", inventory.getByIndex(1).getName());
        assertEquals("Mascarilla", inventory.getByIndex(2).getName());
        assertEquals(3, inventory.getProductsList().size());

        String msg = inventory.addProduct(new Product ("Mascarilla", "Para caras sedosas", 510, 10, 0,8));
        assertEquals("This product already exist", msg);
    }

    @Test
    public void purchaseProductTest(){
        setUp2();
        assertEquals(5, inventory.purchaseProduct("Shampoo", 5).getAmount());
        assertEquals(7, inventory.purchaseProduct("Jabon", 3).getAmount());
        assertEquals(2, inventory.purchaseProduct("Exfoliante", 8).getAmount());
    }

    @Test
    public void deleteProductTest(){
        setUp2();
        /// Jabon, Shampoo, Mascarilla, Exfoliante, Crema

        assertEquals("Shampoo", inventory.deleteProduct(1).getName());
        assertEquals(4, inventory.getProductsList().size());
        /// Jabon, Mascarilla, Exfoliante, Crema

        assertEquals("Exfoliante", inventory.deleteProduct(2).getName());
        assertEquals(3, inventory.getProductsList().size());
        /// Jabon, Mascarilla, Crema

        assertEquals("Jabon", inventory.deleteProduct(0).getName());
        assertEquals(2, inventory.getProductsList().size());
        /// Mascarilla, Crema

        assertEquals("Crema", inventory.deleteProduct(1).getName());
        assertEquals(1, inventory.getProductsList().size());
    }
}
