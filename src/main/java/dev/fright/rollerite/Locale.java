package dev.fright.rollerite;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public enum Locale {

    USAGE,
    NO_PERMISSION,
    PLAYER_ONLY,
    PLAYER_NOT_FOUND,
    PLAYER_NOT_SUPPLIED,
    ENDERCHEST_OPENED,
    INVENTORY_OPENED,
    TRASH_OPENED,
    ITEM_NOT_HELD,
    ITEM_NOT_FIXABLE,
    ITEM_FIXED,
    GAMEMODE_TARGET,
    GAMEMODE_SELF,
    GAMEMODE_INVALID,
    GOD_STATUS_TARGET,
    GOD_STATUS_EXECUTOR,
    NO_TELEPORT_REQUESTS,
    TELEPORT_REQUEST_SENT,
    TELEPORT_REQUEST_RECEIVED,
    TELEPORT_REQUEST_ACCEPTED_SELF,
    TELEPORT_REQUEST_ACCEPTED_OTHER,
    TELEPORT_REQUEST_ALREADY_REQUESTED,
    TELEPORT_REQUEST_FAILED;

    private static FileConfiguration configuration;

    /**
     * Initialize configuration file used for localization.
     *
     * @param configuration Configuration file.
     */
    static void init(final @NotNull FileConfiguration configuration) {
        Locale.configuration = configuration;
    }

    public String get(final Object... args) {
        if (configuration == null)
            return "Locale configuration not initialized.";

        String message = configuration.getString("locale." + this.name().toLowerCase());
        if (message == null)
            return "Locale message not found.";

        return ChatColor.translateAlternateColorCodes('&', MessageFormat.format(message, args));
    }

}
