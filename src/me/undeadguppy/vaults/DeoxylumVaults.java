package me.undeadguppy.vaults;

import me.undeadguppy.vaults.commands.Createvault;
import me.undeadguppy.vaults.commands.Vault;
import me.undeadguppy.vaults.managers.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class DeoxylumVaults extends JavaPlugin {

    private static DeoxylumVaults main;

    public static DeoxylumVaults getInstance() {
        return main;
    }

    public void onEnable() {
        main = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        DataManager.getInstance().setup(this);
        if (!DataManager.getInstance().getYaml().contains("vaults")) {
            DataManager.getInstance().getYaml().createSection("vaults");
            DataManager.getInstance().save();
        }
        getCommand("createvault").setExecutor(new Createvault());
        getCommand("vault").setExecutor(new Vault());
    }

    public Location deserializeLocation(String path) {
        return new Location(Bukkit.getWorld(getConfig().getString("vault-world")),
                DataManager.getInstance().getYaml().getDouble("vaults." + path + ".x"),
                DataManager.getInstance().getYaml().getDouble("vaults." + path + ".y"),
                DataManager.getInstance().getYaml().getDouble("vaults." + path + ".z"));
    }

}
