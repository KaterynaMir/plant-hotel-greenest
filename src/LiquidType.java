public enum LiquidType {
    TAP_WATER ("Tap water"),
    MINERAL_WATER ("Mineral water"),
    PROTEIN_DRINK ("Protein drink");

    private final String desc;

    LiquidType(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
