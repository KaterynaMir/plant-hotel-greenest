public class Plant {

    private String name;
    private double height; // in meters

    public Plant (String name, double height){
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public void waterPlant(){}
}
