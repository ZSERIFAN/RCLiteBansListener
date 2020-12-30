package ro.thmdev.player;

import org.bukkit.entity.Player;

import java.util.UUID;

public class ListenedPlayer {

    private UUID uuid;
    private boolean banned;
    private String reason;

    public ListenedPlayer(UUID uuid) {
        this.uuid = uuid;
        ListenedPlayerManager.addListenedPlayer(this);
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public boolean isBanned() {
        return this.banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
