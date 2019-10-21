package com.github.mineinabyss.shoppy.gui.editor;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.FillableElement;
import com.github.mineinabyss.shoppy.gui.HistoryLayout;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.ShoppyMatter;
import com.github.mineinabyss.shoppy.shops.Trade;
import de.erethon.headlib.HeadLib;

import java.util.List;

public class EditTradeGUI extends HistoryLayout {
    FillableElement wants = new FillableElement(5, 1);
    FillableElement rewards = new FillableElement(5, 1);
    private Trade trade;

    public EditTradeGUI(EditGUI holder, Shop shop, Trade trade) {
        super(holder);
        this.trade = trade;

        update();

        //save button
        ClickableElement save = new ClickableElement(Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "Save to file"));
        save.setClickAction(clickEvent -> {
            shop.save();
            backInHistory();
        });

        addBackButton();
        addElement(0, 5, save);
    }

    private void createMatter(List<? extends ShoppyMatter> matters, FillableElement fillable) {
        matters.forEach(matter -> {
            ClickableElement wantElement = matter.toClickableElement();
            wantElement.setClickAction(clickEvent -> holder.setElement(matter.buildEditLayout(this)));
            fillable.addElement(wantElement);
        });
    }

    public void update() {
        //wants
        wants.clear();
        createMatter(trade.getWants(), wants);
        addElement(1, 1, wants);

        //rewards
        rewards.clear();
        createMatter(trade.getRewards(), rewards);
        addElement(6, 1, rewards);
    }
}
