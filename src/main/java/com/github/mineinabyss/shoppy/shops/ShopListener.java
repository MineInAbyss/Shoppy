package com.github.mineinabyss.shoppy.shops;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ShopListener implements Listener {
    @EventHandler
    public void onRightClickShopKeeper(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (entity.getScoreboardTags().contains("shoppy")) {

        }
    }
}
