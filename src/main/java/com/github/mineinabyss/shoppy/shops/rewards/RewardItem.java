package com.github.mineinabyss.shoppy.shops.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardItem implements Reward {
    private Player player;
    private ItemStack reward;

    public RewardItem(Player player, ItemStack reward) {
        this.player = player;
        this.reward = reward;
    }

    public ItemStack getReward() {
        return reward;
    }

    public boolean reward() {
        return player.getInventory().addItem(reward).isEmpty();
    }
}
