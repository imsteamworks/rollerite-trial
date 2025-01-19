package dev.fright.rollerite.command;

import dev.fright.rollerite.Locale;
import dev.fright.rollerite.handlers.TeleportHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class TpaCommand implements CommandExecutor {

    private final TeleportHandler teleportHandler;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Locale.PLAYER_ONLY.get());
            return true;
        }

        if (!sender.hasPermission("rollerite.tpa")) {
            sender.sendMessage(Locale.NO_PERMISSION.get());
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(Locale.PLAYER_NOT_SUPPLIED.get());
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null || !targetPlayer.isOnline()) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.get());
            return true;
        }

        if(this.teleportHandler.hasAlreadyRequested(player.getUniqueId(), targetPlayer.getUniqueId())) {
            sender.sendMessage(Locale.TELEPORT_REQUEST_ALREADY_REQUESTED.get(targetPlayer.getName()));
            return true;
        }

        this.teleportHandler.requestPlayer(player.getUniqueId(), targetPlayer.getUniqueId());
        sender.sendMessage(Locale.TELEPORT_REQUEST_SENT.get(targetPlayer.getName()));
        targetPlayer.sendMessage(Locale.TELEPORT_REQUEST_RECEIVED.get(player.getName()));
        return true;
    }

}
