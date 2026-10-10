import java.util.*;


public class Main {
    private static final String LINE = "----------------------------------------------";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<String,Plant> PLANTS = new HashMap<>();

    public static void main(String[] args) {
        SCANNER.useLocale(Locale.US);
        PLANTS.put("IGGE", new Cactus("IGGE", 0.2));
        PLANTS.put("LAURA", new Palm("LAURA", 5.0));
        PLANTS.put("MEATLOAF", new Carnivorous("MEATLOAF", 0.7));
        PLANTS.put("OLOF", new Palm("OLOF", 1.0));

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
        String choice = scanner.nextLine().trim();
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
        System.out.print("Name: ");
        String name = scanner.nextLine().trim().toUpperCase();
        if (PLANTS.containsKey(name)){
            PLANTS.remove(name);
            System.out.println("Removing plant " + name);
            return;
        }
        System.out.println("Plant not found");
    }


    private static void addPlant(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim().toUpperCase();
        if (name.isEmpty()){
            System.out.println("Empty name is not allowed.");
            return;
        }
        if (PLANTS.containsKey(name)){
            System.out.println("This name is already taken.");
            return;
        }

        System.out.print(name + "'s class\n" +
                "\t\t(1- palm\n" +
                "\t\t 2 - carnivorous\n" +
                "\t\t 3 - cactus): ");
        String plantClass = scanner.nextLine().trim();
        if (!plantClass.matches("[1-3]")){
            System.out.println("Unknown plant class");
            return;
        }

        System.out.print(name + "'s height in meters: ");
        String heightString = scanner.nextLine().trim();
        try{
            double height = Double.parseDouble(heightString);
            Plant plant = null;
            switch (plantClass){
                case "1" -> {
                    plant = new Palm(name, height);
                }
                case "2" -> {
                    plant = new Carnivorous(name, height);
                }
                case "3" -> {
                    plant = new Cactus(name, height);
                }
            }
            PLANTS.put(plant.getName().toUpperCase(), plant);
        } catch (NumberFormatException e){
            System.out.println("Illegal height, cannot convert to double.");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


    private static void waterPlant(Scanner scanner) {
        System.out.println("Which plant should receive liquid?");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim().toUpperCase();
        if (PLANTS.containsKey(name)){
            PLANTS.get(name).water();
            return;
        }
        System.out.println("Plant not found");
    }

    private static void showPlants() {
        System.out.println("Plants:");
        for (Plant plant : PLANTS.values()) {
            System.out.println(plant);;
        }
    }

}
