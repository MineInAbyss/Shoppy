package com.github.mineinabyss.shoppy.shops;

import com.github.mineinabyss.shoppy.ShoppyAPI;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.gui.editor.EditGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopListener implements Listener {
    private ShoppyContext context;

    public ShopListener(ShoppyContext context) {
        this.context = context;
    }

    @EventHandler
    public void onRightClickShopKeeper(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (entity.getScoreboardTags().contains("shoppy")) {
            Shop shop = ShoppyAPI.getInstance().getShop(e.getRightClicked().getUniqueId());
            if (shop != null) {
                Player p = e.getPlayer();
                e.setCancelled(true);
                if (context.wasEditing(p.getUniqueId())) {
                    p.sendMessage(ChatColor.GREEN + "Now editing shop");
                    new EditGUI(p, shop).show(p);
                } else
                    shop.showGui(p);
            }
        }
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() == Action.LEFT_CLICK_BLOCK && context.wasEditing(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "Cancelled editing shop");
        }
    }
}
