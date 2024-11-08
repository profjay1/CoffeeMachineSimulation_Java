package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {

        CoffeeMachineApp machine = new CoffeeMachineApp(400, 540, 120, 9, 550);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, clean, exit):\n> ");
            String action = sc.nextLine();

            switch (action) {
                case "buy" -> machine.buyCoffee(sc);
                case "fill" -> machine.refillSupplies(sc);
                case "take" -> machine.takeMoney();
                case "remaining" -> machine.coffeeMachineResources();
                case "clean" -> machine.clean();
                case "exit" -> {
                    System.exit(0);
                    return;
                }
                default -> System.out.println("Invalid action, please try again");
            }
        }
    }
}

class CoffeeMachineApp {
     private int water;
     private int milk;
     private int coffeeBeans;
     private int disposableCups;
     private int money;
     private int coffeeCount;

     public CoffeeMachineApp(int water, int milk, int coffeeBeans, int disposableCups, int money) {
         this.water = water;
         this.milk = milk;
         this.coffeeBeans = coffeeBeans;
         this.disposableCups = disposableCups;
         this.money = money;
         this.coffeeCount = 0;
     }

     public int getWater() {
         return water;
     }

     public int getMilk() {
         return milk;
     }

     public int getCoffeeBeans() {
         return coffeeBeans;
     }

     public int getDisposableCups() {
         return disposableCups;
     }

     public int getMoney() {
         return money;
     }


     public void coffeeMachineResources() {
         System.out.println("\nThe coffee machine has:");
         System.out.println(water + " ml of water");
         System.out.println(milk + " ml of milk");
         System.out.println(coffeeBeans + " g of coffee beans");
         System.out.println(disposableCups + " disposable cups");
         System.out.println("$" + money + " of money");
     }

     public void buyCoffee(Scanner sc) {

        if (coffeeCount >= 10) {
            System.out.println("I need cleaning!");
            return;
     }

         System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ");

        Coffee coffee;
        String choice = sc.nextLine();

         if (choice.equals("back")) {
             return;
         }
         switch (choice) {
             case "1" -> coffee = new Coffee(250, 0, 16, 4);
             case "2" -> coffee = new Coffee(350, 75, 20, 7);
             case "3" -> coffee = new Coffee(200, 100, 12, 6);
             default -> {
                 System.out.println("Invalid choice, please try again");
                 return;
             }
         }

         if (canMakeCoffee(coffee)) {
             water -= coffee.getWater();
             milk -= coffee.getMilk();
             coffeeBeans -= coffee.getCoffeeBeans();
             money += coffee.getCost();
             disposableCups--;
             coffeeCount++;
             System.out.println("I have enough resources, making you a coffee!");
         }
     }

     public boolean canMakeCoffee(Coffee coffee) {
         if (coffee.getWater() > water) {
             System.out.println("Sorry, not enough water!");
             return false;
         }
         if (coffee.getMilk() > milk) {
             System.out.println("Sorry, not enough milk!");
             return false;
         }
         if (coffee.getCoffeeBeans() > coffeeBeans) {
             System.out.println("Sorry, not enough coffee!");
             return false;
         }
         if (disposableCups < 1) {
             System.out.println("Sorry, not enough disposable cups!");
             return false;
         }
         return true;
     }

     public void refillSupplies(Scanner sc) {
         System.out.print("Write how many ml of water you want to add:\n> ");
         water += sc.nextInt();
         System.out.print("Write how many ml of milk you want to add:\n> ");
         milk += sc.nextInt();
         System.out.print("Write how many grams of coffee beans you want to add:\n> ");
         coffeeBeans += sc.nextInt();
         System.out.print("Write how many disposable cups you want to add:\n> ");
         disposableCups += sc.nextInt();
         sc.nextLine();
     }

     public void takeMoney() {
         System.out.println("I gave you $" + money);
         money = 0;
     }

     public void clean() {
         if (coffeeCount >= 10) {
             coffeeCount = 0;
             System.out.println("I have been cleaned!");
         } else {
             System.out.println("I do not cleaning now!");
         }
     }
}

class Coffee {
    private final int water;
    private final int milk;
    private final int coffeeBeans;
    private final int cost;

    public Coffee(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }

    public int getWater() {
        return water;
    }
    public int getMilk() {
        return milk;
    }
    public int getCoffeeBeans() {
        return coffeeBeans;
    }
    public int getCost() {
        return cost;
    }
}
