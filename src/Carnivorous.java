public final class Carnivorous extends Plant {
    public Carnivorous(String name, double height) {
        super(name, height);
    }

    public Carnivorous(){}

    @Override
    public double calculateAmount() {
        return 0.1 + 0.2 * getHeight();
    }

    @Override
    public LiquidType determineLiquidType() {
        return LiquidType.PROTEIN_DRINK;
    }
}
