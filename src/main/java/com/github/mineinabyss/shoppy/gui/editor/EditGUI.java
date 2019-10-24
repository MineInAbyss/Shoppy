package com.github.mineinabyss.shoppy.gui.editor;

import com.derongan.minecraft.guiy.gui.Cell;
import com.derongan.minecraft.guiy.gui.ClickableElement;
import com.derongan.minecraft.guiy.gui.Layout;
import com.derongan.minecraft.guiy.gui.ScrollingPallet;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.gui.HistoryGuiHolder;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Trade;
import de.erethon.headlib.HeadLib;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EditGUI extends HistoryGuiHolder {
    private Shop shop;
    ScrollingPallet pallet = new ScrollingPallet(9);

    public EditGUI(Player player, Shop shop) {
        super(6, ChatColor.GREEN + "Shop Editor", Shoppy.getInstance());
        this.player = player;
        this.shop = shop;

        setElement(buildMain());
    }

    private Layout buildMain() {
        Layout layout = new Layout();

        update();

        layout.addElement(0, 0, pallet);

        //add trade button
        ClickableElement addTrade = new ClickableElement(Cell.forItemStack(HeadLib.WOODEN_PLUS.toItemStack(), "Add trade"));
        addTrade.setClickAction(clickEvent -> {
            Trade trade = new Trade();
            trade.setDisplayItem(HeadLib.STONE_QUESTION_MARK.toItemStack("NEW TRADE"));
            shop.addTrade(trade);
            update();
        });
        layout.addElement(7, 5, addTrade);

        addBackButton(layout);
        return layout;
    }

    public void makePallet() {
        shop.getTrades().forEach(trade -> {
            ClickableElement tradeButton = new ClickableElement(Cell.forItemStack(trade.getDisplayItem()));
            tradeButton.setClickAction(clickEvent -> setElement(new EditTradeGUI(this, shop, trade)));
            pallet.addTool(tradeButton);
        });
    }

    public void update() {
        pallet.clear();
        makePallet();

    }
}
