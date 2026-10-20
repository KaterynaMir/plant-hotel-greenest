
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class PlantManager {

    private final String PLANTS_FILENAME = "greenestPlants.txt";

    // Map interface with a HashMap implementation.
    // Polymorphism: objects of different Plant subclasses can be stored as values in the same map.
    private final Map<String,Plant> plants = new HashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();

    public PlantManager() {
       readPlants();
       /* addPlant(new Cactus("Igge", 0.2));
        addPlant(new Palm("Laura", 5.0));
        addPlant(new Carnivorous("Meatloaf", 0.7));
        addPlant(new Palm("Olof", 1.0));*/
    }

    public boolean addPlant(Plant plant) {
        String key = getKey(plant.getName());
        return plants.putIfAbsent(key, plant) == null;
    }

    public boolean removePlant(String name){
        String key = getKey(name);
        if (!plants.containsKey(key)){
            return false;
        }
        plants.remove(key);
        return true;
    }

    public Plant findPlant(String name){
        String key = getKey(name);
        return plants.get(key);
    }

    public Waterable findWaterable(String name) {
        return findPlant(name);
    }

    public Collection<Plant> getPlants(){
        return List.copyOf(plants.values());
    }

    private String getKey(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Plant name cannot be empty.");
        }
        return name.toUpperCase(Locale.ROOT);
    }

    private void readPlants() {
        try {
            List<Plant> loadedPlants = mapper.readValue(
                    new File(PLANTS_FILENAME),
                    new TypeReference<List<Plant>>() {}
            );

            plants.clear();

            for (Plant plant : loadedPlants) {
                addPlant(plant);
            }

            System.out.println("Plants loaded: " + plants.size());
        } catch (JacksonException e) {
            System.out.println("Could not load plants: " + e.getMessage());
        }
    }

    public void writePlants() {
        Collection<Plant> plantsToWrite = plants.values();
        try {
            mapper.writerFor(new TypeReference<Collection<Plant>>() {
                    })
                    .withDefaultPrettyPrinter()
                    .writeValue(new File(PLANTS_FILENAME), plantsToWrite);
        } catch (JacksonException e){
            System.out.println("Could not write plants to the file: " + e.getMessage());
        }
    }
}
