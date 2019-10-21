package com.github.mineinabyss.shoppy.gui.shop;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.gui.HistoryLayout;
import com.github.mineinabyss.shoppy.shops.Trade;
import de.erethon.headlib.HeadLib;
import org.bukkit.entity.Player;

public class ShopTradeGUI extends HistoryLayout {
    private FillableElement wants = new FillableElement(5, 2);
    private FillableElement rewards = new FillableElement(5, 1);
    private Player player;
    private Trade trade;
    private ClickableElement tradeAllow;
    private Element tradeDeny = new ClickableElement(Cell.forItemStack(HeadLib.RED_X.toItemStack("Cannot trade")));;
    private SwappableElement tradeButton;

    public ShopTradeGUI(ShopGUI holder, Player player, Trade trade) {
        super(holder);
        this.player = player;
        this.trade = trade;

        //wants
        createWants();
        addElement(1, 1, wants);

        //rewards
        trade.getRewards().forEach(reward -> {
            rewards.addElement(reward.toElement());
        });
        addElement(6, 1, rewards);

        //trade button
        createTradeAllow();
        tradeButton = new SwappableElement(tradeDeny);
        updateTradeButton();
        addElement(4, 1, tradeButton);

        addBackButton();
    }

    private Element getUsable(boolean bool) {
        if (bool)
            return Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "\u2714");
        return Cell.forItemStack(HeadLib.RED_X.toItemStack(), "X");
    }

    private void createWants() {
        trade.getWants().forEach(want -> {
            wants.addElement(want.toElement());
            wants.addElement(getUsable(want.conditionMet(player)));
        });
    }

    private void updateTradeButton() {
        if (trade.conditionsMet(player))
            tradeButton.swap(tradeAllow);
        else
            tradeButton.swap(tradeDeny);
    }

    private void createTradeAllow() {
        tradeAllow = new ClickableElement(Cell.forItemStack(HeadLib.WOODEN_ARROW_RIGHT.toItemStack("Trade")));
        tradeAllow.setClickAction(clickEvent -> {
            if (trade.trade(player)) {
                wants.clear();
                createWants();
                updateTradeButton();
            }
        });
    }
}
