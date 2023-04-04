package ui;

import model.Product;
import model.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private Shop shop;

    public Main(String shopName){
        scanner = new Scanner(System.in);
        shop = new Shop(shopName);
    }
    public static void main(String [] args){
        Main main = new Main("Mercadolibre");
        main.menu();
    }

    public void menu(){
        int option = 0;
        do{
            System.out.println(
            "Hello! Welcome to " + shop.getName() + "\n Choose an option:" +
                    "\1. Create a product"+
                    "\2. Delete product"+
                    "\3. Increse one product amount"+
                    "\4. Search product"+
                    "\5. Create order"+
                    "\6. Exit"
            );
        option = Integer.parseInt(scanner.nextLine());
        switch (option){
            case 1:
                System.out.println(addProduct());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println(createOrder());
                break;
        };
        }while(option!=6);
    }

    private String createOrder() {
        String msg="";
        System.out.print("Write the buyer name: ");
        String buyerName = scanner.nextLine();
        LocalDateTime orderDate = LocalDateTime.now();
        ArrayList<Product> productsList = new ArrayList<Product>();
        ArrayList<Integer> productsAmount = new ArrayList<Integer>();

        int option = -1;
        int amount = 0;
        do{
            System.out.println("Select a product: \n"+shop.getProductsList() + "\n 0. To EXIT");
            option = Integer.parseInt(scanner.nextLine());
            Product aux = shop.getProductByIndex(option);
            System.out.print("Enter the amount of " + aux.getName() + ": ");
            amount = Integer.parseInt(scanner.nextLine());
            productsAmount.add(amount);
            productsList.add(aux);
        }while(option!=0);
        shop.createOrder(buyerName, orderDate, productsList, productsAmount);
        return msg;
    }

    private String addProduct(){
        String msg ="";
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
                    "\n5.Food and Drinks " +
                    "\n6. Stationery " +
                    "\n7.Sports " +
                    "\n8. Games or Toys  ");
            category = Integer.parseInt(scanner.nextLine());
        }
        Product product = new Product(description, price, amount, category);
        shop.addProduct(product);
        return msg;
    }
}
