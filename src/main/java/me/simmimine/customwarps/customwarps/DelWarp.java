package me.simmimine.customwarps.customwarps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {

    private CustomWarps plugin;

    public DelWarp(CustomWarps plugin) {
        this.plugin = plugin;
        plugin.getCommand("delwarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("console cannot issue this command");
            return false;
        }
        Player p = (Player) sender;
        if (args.length != 1 || !label.equals("delwarp")) {
            p.sendMessage(ChatColor.RED + "Correct usage: /delwarp <warpname>");
            return false;
        }
        String warpname = args[0].toLowerCase();
        if(plugin.getConfig().get(p.getUniqueId() + "." + warpname)==null) {
            p.sendMessage(ChatColor.RED + "You do not have a warp with that name.");
            return false;
        }
        plugin.getConfig().set(p.getUniqueId() + "." + warpname, null);
        plugin.saveConfig();
        p.sendMessage(ChatColor.GREEN + "Deleted the warp called " + warpname);
        return true;
    }
}
