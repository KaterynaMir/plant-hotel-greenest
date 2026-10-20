public final class Palm extends Plant { //inheritance, child class

    //encapsulation
    private static final double LIQUID_PER_METER_HEIGHT_PER_DAY = 0.5; //liter

    public Palm(String name, double height) {
        super(name, height); //inheritance
    }
    public Palm(){
    }

    @Override //polymorphism
    public double calculateAmount() {
        return LIQUID_PER_METER_HEIGHT_PER_DAY * getHeight();
    }

    @Override //polymorphism
    public LiquidType determineLiquidType() {
        return LiquidType.TAP_WATER;
    }

}
