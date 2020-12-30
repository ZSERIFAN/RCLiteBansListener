package ro.thmdev;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class RCLBListener extends JavaPlugin {

    private static RCLBListener instance;
    public static RCLBListener getInstance() {
        return instance;
    }
    private long timeEnabled;

    public static File config = new File("plugins/RCLiteBansListener/config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);
    public Listener[] events;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Initializing plugin setup.");
        this.timeEnabled = System.currentTimeMillis();
        instance = this;
        File dir = new File("plugins", "RCLiteBansListener");
        if (!(dir.exists())) dir.mkdir();
        if (!(config.exists())) saveDefaultConfig();
        loadCfg(cfg, config);
        Util.registerEvents();
        getLogger().log(Level.INFO, "Plugin enable done. Process took: " + (System.currentTimeMillis() - timeEnabled)+ "ms");
    }

    @Override
    public void onDisable() {

    }

    static void loadCfg(FileConfiguration fileConfiguration, File file) {
        try {
            fileConfiguration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
