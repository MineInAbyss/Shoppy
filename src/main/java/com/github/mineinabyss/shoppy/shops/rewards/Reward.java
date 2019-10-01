package com.github.mineinabyss.shoppy.shops.rewards;

import org.bukkit.inventory.ItemStack;

public interface Reward {
    ItemStack getReward();

    boolean reward();
}
