package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public class FixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return true;
        }

        if (!sender.hasPermission("rollerite.fix")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType().equals(Material.AIR) || !itemInHand.getType().isItem()) {
            sender.sendMessage(Locale.ITEM_NOT_HELD.get());
            return true;
        }

        if (!(itemInHand.getItemMeta() instanceof Damageable damageableItemMeta)) {
            sender.sendMessage(Locale.ITEM_NOT_FIXABLE.get());
            return true;
        }

        damageableItemMeta.setDamage(0);
        itemInHand.setItemMeta(damageableItemMeta);
        sender.sendMessage(Locale.ITEM_FIXED.get());
        return true;
    }
}