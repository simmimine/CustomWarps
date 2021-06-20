package me.simmimine.customwarps.customwarps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Supplier;

public class Warp implements CommandExecutor {

    private CustomWarps plugin;

    public Warp(CustomWarps plugin) {
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("console cannot issue this command");
            return false;
        }
        Player p = (Player) sender;
        if (args.length != 1 || !label.equals("warp")) {
            p.sendMessage(ChatColor.RED + "Correct usage: /warp <warpname>");
            return false;
        }
        String warpname = args[0].toLowerCase();
        if (plugin.getConfig().get(p.getUniqueId() + "." + warpname + ".X")==null) {
            p.sendMessage(ChatColor.RED + "You do not have a warp called " + warpname);
            return false;
        }
        double x = plugin.getConfig().getDouble(p.getUniqueId() + "." + warpname + ".X");
        double y = plugin.getConfig().getDouble(p.getUniqueId() + "." + warpname + ".Y");
        double z = plugin.getConfig().getDouble(p.getUniqueId() + "." + warpname + ".Z");
        float pitch = (float) plugin.getConfig().getDouble(p.getUniqueId() + "." + warpname + ".Pitch");
        float yaw = (float) plugin.getConfig().getDouble(p.getUniqueId() + "." + warpname + ".Yaw");
        String world = plugin.getConfig().getString(p.getUniqueId() + "." + warpname + ".World");
        Location location = new Location(Bukkit.getWorld(world),x,y,z,pitch,yaw);
        p.teleport(location);
        p.sendMessage(ChatColor.GREEN + "You have been warped to " + warpname);
        return true;
    }
}
