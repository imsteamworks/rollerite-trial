package dev.fright.rollerite.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GodHandler implements Listener {

    private final Set<UUID> playerUuidSet = new HashSet<>();

    @EventHandler
    public void onPlayerHungerEvent(final @NotNull FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        if (!this.playerUuidSet.contains(player.getUniqueId()))
            return;

        event.setCancelled(true); // Cancel if player loses hunger.
    }

    @EventHandler
    public void onEntityDamageEvent(final @NotNull EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;

        if (!this.playerUuidSet.contains(player.getUniqueId()))
            return;

        event.setCancelled(true); // Cancel if player takes damage.
    }

    /**
     * @param playerUuid the uuid to check
     * @return true if the uuid is inside the {@link #playerUuidSet}, false otherwise
     */
    public boolean isInGodMode(final @NotNull UUID playerUuid) {
        return this.playerUuidSet.contains(playerUuid);
    }

    /**
     * @param playerUuid the uuid to add to the {@link #playerUuidSet}
     * @return true if the uuid was added, false otherwise
     */
    public boolean toggleGodMode(final @NotNull UUID playerUuid) {
        if(!this.playerUuidSet.add(playerUuid)) {
            this.playerUuidSet.remove(playerUuid);
            return false;
        }

        return true;
    }

}
