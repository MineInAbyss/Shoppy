package com.github.mineinabyss.shoppy.gui.shop;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.gui.HistoryGuiHolder;
import com.github.mineinabyss.shoppy.shops.Shop;
import de.erethon.headlib.HeadLib;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI extends HistoryGuiHolder {
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

    private Layout buildMain() {
        Layout layout = new Layout();
        ScrollingPallet pallet = new ScrollingPallet(9);

        shop.getTrades().forEach(trade -> {
            ClickableElement tradeButton = new ClickableElement(Cell.forItemStack(trade.getDisplayItem()));
            tradeButton.setClickAction(clickEvent -> setElement(new ShopTradeGUI(this, player, trade)));
            pallet.addTool(tradeButton);
        });

        layout.addElement(0, 0, pallet);

        addBackButton(layout);
        return layout;
    }
}
