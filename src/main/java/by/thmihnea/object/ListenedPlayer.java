package by.thmihnea.object;

import java.util.UUID;

public class ListenedPlayer {

    /**
     * UUID of the {@link ListenedPlayer}
     * object.
     */
    private UUID uuid;

    /**
     * Boolean which tells us
     * if the player is banned or not.
     */
    private boolean banned;

    /**
     * Reason of the ban.
     */
    private String reason;

    /**
     * Default constructor for the
     * {@link ListenedPlayer} object.
     * This is used when the player joins the server
     * so that we can locally store data for a bit in
     * this object/class.
     * @param uuid Unique Identifier needed to
     *             successfully create the {@link ListenedPlayer}
     *             object.
     */
    public ListenedPlayer(UUID uuid) {
        this.uuid = uuid;
        ListenedPlayerManager.addListenedPlayer(this);
    }

    /**
     * Returns the UUID of
     * this object.
     * @return {@link UUID}
     */
    public UUID getUniqueId() {
        return this.uuid;
    }

    /**
     * Returns whether or not
     * the player is banned.
     * @return {@link Boolean}
     */
    public boolean isBanned() {
        return this.banned;
    }

    /**
     * Setter method which sets if
     * the user is banned or not.
     * @param banned Boolean to set {@link ListenedPlayer#banned}
     *               to.
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    /**
     * Returns the reason of the ban
     * @return {@link String}
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Setter methods which sets
     * the reason of the ban
     * @param reason Reason to set {@link ListenedPlayer#reason}
     *               to.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
