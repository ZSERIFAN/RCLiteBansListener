package by.thmihnea.listener;

import by.thmihnea.RCBL;
import by.thmihnea.SQLUtil;
import by.thmihnea.object.ListenedPlayer;
import litebans.api.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerConnectListener implements Listener {

    /**
     * Player {@link PlayerPreLoginEvent} event used
     * for detecting when a user joins the server, then
     * getting whether or not he's banned, and letting
     * him join no matter what happens.
     * @param e The event itself.
     */
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onConnect(PlayerPreLoginEvent e) {
        e.setResult(PlayerPreLoginEvent.Result.ALLOWED);
        UUID uuid = e.getUniqueId();
        String ip = e.getAddress().toString();

        AtomicBoolean banned = new AtomicBoolean(false);

        CompletableFuture.runAsync(() -> {
            boolean b = Database.get().isPlayerBanned(uuid, ip);
            banned.set(b);
        }).thenRun(() -> {
            ListenedPlayer listenedPlayer = new ListenedPlayer(uuid);
            listenedPlayer.setBanned(banned.get());
            listenedPlayer.setReason(SQLUtil.getBanReason(uuid));
        }).thenRun(() -> {
            RCBL.getInstance().logInfo("Registered new Listened Player in local data map. (Banned: " + banned.get() + ")");
        });
    }
}
