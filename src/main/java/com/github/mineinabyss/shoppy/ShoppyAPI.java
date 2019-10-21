package com.github.mineinabyss.shoppy;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.Shop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginBase;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Stores context for the plugin, such as the plugin instance
 */
public class ShoppyAPI {
    private ShoppyContext context;

    public static ShoppyAPI getInstance(){
        return ((Shoppy) Bukkit.getServer().getPluginManager().getPlugin("Shoppy")).getAPI();
    }

    ShoppyAPI(ShoppyContext context) {
        this.context = context;
    }

    public void addShop(Shop shop){
        context.getShopData().addShop(shop);
    }

    public Shop getShop(UUID uuid){
        return context.getShopData().getShop(uuid);
    }
}
