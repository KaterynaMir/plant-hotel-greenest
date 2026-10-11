public enum PlantType {
    PALM (1, "Palm"),
    CARNIVOROUS (2, "Carnivorous"),
    CACTUS (3, "Cactus");

    private final int plantTypeId;
    private final String plantTypeDesc;

    PlantType(int plantTypeId, String plantTypeDesc){
        this.plantTypeId = plantTypeId;
        this.plantTypeDesc = plantTypeDesc;
    }

    public int getPlantTypeId() {
        return plantTypeId;
    }

    public String getPlantTypeDesc() {
        return plantTypeDesc;
    }

    public static PlantType fromId(int id){
        for (PlantType type: values()){
            if (type.getPlantTypeId() == id){
                return type;
            }
        }
        return null;
    }
}
