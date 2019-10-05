package com.github.mineinabyss.shoppy;

import com.derongan.minecraft.guiy.GuiListener;
import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.ShopListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shoppy extends JavaPlugin {
    private static ShoppyContext context;

    static{
        ConfigurationSerialization.registerClass(Shop.class, "ShoppyShop");
    }
    public static Shoppy getInstance() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineInAbyss");
        return (Shoppy) plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        //register context
        context = new ShoppyContext(getConfig());
        context.setPlugin(this);
        context.setLogger(getLogger());

        //create config managers
        context.setShopData(new ShopDataConfigManager(context));

        //register events
        getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(context), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
