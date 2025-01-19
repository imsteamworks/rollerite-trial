package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
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
        if (!sender.hasPermission("rollerite.gamemode")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(Locale.USAGE.get(label, "<type> [player]"));
            return true;
        }

        GameMode providedGameMode;
        try {
            providedGameMode = GameMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Locale.GAMEMODE_INVALID.get());
            return true;
        }

        Player providedTarget;
        if (args.length > 1) {
            providedTarget = Bukkit.getPlayer(args[1]);
            if (providedTarget == null) {
                sender.sendMessage(Locale.PLAYER_NOT_FOUND.get());
                return true;
            }

            providedTarget.setGameMode(providedGameMode);
            sender.sendMessage(Locale.GAMEMODE_TARGET.get(providedTarget.getName(), providedGameMode.toString().toLowerCase()));
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return true;
        }

        ((Player) sender).setGameMode(providedGameMode);
        sender.sendMessage(Locale.GAMEMODE_SELF.get(providedGameMode.toString().toLowerCase()));
        return true;
    }
}
