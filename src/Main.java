import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String LINE = "----------------------------------------------";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Plant> PLANTS = new ArrayList<>();

    public static void main(String[] args) {

        PLANTS.add(new Cactus("Igge", 0.2));
        PLANTS.add(new Palm("Laura", 5.0));
        PLANTS.add(new Carnivorous("Meatloaf", 0.7));
        PLANTS.add(new Palm("Olof", 1.0));

        boolean running = true;
        while (running){
            printMenu();
            running = handleChoice(SCANNER);
        }
        SCANNER.close();
    }


    private static void printMenu(){
        System.out.println(LINE);
        System.out.println("1. Show plants");
        System.out.println("2. Water plant");
        System.out.println("3. Add plant");
        System.out.println("4. Remove plant");
        System.out.println("5. Quit");
        System.out.println(LINE);
    }


    private static boolean handleChoice(Scanner scanner){
        System.out.print("Choice: ");
        String choice = scanner.next();
        scanner.nextLine();
        switch (choice){
            case "1" -> showPlants();
            case "2" -> waterPlant(scanner);
            case "3" -> addPlant(scanner);
            case "4" -> removePlant(scanner);
            case "5" -> {
                System.out.println("Avslutar programmet");
                return false;
            }
            default -> System.out.println("Unknown option");
        }
        return true;
    }


    private static void removePlant(Scanner scanner) {
    }


    private static void addPlant(Scanner scanner) {
    }


    private static void waterPlant(Scanner scanner) {
        System.out.println("Which plant should receive liquid?");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        for (Plant plant : PLANTS) {
            if (plant.getName().equalsIgnoreCase(name)){
                plant.water();
                return;
            }
        }
        System.out.println("Plant not found");
    }

    private static void showPlants() {
        System.out.println("Plants:");
        for (Plant plant : PLANTS) {
            System.out.println(plant);;
        }
    }

}
