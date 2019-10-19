package com.github.mineinabyss.shoppy.shops.rewards;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs("ShoppyReward")
public interface Reward extends ConfigurationSerializable {
    boolean reward(Player player);
}
