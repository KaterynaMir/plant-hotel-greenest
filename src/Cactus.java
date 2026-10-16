public final class Cactus extends Plant {

    public Cactus(String name, double height) {
        super(name, height);
    }

    public Cactus(){}

    @Override
    public double calculateAmount() {
        return 0.02;
    }

    @Override
    public LiquidType determineLiquidType() {
        return LiquidType.MINERAL_WATER;
    }
}
