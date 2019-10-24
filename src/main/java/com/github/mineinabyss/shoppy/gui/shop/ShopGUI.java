package com.github.mineinabyss.shoppy.gui.shop;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.commands.GUICommandExecutor;
import com.github.mineinabyss.shoppy.gui.HistoryGuiHolder;
import com.github.mineinabyss.shoppy.gui.editor.EditGUI;
import com.github.mineinabyss.shoppy.shops.Shop;
import de.erethon.headlib.HeadLib;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI extends HistoryGuiHolder {
    private List<Layout> history = new ArrayList<>();
    private Shop shop;

    public ShopGUI(Player player, Shop shop) {
        super(6, "Shop", Shoppy.getInstance()); //TODO add shop.getName()
        this.shop = shop;
        this.player = player;

        setElement(buildMain());
    }

    private Layout buildMain() {
        Layout layout = new Layout();
        ScrollingPallet pallet = new ScrollingPallet(9);

        //pallet
        shop.getTrades().forEach(trade -> {
            ClickableElement tradeButton = new ClickableElement(Cell.forItemStack(trade.getDisplayItem()));
            tradeButton.setClickAction(clickEvent -> setElement(new ShopTradeGUI(this, player, trade)));
            pallet.addTool(tradeButton);
        });
        layout.addElement(0, 0, pallet);

        //create edit button
        if(player.hasPermission("shoppy.edit")) {
            ClickableElement edit = new ClickableElement(Cell.forItemStack(HeadLib.QUARTZ_E.toItemStack("Edit")));
            edit.setClickAction(clickEvent -> new EditGUI(player, shop).show(player));
            layout.addElement(7, 5, edit);
        }

        addBackButton(layout);
        return layout;
    }
}
