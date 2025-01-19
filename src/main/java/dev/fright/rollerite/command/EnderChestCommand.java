package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return true;
        }

        if (!sender.hasPermission("rollerite.enderchest")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        Player targetPlayer = player;
        if (args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(Locale.PLAYER_NOT_FOUND.get());
                return true;
            }
        }

        player.openInventory(targetPlayer.getEnderChest());
        sender.sendMessage(Locale.ENDERCHEST_OPENED.get(targetPlayer.getName()));
        return true;
    }

}
