
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class PlantManager {
    private static final Map<String,Plant> PLANTS = new HashMap<>();
    private static final String PLANTS_FILENAME = "greenestPlants.txt";

    public PlantManager(){
       readPlants();
       /* addPlant(new Cactus("Igge", 0.2));
        addPlant(new Palm("Laura", 5.0));
        addPlant(new Carnivorous("Meatloaf", 0.7));
        addPlant(new Palm("Olof", 1.0));*/
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

    public void readPlants() {
        ObjectMapper mapper = new ObjectMapper();

        List<Plant> plants = mapper.readValue(
                new File(PLANTS_FILENAME),
                new TypeReference<List<Plant>>() {}
        );

        PLANTS.clear();

        for (Plant plant : plants) {
            addPlant(plant);
        }

        System.out.println("Plants loaded: " + PLANTS.size());

    }

    public void writePlants() {
        ObjectMapper mapper = new ObjectMapper();
        Collection<Plant> plants = PLANTS.values();
        mapper.writerFor(new TypeReference<Collection<Plant>>() {})
                .withDefaultPrettyPrinter()
                .writeValue(new File(PLANTS_FILENAME), plants);
    }
}
