package me.undeadguppy.vaults.managers;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

public class RandomLocation {

    public static Location getRandomLocation(World w) {
        double x = ThreadLocalRandom.current().nextDouble(-10000, 10000);
        double y = ThreadLocalRandom.current().nextDouble(100, 10000);
        double z = ThreadLocalRandom.current().nextDouble(-10000, 10000);
        Location loc = new Location(w, x, y, z);
        return loc;
    }


}
