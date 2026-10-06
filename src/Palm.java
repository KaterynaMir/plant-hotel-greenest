public class Palm extends Plant{

    public Palm(String name, double height) {
        super(name, height);
    }

    @Override
    public double calculateAmount() {
        return 0.5 * getHeight();
    }

    @Override
    public LiquidType getLiquidType() {
        return LiquidType.TAP_WATER;
    }

    @Override
    public void waterPlant() {

    }
}
