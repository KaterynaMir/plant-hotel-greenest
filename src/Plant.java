import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Plant {

    private final String name;
    private final double height; // in meters
    private LocalDateTime wateringDate;

    public Plant(String name, double height) {
        this.name = name;
        if (height > 0){
            this.height = height;
        } else {
            throw new IllegalArgumentException("Illegal height should be > 0");
        }
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public abstract double calculateAmount();

    public abstract LiquidType getLiquidType();

    public void water() {
        LocalDateTime now = LocalDateTime.now();
        if (wateringDate!=null && wateringDate.plusDays(1).isAfter(now)){
            long minToWatering = ChronoUnit.MINUTES.between(now, wateringDate.plusDays(1));
            System.out.println("Plant is already watered. Should be watered again in "
                    + minToWatering / 60 + " hours and "
                    + minToWatering % 60 + " minutes.");
        } else {
            wateringDate = LocalDateTime.now();
            System.out.println("Watering plant " + getName() +
                    "\nType: " + getClass() +
                    "\nLiquid type: " + getLiquidType() +
                    "\nAmount: " + calculateAmount() + " l");
        }
    }

    @Override
    public String toString(){
        return getClass() + ": " +
                name + ", " +
                height + " m";
    }
}
