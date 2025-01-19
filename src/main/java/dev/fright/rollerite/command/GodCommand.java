package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import dev.fright.rollerite.handlers.GodHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class GodCommand implements CommandExecutor {

    private final GodHandler handler;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("rollerite.god")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        Player targetPlayer = null;
        if (args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(Locale.PLAYER_NOT_FOUND.get());
                return true;
            }
        }

        if (targetPlayer == null) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Locale.PLAYER_NOT_SUPPLIED.get());
                return true;
            }

            targetPlayer = (Player) sender;
        }

        final boolean isGodModeEnabled = handler.isInGodMode(targetPlayer.getUniqueId());
        handler.toggleGodMode(targetPlayer.getUniqueId());

        final String status = isGodModeEnabled ? "disabled" : "enabled";
        targetPlayer.sendMessage(Locale.GOD_STATUS_TARGET.get(status));
        if (!targetPlayer.equals(sender))
            sender.sendMessage(Locale.GOD_STATUS_EXECUTOR.get(status, targetPlayer.getName()));

        return true;
    }

}
