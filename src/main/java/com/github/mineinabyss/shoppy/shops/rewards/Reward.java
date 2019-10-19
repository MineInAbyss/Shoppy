package com.github.mineinabyss.shoppy.shops.rewards;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SerializableAs("ShoppyReward")
public interface Reward extends ConfigurationSerializable {
    String getDisplayName();

    boolean reward(Player player);

    ItemStack getDisplayItem();
}
