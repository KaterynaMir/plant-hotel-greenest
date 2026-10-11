import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Plant {

    private static final double MAX_HEIGHT = 10.0; // Maximum height allowed for plants in the hotel
    private final String name;
    private final double height; // in meters
    private LocalDateTime wateringDate;

    public Plant(String name, double height) {
        this.name = name;
        if (height > 0 && height <= MAX_HEIGHT){
            this.height = height;
        } else {
            throw new IllegalArgumentException("Illegal height," +
                    " should be  0 < height <= " + MAX_HEIGHT);
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
            System.out.printf("Watering plant %s" +
                    "\n  Type: %s" +
                    "\n  Liquid type: %s" +
                    "\n  Amount: %.2f l\n",
                    getName(),
                    getClass().getSimpleName(),
                    getLiquidType(),
                    calculateAmount()
                    );
        }
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + " " +
                name + ", " +
                height + " m";
    }
}
