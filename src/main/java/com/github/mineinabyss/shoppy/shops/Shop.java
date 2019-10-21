package com.github.mineinabyss.shoppy.shops;

import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyAPI;
import com.github.mineinabyss.shoppy.configuration.ShopDataConfigManager;
import com.github.mineinabyss.shoppy.gui.shop.ShopGUI;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import java.util.*;

@SerializableAs("ShoppyShop")
public class Shop implements ConfigurationSerializable {
    private List<Trade> trades = new ArrayList<>();
    private UUID uuid;

    public Shop(UUID uuid) {
        this.uuid = uuid;
    }

    public static Shop deserialize(Map<String, Object> args) {
        Shop shop = new Shop(UUID.fromString((String) args.get(ShopDataConfigManager.UUID_KEY)));
        shop.setTrades((List<Trade>) args.get("trades"));
        return shop;
    }

    public void showGui(Player player){
        new ShopGUI(player, this).show(player);
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

    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public void save(){
        Shoppy.getInstance().getContext().getShopData().saveShop(this);
    }

    public Shop register(){
        ShoppyAPI.getInstance().addShop(this);
        return this;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyShop");
        args.put(ShopDataConfigManager.UUID_KEY, getUuid().toString());
        args.put("trades", getTrades());
        return args;
    }
}
