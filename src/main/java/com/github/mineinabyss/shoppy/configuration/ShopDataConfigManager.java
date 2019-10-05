package com.github.mineinabyss.shoppy.configuration;

import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.google.common.annotations.VisibleForTesting;
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
        return loadedShops.getOrDefault(uuid, loadShop(uuid));
    }

    public Shop loadShop(UUID uuid) {
        Path path = getShopDataPath(uuid);

        if (path.toFile().exists()) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(path.toFile());
            Shop shop = (Shop) config.get(""); //TODO not sure if this can work. Maybe getRoot?
            loadedShops.put(uuid, shop);
            return shop;
        }
        return null;
    }

    public void saveShop(Shop shop) throws IOException {
        Path path = getShopDataPath(shop.getUuid());

        // Recreate directories if missing
        path.toFile().getParentFile().mkdirs();

        YamlConfiguration config = new YamlConfiguration();
        config.set("", shop.serialize());
        config.save(path.toFile());
    }


    @VisibleForTesting
    Path getShopDataPath(UUID uuid) {
        return Shoppy.getInstance().getDataFolder()
                .toPath()
                .resolve(SHOP_DATA_DIR)
                .resolve(uuid.toString() + ".yml");
    }
}
