package by.thmihnea;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeUtil {

    /**
     * Method used to teleport a server from one
     * server to another, using only the server name
     * passed as a {@link String}.
     * @param p Player to teleport.
     * @param server Desired location.
     */
    public static void teleportServer(Player p, String server) {

        Bukkit.getMessenger().registerOutgoingPluginChannel(RCBL.getInstance(), "BungeeCord");

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.sendPluginMessage(RCBL.getInstance(), "BungeeCord", b.toByteArray());
    }

}
