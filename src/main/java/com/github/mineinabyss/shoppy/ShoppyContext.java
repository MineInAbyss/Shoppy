package com.github.mineinabyss.shoppy;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyContext {
    private Plugin plugin;
    private Logger logger;
    private Configuration config;
    private ShopDataConfigManager shopData;

    public ShoppyContext(Configuration config) {
        this.config = config;
    }

    public ShopDataConfigManager getShopData() {
        return shopData;
    }

    public void setShopData(ShopDataConfigManager shopData) {
        this.shopData = shopData;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration getConfig() {
        return config;
    }

}
