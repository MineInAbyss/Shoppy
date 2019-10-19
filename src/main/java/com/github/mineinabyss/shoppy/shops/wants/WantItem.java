package com.github.mineinabyss.shoppy.shops.wants;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("ShoppyWantItem")
public class WantItem implements Want {
    private String displayName;
    private ItemStack displayItem;
    private ItemStack wantedItem;

    public WantItem() {
    }

    public WantItem(ItemStack wantedItem) {
        this.wantedItem = wantedItem;
        displayItem = wantedItem.clone();
    }

    public static WantItem deserialize(Map<String, Object> args) {
        WantItem wantItem = new WantItem();
        wantItem.setDisplayName((String) args.get("display-name"));
        wantItem.setDisplayItem((ItemStack) args.get("display-item"));
        wantItem.setWantedItem((ItemStack) args.get("wanted-item"));
        return wantItem;
    }

    public ItemStack getWantedItem() {
        return wantedItem;
    }

    public void setWantedItem(ItemStack wantedItem) {
        this.wantedItem = wantedItem;
    }

    @Override
    public String getDisplayName() {
        if (displayName == null) {
            int amount = displayItem.getAmount();
            String name = displayItem.getType().name();
            if (amount > 1)
                name += " x" + amount;
            return name;
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    @Override
    public boolean conditionMet(Player player) {
        Bukkit.broadcastMessage(wantedItem.toString());
        return player.getInventory().containsAtLeast(wantedItem, wantedItem.getAmount());
    }

    @Override
    public void fulfillWant(Player player) {
        player.getInventory().removeItem(wantedItem);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyWantItem");
        args.put("display-name", displayName);
        args.put("display-item", displayItem);
        args.put("wanted-item", wantedItem);
        return args;
    }
}
