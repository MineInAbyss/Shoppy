package com.github.mineinabyss.shoppy.shops;

import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.Element;
import com.derongan.minecraft.guiy.gui.Layout;
import com.github.mineinabyss.shoppy.gui.editor.EditTradeGUI;
import org.bukkit.inventory.ItemStack;

public interface ShoppyType {

    ClickableElement toClickableElement();

    Element toElement();

    Layout buildEditLayout(EditTradeGUI editGUI);

    ItemStack getEditGUIAddItem();

    ItemStack getDisplayItem();
}
