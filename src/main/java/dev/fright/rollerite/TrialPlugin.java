package dev.fright.rollerite;

import dev.fright.rollerite.command.*;
import dev.fright.rollerite.handlers.GodHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class TrialPlugin extends JavaPlugin {

    private GodHandler godHandler;

    @Override
    public void onEnable() {
        this.godHandler = new GodHandler();

        this.registerListeners();
        this.registerCommands();
    }

    private void registerCommands() {
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("fix").setExecutor(new FixCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("god").setExecutor(new GodCommand(this.godHandler));
        getCommand("openinventory").setExecutor(new OpenInventoryCommand());
        getCommand("trash").setExecutor(new TrashCommand());
    }
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(this.godHandler, this);
    }

}
