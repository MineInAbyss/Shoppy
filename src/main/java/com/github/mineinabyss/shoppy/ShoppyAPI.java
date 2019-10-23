package com.github.mineinabyss.shoppy;

import com.github.mineinabyss.shoppy.shops.Reward;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Want;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyAPI {
    private ShoppyContext context;

    public static ShoppyAPI getInstance() {
        return ((Shoppy) Bukkit.getServer().getPluginManager().getPlugin("Shoppy")).getAPI();
    }

    ShoppyAPI(ShoppyContext context) {
        this.context = context;
    }

    public void addShop(Shop shop) {
        context.getShopData().addShop(shop);
    }

    public Shop getShop(UUID uuid) {
        return context.getShopData().getShop(uuid);
    }

    public void registerWant(Class<? extends Want> clazz) {
        context.registerWant(clazz);
    }

    public void registerReward(Class<? extends Reward> clazz) {
        context.registerReward(clazz);
    }
}
