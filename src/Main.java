import java.util.ArrayList;

public class Main {
    static void main(String[] args) {

        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Cactus("Igge", 0.2));
        plants.add(new Palm("Laura", 5.0));
        plants.add(new Carnivorous("Meatloaf", 0.7));
        plants.add(new Palm("Olof", 1.0));

        for (Plant plant : plants) {
            plant.waterPlant();
        }

    }
}
