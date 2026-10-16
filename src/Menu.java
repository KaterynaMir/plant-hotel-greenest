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
        while (running){
            printMenu();
            running = handleChoice();
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
                plantManager.writePlants();
                System.out.println("Exiting the program");
                return false;
            }
            default -> System.out.println("Unknown option");
        }
        return true;
    }

    private void showPlants() {
        System.out.println("Plants:");
        for (Plant plant : plantManager.getPlants()) {
            System.out.println("  " + plant);
        }
    }

    private void waterPlant() {
        System.out.println("Which plant should receive liquid?");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        Plant plant = plantManager.findPlant(name);
        if (plant!= null){
            plant.water();
            return;
        }
        System.out.println("Plant not found");
    }

    private void addPlant() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()){
            System.out.println("Empty name is not allowed.");
            return;
        }

        if (plantManager.findPlant(name) != null){
            System.out.println("This name is already taken.");
            return;
        }

        System.out.println("Choose plant type:");
        for (PlantType type: PlantType.values()){
            System.out.printf("  %d - %s\n",
                    type.getPlantTypeId(),
                    type.getPlantTypeDesc());
        }

        System.out.print(name + "'s type: ");
        String plantTypeIdString = scanner.nextLine().trim();
        int plantTypeId;
        try{
            plantTypeId = Integer.parseInt(plantTypeIdString);
        } catch (NumberFormatException e){
            System.out.println("Invalid plant type.");
            return;
        }

        PlantType plantType = PlantType.fromId(plantTypeId);
        if (plantType == null){
            System.out.println("Unknown plant type");
            return;
        }

        System.out.print(name + "'s height in meters: ");
        String heightString = scanner.nextLine().trim();

        try{
            double height = Double.parseDouble(heightString);
            Plant plant = switch (plantType){
                case PALM -> new Palm(name, height);
                case CARNIVOROUS -> new Carnivorous(name, height);
                case CACTUS -> new Cactus(name, height);
            };
            if(plantManager.addPlant(plant)){
                System.out.println("Plant added successfully");
            }
        } catch (NumberFormatException e){
            System.out.println("Illegal height, cannot convert to double.");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void removePlant() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (plantManager.removePlant(name)){
            System.out.println("Removing plant " + name);
            return;
        }
        System.out.println("Plant not found");
    }
}
