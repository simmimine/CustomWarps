package me.simmimine.customwarps.customwarps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private CustomWarps plugin;

    public WarpCommand(CustomWarps plugin) {
        this.plugin = plugin;
        plugin.getCommand("setwarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("console cannot issue this command.");
            return false;
        }
        Player p = (Player) sender;
        if (args.length != 1 || !label.equals("setwarp")) {
            p.sendMessage(ChatColor.RED + "Correct usage: /setwarp <warpname>");
            return false;
        }
        Location location = p.getLocation();
        String warpname = args[0].toLowerCase();
        if (plugin.getConfig().get(warpname) != null) {
            p.sendMessage(ChatColor.RED + "You already have a warp called: " + warpname);
            return false;
        }
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".World", p.getWorld().getName());
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".X", location.getX());
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".Y", location.getY());
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".Z", location.getZ());
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".Pitch", location.getPitch());
        plugin.getConfig().set(p.getUniqueId() + "." + warpname + ".Yaw", location.getYaw());
        plugin.saveConfig();
        p.sendMessage(ChatColor.GREEN + "Set a warp at " + location.getX() + "," + location.getY() + "," + location.getZ() + " called " + warpname);
        return true;
    }
}

