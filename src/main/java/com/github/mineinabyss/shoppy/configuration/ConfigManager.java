package com.github.mineinabyss.shoppy.configuration;

import com.github.mineinabyss.shoppy.shops.Shop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private FileConfiguration configuration;

    public ConfigManager(FileConfiguration configuration) {
        this.configuration = configuration;
    }

    public static void createConfig(Plugin plugin) {
        try {
            if (!plugin.getDataFolder().exists()) {
                if(!plugin.getDataFolder().mkdirs()){
                    throw new RuntimeException("Failed to make config file");
                }
            }
            File file = new File(plugin.getDataFolder(), "config.yml");
            if (!file.exists()) {
                plugin.getLogger().info("Config.yml not found, creating!");
                plugin.saveDefaultConfig();
            } else {
                plugin.getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
