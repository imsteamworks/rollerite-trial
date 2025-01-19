package dev.fright.rollerite;

import dev.fright.rollerite.command.*;
import dev.fright.rollerite.handlers.GodHandler;
import dev.fright.rollerite.handlers.TeleportHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrialPlugin extends JavaPlugin {

    private GodHandler godHandler;
    private TeleportHandler teleportHandler;

    @Override
    public void onEnable() {
        this.godHandler = new GodHandler();
        this.teleportHandler = new TeleportHandler();

        this.saveDefaultConfig();
        Locale.init(this.getConfig());

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
        getCommand("tpa").setExecutor(new TpaCommand(this.teleportHandler));
        getCommand("tpaaccept").setExecutor(new TpaAcceptCommand(this.teleportHandler));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(this.godHandler, this);
    }

}
