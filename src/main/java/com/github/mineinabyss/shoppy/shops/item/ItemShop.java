package com.github.mineinabyss.shoppy.shops.item;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.Element;
import com.derongan.minecraft.guiy.gui.Layout;
import com.github.mineinabyss.shoppy.gui.editor.EditTradeGUI;
import com.github.mineinabyss.shoppy.gui.editor.ItemEditorLayout;
import com.github.mineinabyss.shoppy.shops.ShoppyType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    @Override
    public ItemStack getEditGUIAddItem() {
        ItemStack itemStack = new ItemStack(Material.STONE);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Item");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public ItemStack getDisplayItem() {
        ItemStack display = item.clone();
        display.getItemMeta().setDisplayName(getDisplayName());
        return display;
    }
}
