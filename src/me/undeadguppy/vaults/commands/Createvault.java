package me.undeadguppy.vaults.commands;

import me.undeadguppy.vaults.DeoxylumVaults;
import me.undeadguppy.vaults.managers.DataManager;
import me.undeadguppy.vaults.managers.RandomLocation;
import me.undeadguppy.vaults.managers.SchematicManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Createvault implements CommandExecutor {

    private void serializeLocation(String path, Location loc) {
        DataManager.getInstance().getYaml().set("vaults." + path + ".world", loc.getWorld());
        DataManager.getInstance().getYaml().set("vaults." + path + ".x", loc.getX());
        DataManager.getInstance().getYaml().set("vaults." + path + ".y", loc.getY());
        DataManager.getInstance().getYaml().set("vaults." + path + ".z", loc.getZ());
        DataManager.getInstance().save();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createvault")) {
            if (sender.hasPermission("deoxylumvaults.createvault")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Please specify a player!");
                    return true;
                }
                String playerName = args[0];
                if (DataManager.getInstance().getYaml().contains("vaults." + playerName)) {
                    sender.sendMessage(ChatColor.RED + playerName + " already has a vault!");
                    return true;
                }
                // Create them a vault
                serializeLocation(playerName, RandomLocation.getRandomLocation(
                        Bukkit.getWorld(DeoxylumVaults.getInstance().getConfig().getString("vault-world"))));

                if (Bukkit.getPlayer(playerName) != null) {
                    Player p = Bukkit.getPlayer(playerName);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            DeoxylumVaults.getInstance().getConfig().getString("vault-created")));
                }
                // Create vault
                File file = new File(DeoxylumVaults.getInstance().getDataFolder(), DeoxylumVaults.getInstance().getConfig().getString("vault-schematic"));
                try {
                    SchematicManager.pasteSchematic(Bukkit.getWorld(DeoxylumVaults.getInstance().getConfig().getString("vault-world")), DeoxylumVaults.getInstance().deserializeLocation(playerName), SchematicManager.loadSchematic(file));
                } catch (IOException e) {
                    e.printStackTrace();
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        DeoxylumVaults.getInstance().getConfig().getString("no-permission")));
                return true;
            }
        }
        return true;
    }

}
