package com.github.mineinabyss.shoppy;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.Shop;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyContext {
    private Shoppy plugin;
    private Logger logger;
    private Configuration config;
    private ShopDataConfigManager shopData;
    private List<UUID> editingPlayers = new ArrayList<>(); //TODO once we need this for more than just editing, make it a hashmap of UUID to the thing they are trying to edit

    ShoppyContext(Configuration config) {
        this.config = config;
    }

    public ShopDataConfigManager getShopData() {
        return shopData;
    }

    void setShopData(ShopDataConfigManager shopData) {
        this.shopData = shopData;
    }

    public Shoppy getPlugin() {
        return plugin;
    }

    void setPlugin(Shoppy plugin) {
        this.plugin = plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration getConfig() {
        return config;
    }

    public void beginEditSearch(Player player){
        player.sendMessage(ChatColor.GREEN + "Right click on a shop to edit its contents, left click to stop");
        editingPlayers.add(player.getUniqueId());
    }

    public boolean wasEditing(UUID uuid){
        return editingPlayers.remove(uuid);
    }
}
