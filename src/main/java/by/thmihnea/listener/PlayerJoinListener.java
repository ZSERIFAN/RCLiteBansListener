package by.thmihnea.listener;

import by.thmihnea.BungeeUtil;
import by.thmihnea.RCBL;
import by.thmihnea.object.ListenedPlayer;
import by.thmihnea.object.ListenedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    /**
     * Event which is fired up whenever a player
     * successfully joins the server. (After the
     * {@link PlayerConnectListener} event is fired up)
     * @param e The {@link PlayerJoinEvent} event itself.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ListenedPlayer listenedPlayer = ListenedPlayerManager.getListenedPlayer(player);
        if (!listenedPlayer.isBanned()) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(RCBL.getCfg().getString("message"))
                .replace("%reason%", listenedPlayer.getReason())));
        String serverName = RCBL.getCfg().getString("gulag-server");
        Bukkit.getScheduler().scheduleSyncDelayedTask(RCBL.getInstance(), () -> {
            BungeeUtil.teleportServer(player, serverName);
        }, 5L);
    }
}
