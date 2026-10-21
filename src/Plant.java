import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)

// abstract class implements Waterable interface
public abstract sealed class Plant implements Waterable permits Cactus, Carnivorous, Palm {

    public static final double MAX_HEIGHT = 10.0; // Maximum height allowed for plants in the hotel

    //encapsulation
    private String name;
    private double height; // in meters
    private LocalDateTime wateringDate;


    public Plant(String name, double height) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Empty name is not allowed.");
        }
        this.name = name;

        if (height > 0 && height <= MAX_HEIGHT){
            this.height = height;
        } else {
            throw new IllegalArgumentException(
                    "Illegal height, should be  0 < height <= " + MAX_HEIGHT);
        }
    }

    public Plant(String name, double height, LocalDateTime wateringDate){
        this(name, height);
        if (wateringDate!= null && wateringDate.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Illegal watering date: watering date is in future");
        }
        this.wateringDate = wateringDate;
    }

    public Plant() {

    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public LocalDateTime getWateringDate() {
        return wateringDate;
    }

    public abstract double calculateAmount();

    public abstract LiquidType determineLiquidType();

    @Override
    public void water() {
        LocalDateTime now = LocalDateTime.now();
        if (wateringDate!=null && wateringDate.plusDays(1).isAfter(now)){
            long minToWatering = ChronoUnit.MINUTES.between(now, wateringDate.plusDays(1));

            JOptionPane.showMessageDialog(null,
                    "Plant is already watered. Should be watered again in "
                    + minToWatering / 60 + " hours and "
                    + minToWatering % 60 + " minutes.");
        } else {
            wateringDate = LocalDateTime.now();
            String message = String.format(
                    Locale.US,
                    "Watering plant %s:" +
                    "\n    Type: %s" +
                    "\n    Liquid type: %s" +
                    "\n    Amount: %.2f l\n",
                    getName(),
                    getClass().getSimpleName(),
                    determineLiquidType().getDesc(),
                    calculateAmount()
            );

            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + " " +
                name + ", " +
                height + " m";
    }
}
