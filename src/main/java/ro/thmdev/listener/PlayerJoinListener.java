package ro.thmdev.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ro.thmdev.RCLBListener;
import ro.thmdev.inventory.BannedGUI;
import ro.thmdev.player.ListenedPlayer;
import ro.thmdev.player.ListenedPlayerManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ListenedPlayer listenedPlayer = ListenedPlayerManager.getListenedPlayer(e.getPlayer());
        if (listenedPlayer.getReason() == null) return;
        e.getPlayer().sendMessage(listenedPlayer.getReason());
        Bukkit.getScheduler().scheduleSyncDelayedTask(RCLBListener.getInstance(), () -> {
            BannedGUI.gui.open(e.getPlayer());
        }, 5L);
    }
}
