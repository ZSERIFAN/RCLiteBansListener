package ro.thmdev.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ro.thmdev.RCLBListener;
import ro.thmdev.player.ListenedPlayer;
import ro.thmdev.player.ListenedPlayerManager;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        ListenedPlayer listenedPlayer = ListenedPlayerManager.getListenedPlayer(player);
        if (!listenedPlayer.isBanned()) return;
        String serverName = RCLBListener.cfg.getString("bungee.server-name");
        BungeeMessageListener.teleportServer(player, serverName);
    }
}
