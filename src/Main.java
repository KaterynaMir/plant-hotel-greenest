import java.util.*;


public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        SCANNER.useLocale(Locale.US);

        PlantManager plantManager = new PlantManager();
        Menu menu = new Menu(SCANNER, plantManager);
        menu.run();

        SCANNER.close();
    }


}
