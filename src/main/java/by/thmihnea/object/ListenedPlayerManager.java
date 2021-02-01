package by.thmihnea.object;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ListenedPlayerManager {

    /**
     * This is our {@link Map} which
     * stores all ListenedPlayer objects and binds
     * them to the pointer of the player's Unique Identifier.
     * See {@link ListenedPlayer} for additional info on
     * this matter.
     */
    private static final Map<UUID, ListenedPlayer> listenedPlayers = new HashMap<>();

    /**
     * Returns the HashMap above.
     * @return {@link Map}
     */
    public static Map<UUID, ListenedPlayer> getListenedPlayers() {
        return listenedPlayers;
    }

    /**
     * Adds a listened player object to the
     * data structure.
     * @param listenedPlayer Listened Player object to
     *                       be added.
     */
    public static void addListenedPlayer(ListenedPlayer listenedPlayer) {
        listenedPlayers.put(listenedPlayer.getUniqueId(), listenedPlayer);
    }

    /**
     * Removes a listened player object from
     * the data structure.
     * @param listenedPlayer Listened Player object
     *                       to be removed.
     */
    public static void removeListenedPlayer(ListenedPlayer listenedPlayer) {
        listenedPlayers.remove(listenedPlayer.getUniqueId());
    }

    /**
     * Returns a listened player based off of
     * the Player parameter.
     * @param player Player to lookup for.
     * @return {@link ListenedPlayer}
     */
    public static ListenedPlayer getListenedPlayer(Player player) {
        return listenedPlayers.get(player.getUniqueId());
    }

    /**
     * Returns whether or not the {@link Map} data
     * structure which we've set up for the Listened Player
     * objects contains a specified Player Unique Identifier.
     * @param player Player to lookup for.
     * @return {@link Boolean}
     */
    public static boolean contains(Player player) {
        return listenedPlayers.containsKey(player.getUniqueId());
    }
}
