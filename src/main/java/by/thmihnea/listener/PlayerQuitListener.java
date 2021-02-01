package by.thmihnea.listener;

import by.thmihnea.RCBL;
import by.thmihnea.object.ListenedPlayer;
import by.thmihnea.object.ListenedPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    /**
     * Event which is fired up whenever a player
     * successfully leaves the server.
     * @param e The {@link PlayerQuitEvent} event itself.
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (ListenedPlayerManager.contains(player)) {
            ListenedPlayer listenedPlayer = ListenedPlayerManager.getListenedPlayer(player);
            ListenedPlayerManager.removeListenedPlayer(listenedPlayer);
            RCBL.getInstance().logInfo("Player " + player.getName() + " has been successfully had his ListenedPlayer object removed from cached memory.");
        }
    }
}
