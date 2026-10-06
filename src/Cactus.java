public class Cactus extends Plant {

    public Cactus(String name, double height) {
        super(name, height);
    }

    @Override
    public double calculateAmount() {
        return 0.02;
    }

    @Override
    public LiquidType getLiquidType() {
        return LiquidType.MINERAL_WATER;
    }
}
