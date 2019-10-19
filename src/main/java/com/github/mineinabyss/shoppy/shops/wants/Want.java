package com.github.mineinabyss.shoppy.shops.wants;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Want extends ConfigurationSerializable {
    String getDisplayName();

    ItemStack getDisplayItem();

    boolean conditionMet(Player player);

    void fulfillWant(Player player);
}
