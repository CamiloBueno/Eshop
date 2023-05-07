package test;

import model.Order;
import model.Product;
import model.Shop;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest {
    private Shop mercadolibre;
    private Order order;
    public void setUp1(){
        mercadolibre = new Shop("Mercadolibre");
        mercadolibre.addProduct(new Product("Jabon", "Para pieles sedosas", 2500, 10, 8));
        mercadolibre.addProduct(new Product ("Shampoo", "Para cabellos sedosos", 12500, 10, 8));
        mercadolibre.addProduct(new Product ("Mascarilla", "Para caras sedosas", 10000, 10, 8));
    }
    public void setUp2(){
        setUp1();
        ArrayList<Product> productsList = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        productsList.add(mercadolibre.getProductByIndex(0));
        productsList.add(mercadolibre.getProductByIndex(1));
        order = new Order("Camilo",productsList, date);
    }
    @Test
    public void addProductTest(){
        setUp1();
        //// Aqui quiero mirar que tengo 3 productos en mi inventario
        assertEquals(3, mercadolibre.getInventory().getProductsList().size());

        //// Aqui quiero ver que el producto en la posicion 0 tenga de nombre Jabon
        assertEquals("Jabon", mercadolibre.getInventory().getByIndex(0).getName());

        /// Aqui lo que quiero ver, es que el producto 2 tenga de precio 12.500
        assertEquals(12500, mercadolibre.getInventory().getByIndex(1).getPrice());

        /// Aqui lo que quiero ver, es que el producto 3 tiene 10 cantidades.
        assertEquals(10, mercadolibre.getInventory().getByIndex(2).getAmount());

        int totalProducts = mercadolibre.getInventory().getProductsList().size();
        mercadolibre.addProduct(new Product("Shampoo", "Para una cabello sedosa", 10000,10, 4));

        assertEquals(totalProducts, mercadolibre.getInventory().getProductsList().size());
    }

    @Test
    public void createOrderTest(){
        setUp2();
        ArrayList<Integer> amountProduct = new ArrayList<>();
        //// Añade 4 Jabones y despues 2 shampoos
        amountProduct.add(4);
        amountProduct.add(2);
        mercadolibre.createOrder(order, amountProduct);
        //assertEquals(6, mercadolibre.getInventory().binarySearchOfProductUsingStringValue("Jabon","name").getAmount());
        //assertEquals(8, mercadolibre.getInventory().binarySearchOfProductUsingStringValue("Shampoo","name").getAmount());

        assertEquals(1 , mercadolibre.getOrders().size());
        assertEquals("Camilo", mercadolibre.getOrders().get(0).getBuyerName());

    }

    @Test
    public void increaseAmountOfProductTest(){
        setUp1();
        mercadolibre.increaseAmountOfProduct(1, 15);
        mercadolibre.increaseAmountOfProduct(2, 8);
        mercadolibre.increaseAmountOfProduct(3, 3);
        assertEquals(25, mercadolibre.getProductByIndex(0).getAmount());
        assertEquals(18, mercadolibre.getProductByIndex(1).getAmount());
        assertEquals(13, mercadolibre.getProductByIndex(2).getAmount());
    }
}
