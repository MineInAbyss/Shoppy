package com.github.mineinabyss.shoppy.gui.editor;

import com.github.mineinabyss.shoppy.gui.HistoryLayout;
import com.github.mineinabyss.shoppy.shops.Reward;
import com.github.mineinabyss.shoppy.shops.ShoppyType;
import com.github.mineinabyss.shoppy.shops.Trade;
import com.github.mineinabyss.shoppy.shops.Want;

public class EditorLayout extends HistoryLayout {
    EditTradeGUI editTradeGUI;

    public EditorLayout(EditTradeGUI editTradeGUI) {
        super(editTradeGUI.getHolder());
        this.editTradeGUI = editTradeGUI;
    }

    protected void addToTrade(ShoppyType element) {
        Trade trade = editTradeGUI.getTrade();
        if (element instanceof Want && !trade.getWants().contains(element))
            trade.getWants().add(((Want) element));
        else if (element instanceof Reward && !trade.getRewards().contains(element))
            trade.getRewards().add(((Reward) element));
    }

    protected void addAndGoBack(ShoppyType element){
        addToTrade(element);
        editTradeGUI.update();
        backInHistory();
    }
}
