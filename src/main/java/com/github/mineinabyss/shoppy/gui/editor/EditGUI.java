package com.github.mineinabyss.shoppy.gui.editor;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.gui.HistoryGuiHolder;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Trade;
import com.github.mineinabyss.shoppy.shops.ShoppyMatter;
import com.github.mineinabyss.shoppy.shops.Want;
import de.erethon.headlib.HeadLib;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class EditGUI extends HistoryGuiHolder {
    private Shop shop;

    public EditGUI(Player player, Shop shop) {
        super(6, ChatColor.GREEN + "Shop Editor", Shoppy.getInstance());
        this.player = player;
        this.shop = shop;

        setElement(buildMain());
    }

    private Layout buildMain() {
        Layout layout = new Layout();

        //Scrolling pallet of all trades
        ScrollingPallet pallet = new ScrollingPallet(9);
        shop.getTrades().forEach(trade -> {
            ClickableElement tradeButton = new ClickableElement(Cell.forItemStack(trade.getDisplayItem()));
            tradeButton.setClickAction(clickEvent -> setElement(new EditTradeGUI(this, shop, trade)));
            pallet.addTool(tradeButton);
        });

        layout.addElement(0, 0, pallet);

        addBackButton(layout);
        return layout;
    }
}
