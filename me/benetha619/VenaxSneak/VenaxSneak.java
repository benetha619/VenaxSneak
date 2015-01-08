package me.benetha619.VenaxSneak;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VenaxSneak extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin has succesfully been enabled!");
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has succesfully been disabled!");
    }

    public static boolean isSneaking = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                    String[] args) {

    if (sender instanceof Player) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("sneakon")) {
                if (player.hasPermission("venaxsneak.sneak")) {
                    if (!isSneaking) {
                        player.setSneaking(true);
                        player.sendMessage(ChatColor.GOLD
                                        + "You are now sneaking!");
                        isSneaking = true;
                    } else if (isSneaking) {
                        player.sendMessage(ChatColor.RED
                                            + "You are already sneaking!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED
                                    + "You do not have permission to use this command!");
                }
            }
            if (cmd.getName().equalsIgnoreCase("sneakoff")) {
                if (player.hasPermission("venaxsneak.sneakoff")) {
                    if (isSneaking) {
                        player.setSneaking(false);
                        player.sendMessage(ChatColor.GOLD
                                        + "You are no longer sneaking!");
                        isSneaking = false;
                    } else if (!isSneaking) {
                        player.sendMessage(ChatColor.RED
                                        + "You aren't sneaking!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED
                                    + "You do not have permission to use this command!");
                }
            }
        } else if (args.length >= 1) {
                player.sendMessage(ChatColor.RED + "Too many argumants!");
                player.sendMessage(ChatColor.RED + "/sneakon or /sneakoff");
        }
    }

    return false;
    }
}
