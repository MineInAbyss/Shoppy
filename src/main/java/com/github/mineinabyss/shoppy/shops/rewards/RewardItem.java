package com.github.mineinabyss.shoppy.shops.rewards;

import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("ShoppyRewardItem")
public class RewardItem implements Reward {
    private ItemStack item;

    public RewardItem() {
    }

    public RewardItem(ItemStack item) {
        this.item = item;
    }

    public static RewardItem deserialize(Map<String, Object> args) {
        RewardItem rewardItem = new RewardItem();
        rewardItem.setItem((ItemStack) args.get("item"));
        return rewardItem;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public boolean reward(Player player) {
        return player.getInventory().addItem(item).isEmpty();
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyRewardItem");
        args.put("item", item);
        return args;

    }
}
