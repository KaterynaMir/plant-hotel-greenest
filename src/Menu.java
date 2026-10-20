import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private static final String LINE = "----------------------------------------------";
    private final Scanner scanner;
    private final PlantManager plantManager;

    Menu(Scanner scanner, PlantManager plantManager){
        this.scanner = scanner;
        this.plantManager = plantManager;
    }

    public void run(){
        boolean running = true;
        try {
            while (running) {
                printMenu();
                running = handleChoice();
            }
        } catch (NoSuchElementException e){
            System.out.println("End of input. Exiting the program.");
        } finally {
            plantManager.writePlants();
        }
    }

    private void printMenu(){
        System.out.println(LINE);
        System.out.println("1. Show plants");
        System.out.println("2. Water plant");
        System.out.println("3. Add plant");
        System.out.println("4. Remove plant");
        System.out.println("5. Quit");
        System.out.println(LINE);
    }

    private boolean handleChoice(){
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();
        switch (choice){
            case "1" -> showPlants();
            case "2" -> waterPlant();
            case "3" -> addPlant();
            case "4" -> removePlant();
            case "5" -> {
                System.out.println("Exiting the program");
                return false;
            }
            default -> System.out.println("Unknown option. Please choose 1-5.");
        }
        return true;
    }

    private void showPlants() {
        if (plantManager.getPlants().isEmpty()){
            System.out.println("No plants registered.");
            return;
        }

        System.out.println("Plants:");
        for (Plant plant : plantManager.getPlants()) {
            System.out.println("  " + plant);
        }
    }

    private void waterPlant() {
        System.out.println("Which plant should receive liquid?");
        String name = readString("Name: ");
        Plant plant = plantManager.findPlant(name);
        if (plant!= null){
            plant.water();
            return;
        }
        System.out.println("Plant not found");
    }

    private void addPlant() {
        String name = readName();
        PlantType plantType = choosePlantType(name);
        double height = readHeight(name);
        Plant plant = createPlant(plantType, name, height);
        if (plantManager.addPlant(plant)) {
            System.out.println("Plant added successfully");
        }
    }

    private void removePlant() {
        String name = readString("Name: ");
        if (name.isBlank()) {
            System.out.println("Empty name is not allowed.");
            return;
        }
        if (plantManager.removePlant(name)){
            System.out.println("Removing plant " + name);
            return;
        }
        System.out.println("Plant not found");
    }

    private String readString(String promptMessage){
        System.out.print(promptMessage);
        return scanner.nextLine().trim();
    }

    private String readName(){
        while (true){
            String name = readString("Name: ");
            if (name.isBlank()){
                System.out.println("Empty name is not allowed.");
                continue;
            }
            if (plantManager.findPlant(name) != null){
                System.out.println("This name is already taken.");
                continue;
            }
            return name;
        }
    }

    private void printPlantTypes(){
        for (PlantType type: PlantType.values()){
            System.out.printf("  %d - %s\n",
                    type.getPlantTypeId(),
                    type.getPlantTypeDesc());
        }
    }

    private PlantType choosePlantType(String plantName){
        while (true) {
            System.out.println("Choose plant type:");
            printPlantTypes();
            String plantTypeIdString = readString(plantName + "'s type ID: ");
            try {
                int plantTypeId = Integer.parseInt(plantTypeIdString);
                PlantType plantType = PlantType.fromId(plantTypeId);
                if (plantType == null) {
                    System.out.println("Unknown plant type");
                    continue;
                }
                return plantType;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID number format.");
            }
        }
    }

    private double readHeight(String name){
        while (true) {
            String heightString = readString(name + "'s height in meters: ");
            try {
                double height = Double.parseDouble(heightString);
                if (height > 0 && height <= Plant.MAX_HEIGHT){
                    return height;
                }
                System.out.println("Height must be greater than 0" +
                        " and less or equal " + Plant.MAX_HEIGHT + " meters.");
            } catch (NumberFormatException e) {
                System.out.println("Illegal height, cannot convert to double.");
            }
        }
    }

    private Plant createPlant(PlantType type, String name, double height){
        return switch (type){
            case PALM -> new Palm(name, height);
            case CARNIVOROUS -> new Carnivorous(name, height);
            case CACTUS -> new Cactus(name, height);
        };
    }

}
