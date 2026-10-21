import javax.swing.JOptionPane;

public class Menu {
    private static final String WINDOW_TITLE = "Hotel Greenest";

    private final PlantManager plantManager;

    public Menu(PlantManager plantManager){
        this.plantManager = plantManager;
    }

    public void run(){
        boolean running = true;

        while (running) {
            String choice = showInputWindow(
                    "1. Show plants\n"
                            + "2. Water plant\n"
                            + "3. Add plant\n"
                            + "4. Remove plant\n"
                            + "5. Quit\n\n"
                            + "Enter your choice:",
                    WINDOW_TITLE);
            // Cancel or close the dialog to exit the program.
            if (choice == null) {
                running = false;
                continue;
            }

            switch (choice.trim()) {
                case "1" -> showPlants();
                case "2" -> waterPlant();
                case "3" -> addPlant();
                case "4" -> removePlant();
                case "5" -> {
                    showMessage("Exiting the program.");
                    running = false;
                }
                default -> showWarning( "Unknown option. Please choose 1-5.");
            }
        }

        plantManager.writePlants();
    }

    private void showPlants() {
        if (plantManager.getPlants().isEmpty()){
            showMessage("No plants registered.");
            return;
        }

        StringBuilder message = new StringBuilder("Plants:\n\n");
        for (Plant plant : plantManager.getPlants()) {
            message.append(plant).append("\n");
        }

        showMessage(message.toString());
    }

    private void waterPlant() {
        String name = readNonEmptyName("Which plant should receive liquid?");
        if (name == null) {
            return;
        }

        Waterable waterable = plantManager.findWaterable(name); //interface

        if (waterable == null) {
            showMessage("Plant not found.");
            return;
        }
        // Interface usage: interact with the object through the Waterable interface
        waterable.water();
    }

    private void addPlant() {
        String name = readUniquePlantName();
        if (name == null) {
            return;
        }

        PlantType plantType = choosePlantType();
        if (plantType == null) {
            return;
        }

        Double height = readHeight(name);
        if (height == null) {
            return;
        }

        Plant plant = createPlant(plantType, name, height);
        if (plantManager.addPlant(plant)) {
            showMessage("Plant added successfully.");
        }
    }

    private void removePlant() {
        String name = readNonEmptyName("Which plant should be removed?");
        if (name == null) {
            return;
        }

        if (plantManager.removePlant(name)){
            showMessage("Removing plant " + name);
            return;
        }
        showMessage("Plant not found");
    }

    private String readNonEmptyName(String prompt){
        while (true){
            String name = showInputWindow(prompt, "Plant name");

            if (name == null){
                return null;
            }

            name = name.trim();

            if (!name.isBlank()) {
                return name;
            }

            showWarning("Empty name is not allowed");
        }
    }

    private String readUniquePlantName(){
        while (true){
            String name = readNonEmptyName("Plant's name: ");

            if (name == null) {
                return null;
            }

            if (plantManager.findPlant(name) == null) {
                return name;
            }

            showWarning("A plant with this name already exists." +
                    " Please choose another name.");
        }
    }

    private PlantType choosePlantType(){
        StringBuilder plantTypesMenu = new StringBuilder(
                "Choose plant type:\n"
        );

        for (PlantType type : PlantType.values()) {
            plantTypesMenu.append(type.getPlantTypeId())
                    .append(" - ")
                    .append(type.getPlantTypeDesc())
                    .append("\n");
        }

        while (true) {
            String plantTypeIdString = showInputWindow(plantTypesMenu.toString(), "Plant type ID");

            if (plantTypeIdString == null) {
                return null;
            }

            try {
                int plantTypeId = Integer.parseInt(plantTypeIdString.trim());
                PlantType plantType = PlantType.fromId(plantTypeId);

                if (plantType != null) {
                   return plantType;
                }

                showWarning("Unknown plant id. Please choose 1-3.");
            } catch (NumberFormatException e) {
                showWarning("Invalid number format. Please enter int 1-3.");
            }
        }
    }

    private Double readHeight(String name){
        while (true) {
            String heightString = showInputWindow(name + "'s height in meters: ", "Height");

            if (heightString == null) {
                return null;
            }

            try {
                double height = Double.parseDouble(heightString.trim().replace(',', '.'));

                if (height > 0 && height <= Plant.MAX_HEIGHT){
                    return height;
                }

                showWarning("Height must be greater than 0 and at most "
                        + Plant.MAX_HEIGHT + " meters.");
            } catch (NumberFormatException e) {
                showWarning("Invalid number format. Please enter a numeric height.");
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

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,
                "Invalid input", JOptionPane.WARNING_MESSAGE);
    }

    private String showInputWindow(String message, String title){
        return JOptionPane.showInputDialog(null, message,
                title, JOptionPane.QUESTION_MESSAGE);
    }
}
