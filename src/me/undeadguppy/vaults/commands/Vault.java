package me.undeadguppy.vaults.commands;

import me.undeadguppy.vaults.DeoxylumVaults;
import me.undeadguppy.vaults.managers.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vault implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("vault")) {
            Player p = (Player) sender;
            if (DataManager.getInstance().getYaml().get("vaults." + p.getName()) == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        DeoxylumVaults.getInstance().getConfig().getString("no-vault")));
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    DeoxylumVaults.getInstance().getConfig().getString("entered-vault")));
            p.teleport(DeoxylumVaults.getInstance().deserializeLocation(p.getName()));
            return true;
        }
        return true;
    }

}
