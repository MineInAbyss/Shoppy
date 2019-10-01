package com.github.mineinabyss.shoppy;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyContext {
    //    private Map<UUID, PlayerData> playerDataMap = new HashMap<>();
    private Plugin plugin;
    private Logger logger;
    private Configuration config;

    public ShoppyContext(Configuration config) {
        this.config = config;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

//    public Map<UUID, PlayerData> getPlayerDataMap() {
//        return playerDataMap;
//    }

//    public PlayerData getPlayerData(Player player){
//        return getPlayerDataMap().get(player.getUniqueId());
//    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration getConfig() {
        return config;
    }

//    public AbyssWorldManager getWorldManager() {
//        return worldManager;
//    }
//    public WorldManager getRealWorldManager() {
//        return realWorldManager;
//    }
}
