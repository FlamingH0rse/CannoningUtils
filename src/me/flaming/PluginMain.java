package me.flaming;

import me.flaming.commands.*;
import me.flaming.events.EntityExplodeListener;
import me.flaming.events.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
    private static PluginMain plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        getCommand("tntfill").setExecutor(new TntFillCommand(this));
        getCommand("tntclear").setExecutor(new TntClearCommand(this));
        getCommand("button").setExecutor(new ButtonCommand(this));
        getCommand("lever").setExecutor(new LeverCommand(this));
        getCommand("sandwand").setExecutor(new SandwandCommand(this));


        getServer().getPluginManager().registerEvents(new EntityExplodeListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);

        getLogger().info("CannoningUtils is enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CannoningUtils is disabled");
    }

    public static PluginMain getPlugin() {
        return plugin;
    }
}
