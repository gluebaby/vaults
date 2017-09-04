package me.undeadguppy.vaults.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private static DataManager m;
    File f;
    FileConfiguration file;

    public static DataManager getInstance() {
        if (m == null) {
            m = new DataManager();
        }
        return m;
    }

    public void save() {
        try {
            file.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }

        f = new File(p.getDataFolder(), "vaults.yml");

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        file = YamlConfiguration.loadConfiguration(f);
    }

    public FileConfiguration getYaml() {
        return file;
    }


}
