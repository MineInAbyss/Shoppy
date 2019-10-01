package com.github.mineinabyss.shoppy.shops.wants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WantItem implements Want {
    private String displayName;
    private ItemStack displayItem;
    private ItemStack wantedItem;

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }

    @Override
    public boolean conditionMet(Player player) {
        return player.getInventory().contains(wantedItem);
    }
}
