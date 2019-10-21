package com.github.mineinabyss.shoppy.shops.item;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.Element;
import com.derongan.minecraft.guiy.gui.Layout;
import com.github.mineinabyss.shoppy.gui.editor.EditGUI;
import com.github.mineinabyss.shoppy.gui.editor.EditTradeGUI;
import com.github.mineinabyss.shoppy.gui.editor.ItemEditorLayout;
import org.bukkit.inventory.ItemStack;

public abstract class ItemShop {
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
        String name = item.getType().name();
        if (amount > 1)
            name += " x" + amount;
        return name;
    }

    public ItemStack getDisplayItem() {
        ItemStack display = item.clone();
        display.getItemMeta().setDisplayName(getDisplayName());
        return display;
    }

    public Layout buildEditLayout(EditTradeGUI editTradeGUI) {
        return new ItemEditorLayout(this, editTradeGUI);
    }

    public ClickableElement toClickableElement() {
        return new ClickableElement(Cell.forItemStack(getDisplayItem(), getDisplayName()));
    }

    public Element toElement() {
        return Cell.forItemStack(getDisplayItem(), getDisplayName());
    }
}
