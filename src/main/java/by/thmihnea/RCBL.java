package by.thmihnea;

import by.thmihnea.listener.BungeeMessageListener;
import by.thmihnea.listener.PlayerConnectListener;
import by.thmihnea.listener.PlayerJoinListener;
import by.thmihnea.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class RCBL extends AbstractPlugin {

    /**
     * Our one and only
     * {@link RCBL} instance.
     */
    private static RCBL instance;

    /**
     * Variable which we use
     * for time storing.
     */
    private long time;

    /**
     * The list of listeners
     * which we have to register.
     */
    private List<Listener> listeners = Arrays.asList(
        new PlayerConnectListener(),
        new PlayerJoinListener(),
        new PlayerQuitListener()
    );

    /**
     * Returns the instance of this
     * class.
     * @return {@link RCBL}
     */
    public static RCBL getInstance() {
        return instance;
    }

    /**
     * Method which has replaced
     * {@link JavaPlugin#onEnable()}, inherited from
     * {@link AbstractPlugin}
     */
    @Override
    protected void start() {
        this.logInfo("Attempting to enable plugin modules.");
        this.setupTime();
        this.setupInstance();
        this.setupFiles();
        this.registerEvents(this.listeners);
        this.setupBungeeListeners();
        this.logInfo("Plugin has been successfully enabled. Process took: " + (System.currentTimeMillis() - this.time) + "ms");
    }

    /**
     * Method which has replaced
     * {@link JavaPlugin#onDisable()}, inherited from
     * {@link AbstractPlugin}
     */
    @Override
    protected void stop() {
        this.logInfo("Plugin has been successfully disabled.");
    }

    /**
     * Sets up the instance for the
     * {@link RCBL} class.
     */
    private void setupInstance() {
        instance = this;
    }

    /**
     * Sets up the time variable
     * so that we can track the amount
     * of time being used for plugin
     * startup.
     */
    private void setupTime() {
        this.time = System.currentTimeMillis();
    }

    /**
     * Sets up the {@link BungeeMessageListener} listener
     * so that we can send players to other servers.
     */
    protected void setupBungeeListeners() {
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(RCBL.getInstance(), "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(RCBL.getInstance(), "BungeeCord", new BungeeMessageListener());
    }
}
