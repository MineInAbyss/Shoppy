package com.github.mineinabyss.shoppy.shops;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SerializableAs("ShoppyTrade")
public class Trade implements ConfigurationSerializable {
    private List<Want> wants = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    private ItemStack displayItem;

    public Trade() {
    }

    public Trade(ItemStack displayItem, Want want, Reward reward) {
        this.displayItem = displayItem;
        wants.add(want);
        rewards.add(reward);
    }

    public static Trade deserialize(Map<String, Object> args) {
        Trade trade = new Trade();
        trade.setWants((List<Want>) args.get("wants"));
        trade.setRewards((List<Reward>) args.get("rewards"));
        trade.setDisplayItem((ItemStack) args.get("display-item"));
        return trade;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public List<Want> getWants() {
        return wants;
    }

    public void setWants(List<Want> wants) {
        this.wants = wants;
    }

    public void remove(ShoppyType matter){
        if(matter instanceof Want)
            wants.remove(matter);
        else if(matter instanceof Reward)
            rewards.remove(matter);
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public boolean conditionsMet(Player player) {
        return wants.stream().anyMatch(want -> want.conditionMet(player));
    }

    public boolean trade(Player player) {
        if (conditionsMet(player)) {
            wants.forEach(want -> want.fulfillWant(player));
            rewards.forEach(reward -> reward.reward(player));
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> serialize() {
        Bukkit.broadcastMessage("Serializing trade");
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyTrade");
        args.put("wants", wants);
        args.put("rewards", rewards);
        args.put("display-item", displayItem);
        return args;
    }
}
