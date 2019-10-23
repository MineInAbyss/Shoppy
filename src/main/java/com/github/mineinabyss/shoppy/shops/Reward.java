package com.github.mineinabyss.shoppy.shops;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public interface Reward extends ConfigurationSerializable, ShoppyType {
    boolean reward(Player player);
}
