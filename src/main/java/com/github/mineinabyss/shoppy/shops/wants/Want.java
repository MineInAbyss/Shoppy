package com.github.mineinabyss.shoppy.shops.wants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Want {
    String getDisplayName();

    ItemStack getDisplayItem();

    boolean conditionMet(Player player);
}
