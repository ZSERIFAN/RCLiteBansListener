package ro.thmdev;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import ro.thmdev.listener.*;

import java.util.Arrays;

public class Util {

    public static void registerEvents() {
        RCLBListener.getInstance().events = new Listener[]{
                new PlayerConnectListener(),
                new PlayerQuitListener(),
                new PlayerJoinListener(),
                new InventoryCloseListener()
        };
        Arrays.asList(RCLBListener.getInstance().events).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, RCLBListener.getInstance());
        });
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(RCLBListener.getInstance(), "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(RCLBListener.getInstance(), "BungeeCord", new BungeeMessageListener());
    }
}
