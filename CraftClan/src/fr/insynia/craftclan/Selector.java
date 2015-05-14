package fr.insynia.craftclan;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Doc on 13/05/2015.
 */
public class Selector {
    private static final String DEFAULT_WORLD = "world";
    private static Selector instance;
    private static Location firstPoint = null;
    private static Location secondPoint = null;

    protected Selector() {
    }

    public static Selector getInstance() {
        if(instance == null) {
            instance = new Selector();
        }
        return instance;
    }

    public static void addPoint(double x, double y, double z) {
        Location loc = new Location(Bukkit.getWorld(DEFAULT_WORLD), (int) x, (int) y, (int) z);
        if (secondPoint == null) {
            if (firstPoint == null) {
                setFirstPoint(loc);
                secondPoint = null;
            } else {
                setSecondPoint(loc);
            }
        } else {
            setFirstPoint(loc);
        }
    }

    public static void setFirstPoint(Location firstPoint) {
        Selector.firstPoint = firstPoint;
    }

    public static void setSecondPoint(Location secondPoint) {
        Selector.secondPoint = secondPoint;
    }

    public static void saveStructure(String filename) {
        BlockSpawner.saveStructure(filename, firstPoint, secondPoint);
    }
}