public final class Palm extends Plant {

    public Palm(String name, double height) {
        super(name, height);
    }
    public Palm(){
    }

    @Override
    public double calculateAmount() {
        return 0.5 * getHeight();
    }

    @Override
    public LiquidType determineLiquidType() {
        return LiquidType.TAP_WATER;
    }

}
