package com.github.mineinabyss.shoppy.shops;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.shops.rewards.Reward;
import com.github.mineinabyss.shoppy.shops.wants.Want;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SerializableAs("ShoppyTrade")
public class Trade implements ConfigurationSerializable {
    private List<Want> wants = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();
    private Player player;

    public static Trade deserialize(Map<String, Object> args) {
        return new Trade();
    }

    public boolean conditionsMet() {
        return wants.stream().anyMatch(want -> want.conditionMet(player));
    }

    public void trade() {
        if (conditionsMet())
            rewards.forEach(Reward::reward);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyTrade");
//        args.put(ShopDataConfigManager.UUID_KEY, getUuid().toString());
//        args.put("trades", getTrades());
        return args;
    }
}
