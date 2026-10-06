public abstract class Plant {

    private final String name;
    private final double height; // in meters

    public Plant(String name, double height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public abstract double calculateAmount();

    public abstract LiquidType getLiquidType();

    public void waterPlant() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Watering plant: " + getName() +
                "\nType: " + getClass() +
                "\nLiquid type: " + getLiquidType() +
                "\nAmount: " + calculateAmount() + " l");
        System.out.println("-------------------------------------------------------");
    }
}
