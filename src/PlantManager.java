import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlantManager {
    private static final Map<String,Plant> PLANTS = new HashMap<>();

    public PlantManager(){
        addPlant(new Cactus("Igge", 0.2));
        addPlant(new Palm("Laura", 5.0));
        addPlant(new Carnivorous("Meatloaf", 0.7));
        addPlant(new Palm("Olof", 1.0));
    }

    public boolean addPlant(Plant plant){
        String key = getKey(plant.getName());
        if (PLANTS.containsKey(key)){
            return false;
        }
        PLANTS.put(key, plant);
        return true;
    }

    public boolean removePlant(String name){
        String key = getKey(name);
        if (!PLANTS.containsKey(key)){
            return false;
        }
        PLANTS.remove(key);
        return true;
    }

    public Plant findPlant(String name){
        String key = getKey(name);
        return PLANTS.get(key);
    }

    public Collection<Plant> getPlants(){
        return PLANTS.values();
    }

    private String getKey(String name){
        return name.toUpperCase();
    }
}
