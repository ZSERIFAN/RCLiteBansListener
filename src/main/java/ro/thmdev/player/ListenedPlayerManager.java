package ro.thmdev.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ListenedPlayerManager {

    private static Map<UUID, ListenedPlayer> listenedPlayers = new HashMap<>();

    public static Map<UUID, ListenedPlayer> getListenedPlayers() {
        return listenedPlayers;
    }

    public static void addListenedPlayer(ListenedPlayer listenedPlayer) {
        listenedPlayers.put(listenedPlayer.getUUID(), listenedPlayer);
    }

    public static void removeListenedPlayer(ListenedPlayer listenedPlayer) {
        listenedPlayers.remove(listenedPlayer.getUUID());
    }

    public static ListenedPlayer getListenedPlayer(Player player) {
        return listenedPlayers.get(player.getUniqueId());
    }
}
