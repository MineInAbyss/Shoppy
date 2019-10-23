package com.github.mineinabyss.shoppy.shops.item;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.Element;
import com.derongan.minecraft.guiy.gui.Layout;
import com.github.mineinabyss.shoppy.gui.editor.EditTradeGUI;
import com.github.mineinabyss.shoppy.gui.editor.ItemEditorLayout;
import com.github.mineinabyss.shoppy.shops.Reward;
import com.github.mineinabyss.shoppy.shops.ShoppyType;
import com.github.mineinabyss.shoppy.shops.Want;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ItemShop implements ShoppyType {
    protected ItemStack item;

    public ItemShop(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getDisplayName() {
        int amount = item.getAmount();
        String name;
        if (item.getItemMeta().hasDisplayName())
            name = item.getItemMeta().getDisplayName();
        else
            name = item.getType().name();

        if (amount > 1)
            name += " x" + amount;
        return name;
    }

    public Layout buildEditLayout(EditTradeGUI editTradeGUI) {
        return new ItemEditorLayout(this, editTradeGUI);
    }

    @Override
    public ClickableElement toClickableElement() {
        return new ClickableElement(Cell.forItemStack(getDisplayItem(), getDisplayName()));
    }

    public Element toElement() {
        return Cell.forItemStack(getDisplayItem(), getDisplayName());
    }

    //TODO make item look fancy
    @Override
    public ItemStack getEditGUIAddItem() {
        return new ItemStack(Material.STONE);
    }

    @Override
    public ItemStack getDisplayItem() {
        ItemStack display = item.clone();
        display.getItemMeta().setDisplayName(getDisplayName());
        return display;
    }
}
