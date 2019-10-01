package com.github.mineinabyss.shoppy;

import com.derongan.minecraft.guiy.GuiListener;
import com.github.mineinabyss.shoppy.shops.ShopListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shoppy extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
