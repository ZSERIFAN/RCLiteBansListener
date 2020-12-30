package ro.thmdev.listener;

import litebans.api.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import ro.thmdev.player.ListenedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerConnectListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onConnect(PlayerPreLoginEvent e) {
        e.setResult(PlayerPreLoginEvent.Result.ALLOWED);
        UUID uuid = e.getUniqueId();
        String ip = e.getAddress().toString();

        AtomicBoolean isBanned = new AtomicBoolean(false);
        CompletableFuture.runAsync(() -> {
            isBanned.set(Database.get().isPlayerBanned(uuid, ip));
        }).thenRun(() -> {
            ListenedPlayer listenedPlayer = new ListenedPlayer(e.getUniqueId());
            listenedPlayer.setBanned(isBanned.get());
            listenedPlayer.setReason(getBanReason(listenedPlayer.getUUID()));
        });
    }

    public String getBanReason(UUID uuid) {
        String reason = null;
        try (PreparedStatement ps = Database.get().prepareStatement("SELECT * FROM {bans} WHERE uuid=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    reason = rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reason;
    }
}
