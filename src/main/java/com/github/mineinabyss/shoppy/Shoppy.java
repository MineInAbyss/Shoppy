package com.github.mineinabyss.shoppy;

import com.derongan.minecraft.guiy.GuiListener;
import com.github.mineinabyss.shoppy.commands.GUICommandExecutor;
import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.ShopListener;
import com.github.mineinabyss.shoppy.shops.Trade;
import com.github.mineinabyss.shoppy.shops.rewards.Reward;
import com.github.mineinabyss.shoppy.shops.rewards.RewardItem;
import com.github.mineinabyss.shoppy.shops.wants.Want;
import com.github.mineinabyss.shoppy.shops.wants.WantItem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shoppy extends JavaPlugin {
    private ShoppyContext context;


    public ShoppyContext getContext() {
        return context;
    }

    public static Shoppy getInstance() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Shoppy");
        return (Shoppy) plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigurationSerialization.registerClass(Shop.class, "ShoppyShop");
        ConfigurationSerialization.registerClass(Trade.class, "ShoppyTrade");
        ConfigurationSerialization.registerClass(WantItem.class, "ShoppyWantItem");
        ConfigurationSerialization.registerClass(RewardItem.class, "ShoppyRewardItem");
        //register context
        context = new ShoppyContext(getConfig());
        context.setPlugin(this);
        context.setLogger(getLogger());

        //create config managers
        context.setShopData(new ShopDataConfigManager(context));

        //register events
        getServer().getPluginManager().registerEvents(new GuiListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(context), this);

        //register command executors
        GUICommandExecutor guiCommandExecutor = new GUICommandExecutor(context);

        this.getCommand("shoppy").setExecutor(guiCommandExecutor);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getContext().getShopData().saveShopsAndClear();
    }
}
