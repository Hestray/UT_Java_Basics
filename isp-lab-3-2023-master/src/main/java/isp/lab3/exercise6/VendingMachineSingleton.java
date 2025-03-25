package isp.lab3.exercise6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineSingleton {
        //    AND ALL OF MY CONSTRUCTORS WILL BE PRIVATE
        String product;
        List<String> products    = new ArrayList<>();
        List<Integer>   prices      = new ArrayList<>();
        int profit = 0;         // profit for vending machine
        int number = 0;         // number of products
        int credit = 0;         // current credit for user

        private VendingMachineSingleton(String prodName, int cost) {
            this.number++;

            this.products.add(prodName);
            this.prices.add(cost);
        }

        private VendingMachineSingleton(String[] prodList, int[] cost, String command) {
            if (prodList.length != cost.length)
                System.out.println("ERROR: THE LIST OF PRODUCTS AND LIST OF THE PRICES MUST HAVE THE SAME LENGTH.");
            else
            {
                if (command.contentEquals("R")) // replace the old product list with another product list
                {
                    // clear the lists so that they can be fully replaced
                    this.products.clear();
                    this.prices.clear();
                }
                number      = prodList.length;
                for (int i = 0; i < prodList.length; i++) {
                    this.products.add(prodList[i]);
                    this.prices.add(cost[i]);
                }
            }
        }
        private static volatile VendingMachineSingleton instance;
        public static VendingMachineSingleton getInstance() {
            if (instance != null)
                return instance;
            else
            {
                synchronized (VendingMachineSingleton.class) {
                    if (instance == null) {
                        instance = new VendingMachineSingleton("rabbit toy", 5);
                    }
                    return instance;
                }
            }
        }
        public void getProducts() {
            for (int i = 0; i < number; i++)
                System.out.println("Product: " + products.get(i) + "\t\t ID: " + i + "\t\t Price: " + prices.get(i));
        }

        public void insertCoin(int value) { profit += value; credit += value; }

        public String selectProduct(int id) {
            // check if the id is valid and if there is a product with that id
            if (products.get(id) == null) return "ERROR";
            else
            if (id > number) return "ERROR";
            else
            if (this.credit < this.prices.get(id)) return "ERROR";
            else return products.get(id);
        }

        public void displayCredit() {
            System.out.println("Your credit is: " + credit);
        }

        public boolean isInteger(String input) {
            if (input == null) {
                return false;
            }
            int length = input.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (input.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            for (; i < length; i++) {
                char c = input.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }
        public void userBuy() {
            System.out.println("STEPS TO BUY:\n1. Insert coins\n2. Select product\n3. Leave");

            int choice = 0;
            int ID = -1;

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    // insert coins
                    System.out.println("In order to stop inserting coins, type \"ok\".");
                    String approval = "0";
                    while(!approval.contentEquals("ok"))
                    {
                        // while user adds coins, keep counting
                        int coin = Integer.parseInt(approval);
                        this.insertCoin(coin);       // increase profit and credit

                        System.out.println("Insert coin of value: ");
                        approval = scanner.next();

                        // if 'ok' or its variants, convert to lowercase 'OK'
                        if (!isInteger(approval))
                        {
                            approval = approval.toLowerCase();
                            String okayVariants = "ok, okay, k, oky, kay, oke";
                            if (okayVariants.contains(approval)){
                                approval = "ok";
                            }
                            else {
                                System.out.println("Invalid choice. Please input either a positive integer or \"ok\" to terminate.");
                                approval = scanner.next();
                            }
                        }
                        else {
                            if (Integer.parseInt(approval) < 0) {
                                System.out.println("Whoops looks like you introduced a negative number. Make sure your value is a positive integer number.");
                                approval = scanner.next();
                            }
                        }
                    }
                    break;
                case 2:
                    // select product to buy
                    System.out.println("Write the ID of the product you want to buy: ");
                    ID = scanner.nextInt();
                    this.selectProduct(ID);

                    // receive product
                    if(this.selectProduct(ID).contains("ERROR")) {
                        System.out.println("Yeah, don't try your luck with no money on you kid.");
                    }
                    else
                    {
                        System.out.println("Clink clank, your product is here and you can pick it up!");
                        this.credit = this.credit - prices.get(ID);
                    }

                    // receive rest
                    String restChoice;
                    if(!this.selectProduct(ID).contains("ERROR")) {
                        System.out.println("Do you want to receive rest? Type 'yes' or 'no': ");

                        // user choice
                        restChoice = scanner.next();

                        // act based on choice
                        if (restChoice.toLowerCase().contains("y") || restChoice.toLowerCase().contains("yes")) {
                            System.out.println("Here is your rest! " + this.credit);
                            this.insertCoin(this.credit * (-1));
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        public void userMenu() {
            System.out.println("Hello and welcome! What do you want to do?");
            System.out.println("\n1. See product list\n2. Buy a product\n3. Display current credit\n4. Leave");

            int choice = 0;

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    this.getProducts();
                    this.userMenu();
                    break;
                case 2:
                    this.userBuy();
                    this.userMenu();
                    break;
                case 3:
                    this.displayCredit();
                    this.userMenu();
                    break;
                case 4:
                    System.out.println("Goodbye! Thank you for stopping by!");
                    break;
                default:
                    System.out.println("Not a valid choice.");
                    this.userMenu();
                    break;
            }
        }
        public static void main(String[] args) {
            String[]    prodList    = {"beer", "cola", "water"};
            int[]       prices      = {7, 5, 2};
            VendingMachineSingleton prod = new VendingMachineSingleton(prodList, prices, "R");

            prod.userMenu();

            System.out.println("Today's profit is: " + prod.profit);
        }
    }
