package by.thmihnea.listener;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeMessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (!s.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subchannel = in.readUTF();

        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
        }
    }

}
