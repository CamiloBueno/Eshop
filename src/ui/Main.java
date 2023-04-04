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
                    "\n1. Create a product"+
                    "\n2. Delete product"+
                    "\n3. Increse one product amount"+
                    "\n4. Search product"+
                    "\n5. Create order"+
                    "\n6. Exit"
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
        shop.addProduct(new Product("Jabon", "Para pieles sedosas", 2500, 10, 8));
        shop.addProduct(new Product ("Shampoo", "Para cabellos sedosos", 12500, 10, 8));
        shop.addProduct(new Product ("Mascarilla", "Para caras sedosas", 10000, 10, 8));
        System.out.print("Write the buyer name: ");
        String buyerName = scanner.nextLine();
        LocalDateTime orderDate = LocalDateTime.now();
        ArrayList<Product> productsList = new ArrayList<Product>();
        ArrayList<Integer> productsAmount = new ArrayList<Integer>();
        int option = -1;
        int amount = 0;
        while(option!=0){
            System.out.println("Select a product: \n"+shop.getProductsList() + "\n0.To EXIT");
            option = Integer.parseInt(scanner.nextLine());
            if(option>0){
                Product aux = shop.getProductByIndex(option);
                System.out.println("Enter the amount of the product: " + aux.getName() + "\nAvailable units: "+ aux.getAmount());
                amount = Integer.parseInt(scanner.nextLine());
                productsAmount.add(amount);
                productsList.add(aux);
            }
        }
        shop.createOrder(buyerName, orderDate, productsList, productsAmount);
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
                    "\n5.Food and Drinks " +
                    "\n6. Stationery " +
                    "\n7.Sports " +
                    "\n8. Games or Toys  ");
            category = Integer.parseInt(scanner.nextLine());
        }
        Product product = new Product(name, description, price, amount, category);
        shop.addProduct(product);
        return msg;
    }
}
