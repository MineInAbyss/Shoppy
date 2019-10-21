package com.github.mineinabyss.shoppy.shops;

import com.derongan.minecraft.guiy.gui.ClickableElement;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public interface Reward extends ShoppyMatter, ConfigurationSerializable {
    boolean reward(Player player);
}
