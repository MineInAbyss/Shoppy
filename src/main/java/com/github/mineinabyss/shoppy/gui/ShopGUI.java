package com.github.mineinabyss.shoppy.gui;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Trade;
import de.erethon.headlib.HeadLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI extends GuiHolder {
    private Player player;
    private List<Layout> history = new ArrayList<>();
    private ClickableElement back;
    private Shop shop;

    public ShopGUI(Player player, Shop shop) {
        super(6, "Shop", Shoppy.getInstance()); //TODO add shop.getName()
        this.shop = shop;
        this.player = player;

        //create back button
        Element cell = Cell.forItemStack(HeadLib.RED_X.toItemStack("Back"));
        back = new ClickableElement(cell);
        back.setClickAction(clickEvent -> backInHistory());

        setElement(buildMain());
    }

    public Player getPlayer() {
        return player;
    }

    public void addBackButton(Layout layout) {
        history.add(layout);
        layout.addElement(8, 5, back);
    }

    public void backInHistory() {
        if (history.size() <= 1) {
            player.closeInventory();
            return;
        }
        Layout previous = history.get(history.size() - 2);
        history.remove(history.size() - 1);

        setElement(previous);
    }

    private Layout buildMain() {
        Layout layout = new Layout();
        ScrollingPallet pallet = new ScrollingPallet(9);

        shop.getTrades().forEach(trade -> {
            ClickableElement tradeButton = new ClickableElement(Cell.forItemStack(trade.getDisplayItem()));
            tradeButton.setClickAction(clickEvent -> setElement(buildTradeMenu(trade)));
            pallet.addTool(tradeButton);
        });

        layout.addElement(0, 0, pallet);

//        ClickableElement name = new ClickableElement(Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "Create"));
//        layout.addElement(0, 5, name);

        addBackButton(layout);
        return layout;
    }

    private Layout buildTradeMenu(Trade trade) {
        Layout layout = new Layout();

        //wants
        FillableElement wants = new FillableElement(5, 2);
        createWants(trade, wants);
        layout.addElement(0, 1, wants);

        //rewards
        FillableElement rewards = new FillableElement(5, 1);
        trade.getRewards().forEach(reward -> rewards.addElement(Cell.forItemStack(reward.getDisplayItem(), reward.getDisplayName())));
        layout.addElement(6, 1, rewards);

        //trade button
        ClickableElement tradeButton = createTradeButton(layout, trade, wants);
        addBackButton(layout);
        return layout;
    }

    private Element getUsable(boolean bool) {
        if (bool)
            return Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "\u2714");
        return Cell.forItemStack(HeadLib.RED_X.toItemStack(), "X");
    }

    private FillableElement createWants(Trade trade, FillableElement wants) {
        trade.getWants().forEach(want -> {
            wants.addElement(Cell.forItemStack(want.getDisplayItem(), want.getDisplayName()));
            wants.addElement(getUsable(want.conditionMet(player)));
        });
        return wants;
    }

    private ClickableElement createTradeButton(Layout layout, Trade trade, FillableElement wants) {
        ClickableElement tradeButton;
        if (trade.conditionsMet(player)) {
            tradeButton = new ClickableElement(Cell.forItemStack(HeadLib.WOODEN_ARROW_RIGHT.toItemStack(), "Trade"));
            tradeButton.setClickAction(clickEvent -> {
                if (trade.trade(player)) {
                    wants.clear();
                    createWants(trade, wants);
                    //recreate the element TODO if we stop making things immutable this could be done a lot cleaner
                    createTradeButton(layout, trade, wants);
                }
            });
        } else {
            tradeButton = new ClickableElement(Cell.forItemStack(HeadLib.RED_X.toItemStack(), "Cannot trade"));

        }
        layout.addElement(4, 1, tradeButton);
        return tradeButton;
    }
}
