package com.github.mineinabyss.shoppy.shops;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public interface Want extends ConfigurationSerializable, ShoppyType {
    boolean conditionMet(Player player);

    void fulfillWant(Player player);
}
