package com.github.mineinabyss.shoppy.shops.item;

import com.derongan.minecraft.guiy.gui.Layout;
import com.github.mineinabyss.shoppy.gui.editor.EditGUI;
import com.github.mineinabyss.shoppy.gui.editor.ItemEditorLayout;
import com.github.mineinabyss.shoppy.shops.Want;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("ShoppyWantItem")
public class WantItem extends ItemShop implements Want {
    public WantItem(ItemStack wantedItem) {
        super(wantedItem);
    }

    public static WantItem deserialize(Map<String, Object> args) {
        WantItem wantItem = new WantItem((ItemStack) args.get("wanted-item"));
        return wantItem;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("==", "ShoppyWantItem");
        args.put("wanted-item", item);
        return args;
    }

    @Override
    public boolean conditionMet(Player player) {
        return player.getInventory().containsAtLeast(item, item.getAmount());
    }

    @Override
    public void fulfillWant(Player player) {
        player.getInventory().removeItem(item);
    }
}
