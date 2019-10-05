package com.github.mineinabyss.shoppy.shops;

import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SerializableAs("ShoppyShop")
public class Shop implements ConfigurationSerializable {
    private List<Trade> trades = new ArrayList<>();
    private UUID uuid;

    public Shop(UUID uuid) {
        this.uuid = uuid;
    }

    public static Shop deserialize(Map<String, Object> args) {
        Shop shop = new Shop((UUID) args.get(ShopDataConfigManager.UUID_KEY));
        shop.setTrades((List<Trade>) args.get("trades"));
        return shop;
    }

    public void showGui(){

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
