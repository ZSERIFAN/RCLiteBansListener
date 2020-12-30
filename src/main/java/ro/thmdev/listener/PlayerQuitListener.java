package ro.thmdev.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ro.thmdev.player.ListenedPlayerManager;

import java.util.logging.Level;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (ListenedPlayerManager.getListenedPlayers().containsKey(e.getPlayer().getUniqueId()))
            ListenedPlayerManager.removeListenedPlayer(ListenedPlayerManager.getListenedPlayer(e.getPlayer()));
        Bukkit.getLogger().log(Level.INFO, "[RCLiteBansListener] Removed Listened Player object belonging to " + e.getPlayer().getName() + " (UUID: " + e.getPlayer().getUniqueId() + ") from cached memory.");
    }
}
