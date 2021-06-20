package me.simmimine.customwarps.customwarps;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginCommand command = getCommand("setwarp");
        command.setExecutor(new WarpCommand(this));
        PluginCommand delwarp = getCommand("delwarp");
        delwarp.setExecutor(new DelWarp(this));
        PluginCommand warp = getCommand("warp");
        warp.setExecutor(new Warp(this));
        PluginCommand warps = getCommand("mywarps");
        warps.setExecutor(new MyWarps(this));
        loadConfig();
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
