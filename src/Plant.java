

//import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)

/*
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cactus.class, name = "cactus"),
        @JsonSubTypes.Type(value = Carnivorous.class, name = "carnivorous"),
        @JsonSubTypes.Type(value = Palm.class, name = "palm")
})*/

public abstract sealed class Plant permits Cactus, Carnivorous, Palm {

    private static final double MAX_HEIGHT = 10.0; // Maximum height allowed for plants in the hotel
    private String name;
    private double height; // in meters
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

    public void water() {
        LocalDateTime now = LocalDateTime.now();
        if (wateringDate!=null && wateringDate.plusDays(1).isAfter(now)){
            long minToWatering = ChronoUnit.MINUTES.between(now, wateringDate.plusDays(1));
            System.out.println("Plant is already watered. Should be watered again in "
                    + minToWatering / 60 + " hours and "
                    + minToWatering % 60 + " minutes.");
        } else {
            wateringDate = LocalDateTime.now();
            System.out.printf(Locale.US,"Watering plant %s" +
                    "\n  Type: %s" +
                    "\n  Liquid type: %s" +
                    "\n  Amount: %.2f l\n",
                    getName(),
                    getClass().getSimpleName(),
                    determineLiquidType(),
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
