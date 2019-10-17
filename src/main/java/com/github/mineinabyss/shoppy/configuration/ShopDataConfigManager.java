package com.github.mineinabyss.shoppy.configuration;

import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.google.common.annotations.VisibleForTesting;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShopDataConfigManager {
    public static final String UUID_KEY = "uuid";
    public static final String TRADES_KEY = "trades";
    public static final String SHOP_DATA_DIR = "shops";

    //TODO Derongan stores this in the context, not sure if that's better
    private static final Map<UUID, Shop> loadedShops = new HashMap<>();

    private ShoppyContext context;

    public ShopDataConfigManager(ShoppyContext context) {
        this.context = context;
    }

    public Shop getShop(UUID uuid) {
        Shop shop = loadedShops.computeIfAbsent(uuid, this::readShop);
        Bukkit.broadcastMessage(loadedShops.keySet().toString());
        return shop;
    }

    public void addShop(Shop shop) {
        loadedShops.put(shop.getUuid(), shop);
        saveShop(shop);
    }

    public Shop readShop(UUID uuid) {
        Path path = getShopDataPath(uuid);

        if (path.toFile().exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(path.toFile());
            return (Shop) config.get("shop");
        }
        return null;
    }

    public void saveShop(Shop shop) {
        Path path = getShopDataPath(shop.getUuid());

        // Recreate directories if missing
        path.toFile().getParentFile().mkdirs();

        FileConfiguration config = new YamlConfiguration();
        config.set("shop", shop);
        try {
            config.save(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveShopsAndClear() {
        loadedShops.values().forEach(this::saveShop);
        loadedShops.clear();
    }


    @VisibleForTesting
    Path getShopDataPath(UUID uuid) {
        return Shoppy.getInstance().getDataFolder()
                .toPath()
                .resolve(SHOP_DATA_DIR)
                .resolve(uuid.toString() + ".yml");
    }
}
