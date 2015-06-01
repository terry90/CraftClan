package fr.insynia.craftclan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Doc on 13/05/2015.
 */
public class UtilCC {

    public static int getFloorY(int x, int z) {
        Location loc = new Location(Bukkit.getWorld(MapState.DEFAULT_WORLD), x, 0, z);
        int y = loc.getWorld().getMaxHeight();

        while (y >= loc.getWorld().getSeaLevel()) {
            if (checkFloorBlock(loc.getWorld().getBlockAt(x, y, z).getType()))
                return y;
            y--;
        }
        return y;
    }

    private static boolean checkFloorBlock(Material type) {
        if (type != Material.AIR && type != Material.SNOW &&
                type != Material.LEAVES && type != Material.LEAVES_2 &&
                type != Material.LONG_GRASS && type != Material.RED_MUSHROOM &&
                type != Material.BROWN_MUSHROOM && type != Material.YELLOW_FLOWER &&
                type != Material.LEAVES && type != Material.LOG && type != Material.LOG_2)
            return true;
        return false;
    }

    // Distance with Y axis
    public static int distanceBasicFull(Location from, Location to) {
        if (to == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else if(to.getWorld() != null && from.getWorld() != null) {
            if(to.getWorld() != from.getWorld()) {
                throw new IllegalArgumentException("Cannot measure distance between " + from.getWorld().getName() + " and " + to.getWorld().getName());
            } else {
                int xDist = Math.abs((int)(from.getX()) - (int)(to.getX()));
                int zDist = Math.abs((int)(from.getZ()) - (int)(to.getZ()));
                int yDist = Math.abs((int)(from.getY()) - (int)(to.getY()));
                return (xDist > zDist ? (xDist > yDist ? xDist : yDist) : (zDist > yDist ? zDist : yDist));
            }
        } else {
            throw new IllegalArgumentException("Cannot measure distance to a null world");
        }
    }

    // Distance without Y axis
    public static int distanceBasic(Location from, Location to) {
        if (to == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else if(to.getWorld() != null && from.getWorld() != null) {
            if(to.getWorld() != from.getWorld()) {
                throw new IllegalArgumentException("Cannot measure distance between " + from.getWorld().getName() + " and " + to.getWorld().getName());
            } else {
                int xDist = Math.abs((int)(from.getX()) - (int)(to.getX()));
                int zDist = Math.abs((int)(from.getZ()) - (int)(to.getZ()));
                return (xDist > zDist ? xDist : zDist);
            }
        } else {
            throw new IllegalArgumentException("Cannot measure distance to a null world");
        }
    }

    // Check capture time
    public static void timeLeftCapture(int time, Player p, PlayerCC pcc) {
        if (time <= 10) p.sendMessage("Il vous reste " + time + " secondes pour capturer le point !");
        else if (time <= 60 && time % 5 == 0) p.sendMessage("Il vous reste " + time + " secondes pour capturer le point !");
        else if (time > 60 && time % 10 == 0) p.sendMessage("Il vous reste " + time + " secondes pour capturer le point !");
    }

    // Check if an string arg is an integer
    public static boolean checkArgIsInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if a color exists
    public static boolean checkColor(String color) {
        for (ChatColor c : ChatColor.values())
            if (c.name().equals(color)) return true;
        return false;
    }

    // Check if a faction exists
    public static boolean checkFactionExists(String factionName) {
        List<Faction> factions = MapState.getInstance().getFactions();
        for (Faction f : factions) if (factionName.equalsIgnoreCase(f.getName())) return true;
        return false;
    }

    // Check if a point exists
    public static boolean checkPointExists(String pointName) {
        List<Point> points = MapState.getInstance().getPoints();
        for (Point p : points) if (pointName.equalsIgnoreCase(p.getName())) return true;
        return false;
    }

    // Check args count
    public static boolean checkArgsChatCommand(String[] args, int estimatedArgs) {
        return args.length == estimatedArgs + 1;
    }

    // Récupère la partie entière, du nombre divisé par 2
    public static int halfRound(int toHalfRound) {
        return Math.round(toHalfRound / 2);
    }

    public static int getGlassMetadataColor(String gcolor) {
        switch (gcolor.toLowerCase()) {
            case "white":
                return 0;
            case "gold":
                return 1;
            case "dark_purple":
                return 2;
            case "aqua":
                return 3;
            case "yellow":
                return 4;
            case "green":
                return 5;
            case "red":
                return 6;
            case "dark_gray":
                return 7;
            case "gray":
                return 8;
            case "dark_aqua":
                return 9;
            case "light_purple":
                return 10;
            case "dark_blue":
                return 11;
            case "dark_green":
                return 13;
            case "dark_red":
                return 14;
            case "black":
                return 15;
            case "blue":
                return 11;
            default:
                return -1;
        }
    }

    public static int getInt(double toInt) {
        return (toInt < 0 ? (int)toInt - 1 : (int)toInt);
    }
}
