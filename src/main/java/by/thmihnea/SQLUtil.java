package by.thmihnea;

import litebans.api.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLUtil {

    /**
     * Method to return the ban reason of a player
     * from the local LiteBans database.
     * @param uuid Unique Identifier to scatter for.
     * @return {@link String}
     */
    public static String getBanReason(UUID uuid) {
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
