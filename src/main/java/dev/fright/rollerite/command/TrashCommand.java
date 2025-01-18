package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return false;
        }

        if (!sender.hasPermission("rollerite.trash")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return false;
        }

        Inventory trashInventory = Bukkit.createInventory(null, 27, "Trash");
        player.openInventory(trashInventory);
        sender.sendMessage(Locale.TRASH_OPENED.get());

        return true;
    }

}
