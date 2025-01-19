package dev.fright.rollerite.handlers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TeleportHandler {

    private final Map<UUID, Set<UUID>> uuidToRequestsMap = new HashMap<>();

    /**
     * Request to teleport to a player.
     *
     * @param executorUuid the player requesting the teleport
     * @param targetUuid   the player to teleport to
     */
    public void requestPlayer(final @NotNull UUID executorUuid, final @NotNull UUID targetUuid) {
        this.uuidToRequestsMap.computeIfAbsent(targetUuid, k -> new HashSet<>()).add(executorUuid);
    }

    /**
     * Check if a player has already requested to teleport to another player.
     *
     * @param executorUuid the player requesting the teleport
     * @param targetUuid   the player to teleport to
     * @return true if the player has already requested to teleport to the other player
     */
    public boolean hasAlreadyRequested(final @NotNull UUID executorUuid, final @NotNull UUID targetUuid) {
        final Set<UUID> requestsSet = this.uuidToRequestsMap.get(targetUuid);
        return requestsSet != null && requestsSet.contains(executorUuid);
    }

    /**
     * Get the most recent request to teleport to a player and remove it from the list.
     *
     * @param targetUuid the player to teleport to
     * @return the player requesting the teleport
     */
    public @Nullable UUID pop(final @NotNull UUID targetUuid) {
        final Set<UUID> requestsSet = this.uuidToRequestsMap.get(targetUuid);
        if (requestsSet == null || requestsSet.isEmpty())
            return null;

        final UUID executorUuid = requestsSet.iterator().next();
        requestsSet.remove(executorUuid);

        return executorUuid;
    }

}
