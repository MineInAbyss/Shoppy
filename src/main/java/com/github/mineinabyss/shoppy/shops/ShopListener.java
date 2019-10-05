package com.github.mineinabyss.shoppy.shops;

import com.github.mineinabyss.shoppy.ShoppyContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ShopListener implements Listener {
    private ShoppyContext context;

    public ShopListener(ShoppyContext context) {
        this.context = context;
    }

    @EventHandler
    public void onRightClickShopKeeper(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (entity.getScoreboardTags().contains("shoppy")) {
            Shop shop = context.getShopData().getShop(e.getRightClicked().getUniqueId());
            if(shop == null)
                Bukkit.broadcastMessage("Failed to get shop!");
            else
                Bukkit.broadcastMessage(shop.toString());
        }
    }
}
