
public class Main {

    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();
        Menu menu = new Menu(plantManager);
        menu.run();
    }
}
