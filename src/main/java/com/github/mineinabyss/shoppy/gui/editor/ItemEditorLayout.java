package com.github.mineinabyss.shoppy.gui.editor;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.ContainerElement;
import com.github.mineinabyss.shoppy.shops.item.ItemShop;
import de.erethon.headlib.HeadLib;

public class ItemEditorLayout extends EditorLayout {
    private ItemShop itemElement;
    private ContainerElement container;

    public ItemEditorLayout(ItemShop itemElement, EditTradeGUI editTradeGUI) {
        super(editTradeGUI);
        this.itemElement = itemElement;

        //container for swapping out items
        container = new ContainerElement(4, 7, null, null);
        buildContainer();
        addElement(1, 1, container);

        //save button
        ClickableElement save = new ClickableElement(Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "Save"));
        save.setClickAction(clickEvent -> {
            itemElement.setItem(((Cell) container.getElement(0, 0)).itemStack);
            editTradeGUI.update();
            addAndGoBack(itemElement);
        });
        addElement(0, 5, save);
        addBackButton();
    }

    private void buildContainer() {
        container.addElement(Cell.forItemStack(itemElement.getItem()));
    }
}
