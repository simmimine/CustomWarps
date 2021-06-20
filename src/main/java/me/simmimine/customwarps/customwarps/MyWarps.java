package me.simmimine.customwarps.customwarps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MyWarps implements CommandExecutor {

    private CustomWarps plugin;

    public MyWarps(CustomWarps plugin) {
        this.plugin = plugin;
        plugin.getCommand("mywarps").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("console cannot issue this command");
            return false;
        }
        Player p = (Player) sender;
        if (args.length != 0 || !(label.equals("mywarps"))) {
            p.sendMessage("Correct usage: /mywarps");
            return false;
        }
        p.sendMessage(ChatColor.GREEN + "Your warps: ".concat(configReader(p.getUniqueId())));
        return true;
    }
    
    private String configReader(UUID p) {
        List rtnList = new ArrayList<>();
        for (String x : plugin.getConfig().getConfigurationSection(p.toString()).getKeys(false)) {
            if (x == null) continue;
            rtnList.add(x);
        }
        StringBuilder s=new StringBuilder();
        for(Object str : rtnList) {
            s.append(str);
            s.append(", ");
        }
        s.setLength(s.length()-2);
        String str=s.toString();
        return str;
    }
}