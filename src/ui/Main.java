package ui;

import com.google.gson.reflect.TypeToken;
import model.Order;
import model.Product;
import model.Shop;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    private Scanner scanner;
    private Shop mercadolibre;

    public Main(String shopName){
        scanner = new Scanner(System.in);
        mercadolibre = new Shop(shopName);
    }

    public static void main(String [] args){
        Main main = new Main("Mercadolibre");
        main.loadProducts();
        main.menu();
    }

    public void menu(){
        int option = 0;
        do{
            System.out.println(
            "Hello! Welcome to " + mercadolibre.getName() + "\n Choose an option:" +
                    "\n1. Create a product *"+
                    "\n2. Delete product *"+
                    "\n3. Increase the amount of one product *"+
                    "\n4. Search product"+
                    "\n5. Create order *" +
                    "\n6. Show list of products *"+
                    "\n7. Exit"
            );
        option = Integer.parseInt(scanner.nextLine());
        switch (option){
            case 1:
                System.out.println(addProduct());
                break;
            case 2:
                System.out.println(deleteProduct());
                break;
            case 3:
                System.out.println(increaseProduct());
                break;
            case 4:
                break;
            case 5:
                System.out.println(createOrder());
                break;
            case 6:
                System.out.println(showProductsList());
                scanner.nextLine();
                break;
        }
        }while(option!=7);
        saveProducts();
    }

    private void loadProducts() {
        String productsPath = "src/data/products.json";
        try {
            /// Importar la libreria Gson - (Google Json)
            Gson gson = new Gson();
            FileReader productsReader = new FileReader(productsPath);

            /// La documentacion de Google aconseja usar esto para deserializar
            Type productsListType = new TypeToken<ArrayList<Product>>(){}.getType();

            ArrayList<Product> productList = gson.fromJson(productsReader, productsListType);
            mercadolibre.getInventory().setProductsList(productList);
        } catch (FileNotFoundException e) {
            new File(productsPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveProducts() {
        Gson gson = new Gson();
        String productListSerialization = gson.toJson(mercadolibre.getInventory().getProductsList());
        try {
            FileWriter productsWriter = new FileWriter("src/data/products.json");
            productsWriter.write(productListSerialization);
            productsWriter.flush();
            productsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String showProductsList(){
        return "Available products:\n"+mercadolibre.getProductsList()+"\nEnter to exit....";
    }

    private String deleteProduct() {
        System.out.println("Choose the product to delete:\n");
        System.out.println(mercadolibre.getProductsList());
        int productId = Integer.parseInt(scanner.nextLine())-1;
        return mercadolibre.deleteProduct(productId);
    }

    private String increaseProduct() {
        System.out.println("Choose one product, using the index, to increase it amount:\n");
        System.out.println(mercadolibre.getProductsList());
        int productIndex = Integer.parseInt(scanner.nextLine());
        System.out.print("Write the amount to increase: ");
        int amount = Integer.parseInt(scanner.nextLine());
        return mercadolibre.increaseAmountOfProduct(productIndex, amount);
    }

    private String createOrder() {
        String msg="";
        System.out.print("Write the buyer name: ");
        String buyerName = scanner.nextLine();
        LocalDateTime orderDate = LocalDateTime.now();
        ArrayList<Product> productsList = new ArrayList<Product>();
        ArrayList<Integer> productsAmount = new ArrayList<Integer>();
        int option = -1;
        while(option!=0){
            System.out.println("Select a product: \n"+ mercadolibre.getProductsList() + "\n0.To EXIT");
            option = Integer.parseInt(scanner.nextLine());
            if(option>0){
                Product aux = mercadolibre.getProductByIndex(option-1);
                System.out.println("Enter the amount of the product: " + aux.getName() + "\nAvailable units: "+ aux.getAmount());
                int amount = Integer.parseInt(scanner.nextLine());
                productsAmount.add(amount);
                productsList.add(aux);
            }
        }
        Order order = new Order(buyerName, productsList, orderDate);
        mercadolibre.createOrder(order, productsAmount);
        return msg;
    }

    private String addProduct(){
        String msg ="";
        System.out.print("Write the product name: ");
        String name = scanner.nextLine();
        System.out.print("Write the product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the product price without dots: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the amount of the product : ");
        int amount = Integer.parseInt(scanner.nextLine());
        int category = 0;
        while(category < 1 && category > 8){
            System.out.print("Select a category: " +
                    "\n1. Books " +
                    "\n2. Electronic " +
                    "\n3. Clothes " +
                    "\n4. Accesories " +
                    "\n5. Food and Drinks " +
                    "\n6. Stationery " +
                    "\n7. Sports " +
                    "\n8. Beauty care " +
                    "\n8. Games or Toys  ");
            category = Integer.parseInt(scanner.nextLine());
        }
        Product product = new Product(name, description, price, amount, category);
        mercadolibre.addProduct(product);
        return msg;
    }
}
