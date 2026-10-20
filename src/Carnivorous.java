public final class Carnivorous extends Plant { //inheritance, child class

    //encapsulation
    private static final double LIQUID_PER_DAY_BASE_AMOUNT = 0.1; //liter
    private static final double LIQUID_PER_METER_HEIGHT_PER_DAY = 0.2; //liter

    public Carnivorous(String name, double height) {
        super(name, height); //inheritance
    }

    public Carnivorous(){}

    @Override //polymorphism
    public double calculateAmount() {
        return LIQUID_PER_DAY_BASE_AMOUNT + LIQUID_PER_METER_HEIGHT_PER_DAY * getHeight();
    }

    @Override //polymorphism
    public LiquidType determineLiquidType() {
        return LiquidType.PROTEIN_DRINK;
    }
}
