public final class Cactus extends Plant { //inheritance, child class

    //encapsulation
    private static final double LIQUID_PER_DAY = 0.02; //liter

    public Cactus(String name, double height) {
        super(name, height); //inheritance
    }

    public Cactus(){}

    @Override //polymorphism
    public double calculateAmount() {
        return LIQUID_PER_DAY;
    }

    @Override //polymorphism
    public LiquidType determineLiquidType() {
        return LiquidType.MINERAL_WATER;
    }
}
