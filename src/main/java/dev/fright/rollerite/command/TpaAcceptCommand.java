package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import dev.fright.rollerite.handlers.TeleportHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@RequiredArgsConstructor
public class TpaAcceptCommand implements CommandExecutor {

    private final TeleportHandler teleportHandler;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // create command with syntax:
        // /tpaccept sender must be a player and it will use the teleport handler to get the most recent request
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return true;
        }

        if (!sender.hasPermission("rollerite.tpa")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        final UUID mostRecentRequestUuid = this.teleportHandler.pop(player.getUniqueId());
        if (mostRecentRequestUuid == null) {
            player.sendMessage(Locale.NO_TELEPORT_REQUESTS.get());
            return true;
        }

        final Player onlinePlayer = Bukkit.getPlayer(mostRecentRequestUuid);
        if (onlinePlayer == null || !onlinePlayer.isOnline()) {
            player.sendMessage(Locale.TELEPORT_REQUEST_FAILED.get());
            return true;
        }

        if (!onlinePlayer.teleport(player, PlayerTeleportEvent.TeleportCause.PLUGIN)) {
            player.sendMessage(Locale.TELEPORT_REQUEST_FAILED.get());
            return true;
        }

        sender.sendMessage(Locale.TELEPORT_REQUEST_ACCEPTED_SELF.get(onlinePlayer.getName()));
        onlinePlayer.sendMessage(Locale.TELEPORT_REQUEST_ACCEPTED_OTHER.get(sender.getName()));
        return true;
    }

}
