package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return false;
        }

        if (!sender.hasPermission("rollerite.openinventory")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return false;
        }

        Player targetPlayer = player;
        if (args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(Locale.PLAYER_NOT_FOUND.get());
                return false;
            }
        }

        player.openInventory(targetPlayer.getInventory());
        sender.sendMessage(Locale.INVENTORY_OPENED.get(targetPlayer.getName()));
        return true;}

}
