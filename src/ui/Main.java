package ui;

import com.google.gson.reflect.TypeToken;
import model.Order;
import model.Product;
import model.Shop;

import java.awt.font.NumericShaper;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    private Scanner scanner;
    private Shop mercadolibre;

    private final String[] F_CATEGORIES ={"Books","Clothes","Electronic","Accesories","Food and Drinks","Stationery","Sports","Beauty care","Games or Toys"};
    private final String[] CATEGORIES ={"BOOKS",
            "ELECTRONICS",
            "CLOTHES",
            "ACCESORIES",
            "FOODANDDRINK",
            "STATIONERY",
            "SPORTS",
            "BEAUTYCARE",
            "GAMESANDTOYS"};
    public Main(String shopName) {
        scanner = new Scanner(System.in);
        mercadolibre = new Shop(shopName);
    }

    public static void main(String[] args) {
        Main main = new Main("Mercadolibre");
        main.loadProducts();
        main.loadOrders();
        main.menu();
    }

    public void menu() {
        int option = 0;
        do {
            System.out.println(
                    "Hello! Welcome to " + mercadolibre.getName() + "\n Choose an option:" +
                            "\n1. Create a product" +
                            "\n2. Delete product" +
                            "\n3. Increase the amount of one product" +
                            "\n4. Search product or Order" +
                            "\n5. Create order" +
                            "\n6. Show list of Products"+
                            "\n7. Show list of Orders"+
                            "\n8. Exit"
            );
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
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
                    System.out.println(search());
                    break;
                case 5:
                    System.out.println(createOrder());
                    break;
                case 6:
                    System.out.println(showProductsList());
                    break;
                case 7:
                    System.out.println(showOrderList());
                    break;
            }
        } while (option != 8);
        saveOrders();
        saveProducts();
    }

    private String showOrderList() {
        return mercadolibre.getOrderList();
    }

    private void loadProducts() {
        String productsPath = "src/data/products.json";
        try {
            /// Importar la libreria Gson - (Google Json)
            Gson gson = new Gson();
            FileReader productsReader = new FileReader(productsPath);

            /// La documentacion de Google aconseja usar esto para deserializar
            Type productsListType = new TypeToken<ArrayList<Product>>() {
            }.getType();

            ArrayList<Product> productList = gson.fromJson(productsReader, productsListType);
            mercadolibre.getInventory().setProductsList(productList);
        } catch (FileNotFoundException e) {
            new File(productsPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrders() {
        String ordersPath = "src/data/orders.json";
        try {
            Gson gson = new Gson();
            FileReader orderReader = new FileReader(ordersPath);
            Type orderListType = new TypeToken<ArrayList<Order>>() {
            }.getType();
            ArrayList<Order> orderList = gson.fromJson(orderReader, orderListType);
            mercadolibre.setOrderList(orderList);
        } catch (FileNotFoundException e) {
            new File(ordersPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveOrders() {
        Gson gson = new Gson();
        String orderListSerialization = gson.toJson(mercadolibre.getOrders());
        try {
            FileWriter ordersWriter = new FileWriter("src/data/orders.json");
            ordersWriter.write(orderListSerialization);
            ordersWriter.flush();
            ordersWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    private String search() {
        System.out.println("Search Engine: \nWrite: \n1. For products \n2. For Orders");
        int typeSearch = Integer.parseInt(scanner.nextLine());

        while (typeSearch != 1 && typeSearch != 2) {
            System.out.println("Wrong option \nWrite: \n1. For products \n2. For Orders");
            typeSearch = Integer.parseInt(scanner.nextLine());
        }

        if (typeSearch == 1) {
            return searchProductMenu();
        } else {
            return searchOrderMenu();
        }
    }

    private String searchProductMenu() {
        String option = "Select the type of search do you want to use" +
                "\n1. Simple" +
                "\n2. Range";
        System.out.println(option);
        int typeSearch = Integer.parseInt(scanner.nextLine());

        while (typeSearch != 1 && typeSearch != 2) {
            System.out.println(option);
            typeSearch = Integer.parseInt(scanner.nextLine());
        }

        if (typeSearch == 1) {
            return simpleProductSearch();
        } else if (typeSearch == 2) {
            return rangeProductSearch();
        }
        return "";
    }

    private String simpleProductSearch() {
        String option = "Select the type of variable do you want to use to search the product: " +
                "\n1. Name" +
                "\n2. Price" +
                "\n3. Category" +
                "\n4. Times purchased";
        System.out.println(option);
        int variableSearch = Integer.parseInt(scanner.nextLine());
        boolean validProcess = false;
        while(!validProcess) {
            if (variableSearch == 2 || variableSearch == 4) {
                boolean isValidTheValue = false;
                while (!isValidTheValue) {
                    try {
                        System.out.print("Write the value to search: ");
                        Double valueToSearch = Double.parseDouble(scanner.nextLine());
                        int[] sortOptions = sortProductsSearchMenu();
                        isValidTheValue = true;
                        validProcess = true;
                        return mercadolibre.simpleProductSearchNumericValue(variableSearch, valueToSearch, sortOptions);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong format. Write a valid value please");
                    }
                }
            } else if (variableSearch == 1) {
                System.out.print("Write the value to search: ");
                String valueToSearch = (scanner.nextLine());
                int[] sortOptions = sortProductsSearchMenu();
                validProcess = true;
                return mercadolibre.simpleProductSearchStringValue(variableSearch, valueToSearch, sortOptions, false);
            } else if(variableSearch ==3){
                System.out.println("Choose the category to search: "+showCategories());
                int categoryIndex = Integer.parseInt(scanner.nextLine());
                int[] sortOptions = sortProductsSearchMenu();
                validProcess = true;
                while(categoryIndex<1 && categoryIndex>9){
                    System.out.println("ERROR. Choose again the category to search: "+showCategories());
                    categoryIndex = Integer.parseInt(scanner.nextLine());
                }
                String valueToSearch = CATEGORIES[categoryIndex-1];
                return mercadolibre.simpleProductSearchStringValue(variableSearch, valueToSearch, sortOptions, true);

            }else {
                System.out.println(option);
                variableSearch = Integer.parseInt(scanner.nextLine());
            }
        }
        return "";
    }

    private String rangeProductSearch(){
        System.out.println("Select the type of variable do you want to use to search the product: " +
                "\n1. Price" +
                "\n2. Amount" +
                "\n3. Times purchased");
        int variableToSearch = Integer.parseInt(scanner.nextLine());

        while (variableToSearch < 0 && variableToSearch > 3) {
            System.out.println("Write a correct value, please");
            variableToSearch = Integer.parseInt(scanner.nextLine());
        }

        System.out.print("Write a minimum value: ");
        Double minValue = Double.parseDouble(scanner.nextLine());

        System.out.print("Write a maximum value: ");
        Double maxValue = Double.parseDouble(scanner.nextLine());

        int[] searchOptions = sortProductsSearchMenu();

        return mercadolibre.complexSearchUsingNumericRange(variableToSearch, minValue, maxValue, searchOptions);
    }

    private String searchOrderMenu(){
        String options ="Select the variable do you want to use" +
                "\n1. Name" +
                "\n2. Total price"+
                "\n3. Purchase Date";
        System.out.println(options);
        int typeSearch =Integer.parseInt(scanner.nextLine());

        while(typeSearch<0 || typeSearch>3){
            System.out.println(options);
            typeSearch =Integer.parseInt(scanner.nextLine());
        }
        if(typeSearch == 2 ){
            try{
                System.out.print("Write the value to search: ");
                Double valueToSearch =Double.parseDouble(scanner.nextLine());
                return mercadolibre.searchOrderUsingNumericValue(typeSearch,valueToSearch);
            }catch (NumberFormatException e){
                System.out.print("Write a valid value. ");
            }
        }else if(typeSearch == 1 || typeSearch ==3){
            System.out.print("Write the value to search: ");
            String valueToSearch = scanner.nextLine();
            return mercadolibre.searchOrderUsingStringValue(typeSearch, valueToSearch);
        }
        return "";
    }
    private int[] sortProductsSearchMenu(){
        int[] options = new int[2];
        String variableOptions = "Write variable to sort: " +
                "\n1. Name" +
                "\n2. Price" +
                "\n3. Category" +
                "\n4. Times purchased";
        System.out.println(variableOptions);
        int variableToSort =Integer.parseInt(scanner.nextLine());
        while(variableToSort > 4 && variableToSort < 0){
            System.out.print("Write a valid option, please: ");
            System.out.print(variableOptions);
            variableToSort =Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Write order to sort \n1.Ascendent \n2.Descendent");
        int orderToSort =Integer.parseInt(scanner.nextLine());
        while(orderToSort!=1 && orderToSort!= 2){
            System.out.print("Write a valid option, please");
            System.out.print("Write order to sort \n1.Ascendent \n2.Descendent");
            orderToSort =Integer.parseInt(scanner.nextLine());
        }
        options[0] = variableToSort;
        options[1] = orderToSort;
      return options;
    };
    private String showProductsList(){
        return "\n"+mercadolibre.getProductsList()+"\n";
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
        ArrayList<Product> productsToExclude = new ArrayList<>();
        int option = -1;
        while(option!=0){
            System.out.println("Select a product: \n"+ mercadolibre.getProductsListExcluidingProducts(productsToExclude) + "\n0.To EXIT");
            option = Integer.parseInt(scanner.nextLine());
            if(option>0){
                Product aux = mercadolibre.getProductByIndex(option-1);
                System.out.println("Enter the amount of the product: " + aux.getName() + "\nAvailable units: "+ aux.getAmount());
                int amount = Integer.parseInt(scanner.nextLine());
                productsAmount.add(amount);
                productsList.add(aux);
                productsToExclude.add(aux);
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
        while(category < 1 && category > 9){
            System.out.println(showCategories());
            category = Integer.parseInt(scanner.nextLine());
        }
        Product product = new Product(name, description, price, amount, 0,category);
        mercadolibre.addProduct(product);
        return msg;
    }

    private String showCategories(){
        String categories = "";
        for (int i = 0; i < F_CATEGORIES.length; i++) {
            categories += "\n"+(i+1)+"."+F_CATEGORIES[i];
        }
        return categories;
    }
}
