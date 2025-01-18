package dev.fright.rollerite.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("rollerite.gamemode")) {
            sender.sendMessage("You do not have permission to use this command.");
            return false;
        }

        if(args.length < 1) {
            sender.sendMessage("Usage: /gamemode <type> [player]");
            return false;
        }

        GameMode providedGameMode;
        try {
            providedGameMode = GameMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("Invalid gamemode type.");
            return false;
        }

        Player providedTarget;
        if(args.length > 1) {
            providedTarget = Bukkit.getPlayer(args[1]);
            if(providedTarget == null) {
                sender.sendMessage("Player not found.");
                return false;
            }

            providedTarget.setGameMode(providedGameMode);
            sender.sendMessage("Set " + providedTarget.getName() + "'s gamemode to " + providedGameMode.toString().toLowerCase() + ".");
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to change your own gamemode.");
            return false;
        }

        ((Player) sender).setGameMode(providedGameMode);
        sender.sendMessage("Set your gamemode to " + providedGameMode.toString().toLowerCase() + ".");
        return true;
    }
}
