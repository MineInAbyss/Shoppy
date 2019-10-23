package com.github.mineinabyss.shoppy;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.Reward;
import com.github.mineinabyss.shoppy.shops.Want;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyContext {
    private Shoppy plugin;
    private Logger logger;
    private Configuration config;
    private ShopDataConfigManager shopData;
    private List<UUID> editingPlayers = new ArrayList<>(); //TODO once we need this for more than just editing, make it a hashmap of UUID to the thing they are trying to edit
    private List<Class<? extends Want>> registeredWants = new ArrayList<>();
    private List<Class<? extends Reward>> registeredRewards = new ArrayList<>();

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

    public void beginEditSearch(Player player) {
        player.sendMessage(ChatColor.GREEN + "Right click on a shop to edit its contents, left click to stop");
        editingPlayers.add(player.getUniqueId());
    }

    public void registerWant(Class<? extends Want> clazz) {
        registeredWants.add(clazz);
    }

    public void registerReward(Class<? extends Reward> clazz) {
        registeredRewards.add(clazz);
    }

    private Want getDefaultWant(Class<? extends Want> clazz) {
        try {
            Method m = clazz.getDeclaredMethod("defaultWant");
            return (Want) m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reward getDefaultReward(Class<? extends Reward> clazz) {
        try {
            Method m = clazz.getDeclaredMethod("defaultReward");
            return (Reward) m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Want> getWants() {
        return registeredWants.stream().map(this::getDefaultWant).collect(Collectors.toList());
    }

    public List<Reward> getRewards() {
        return registeredRewards.stream().map(this::getDefaultReward).collect(Collectors.toList());
    }

    public boolean wasEditing(UUID uuid) {
        return editingPlayers.remove(uuid);
    }
}
