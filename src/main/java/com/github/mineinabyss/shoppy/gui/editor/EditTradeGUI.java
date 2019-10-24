package com.github.mineinabyss.shoppy.gui.editor;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.gui.HistoryLayout;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.ShoppyType;
import com.github.mineinabyss.shoppy.shops.Trade;
import de.erethon.headlib.HeadLib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditTradeGUI extends HistoryLayout {
    FillableElement wants = new FillableElement(5, 2);
    FillableElement rewards = new FillableElement(5, 2);
    private Shop shop;

    public Trade getTrade() {
        return trade;
    }

    private Trade trade;
    private FillableElement types = new FillableElement(5, 9);
    private Map<ClickableElement, ShoppyType> removeMap = new HashMap<>();
    ShoppyContext context;

    public EditTradeGUI(EditGUI holder, Shop shop, Trade trade) {
        super(holder);
        this.shop = shop;
        this.trade = trade;
        context = Shoppy.getInstance().getContext();

        update();

        //container for icon
        ContainerElement icon = new ContainerElement(1, 1, null, null);
        icon.addElement(Cell.forItemStack(trade.getDisplayItem()));
        addElement(6, 5, icon);

        //save button
        ClickableElement save = new ClickableElement(Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "Save to file"));
        save.setClickAction(clickEvent -> {
            shop.save();
            trade.setDisplayItem(((Cell) icon.getElement(0, 0)).itemStack);
            backInHistory();
            holder.update();
        });
        addElement(0, 5, save);

        //delete button
        ClickableElement delete = new ClickableElement(Cell.forItemStack(HeadLib.PLAIN_RED.toItemStack(), "Delete trade"));
        delete.setClickAction(clickEvent -> {
            shop.getTrades().remove(trade);
            holder.update();
            backInHistory();
        });
        addElement(7, 5, delete);

        addBackButton();
    }

    private void createMatter(List<? extends ShoppyType> matters, FillableElement fillable, boolean isWant) {
        matters.forEach(matter -> {
            ClickableElement matterElement = matter.toClickableElement();
            matterElement.setClickAction(clickEvent -> holder.setElement(matter.buildEditLayout(this)));
            fillable.addElement(matterElement);

            //remove buttons
            ClickableElement remove = new ClickableElement(Cell.forItemStack(HeadLib.WOODEN_MINUS.toItemStack(), "Remove"));
            removeMap.put(remove, matter);
            remove.setClickAction(clickEvent -> {
                trade.remove(removeMap.get(remove));
                update();
            });
            fillable.addElement(remove);
        });

        //add button
        ClickableElement add = new ClickableElement(Cell.forItemStack(HeadLib.WOODEN_PLUS.toItemStack(), "Add"));
        add.setClickAction(clickEvent -> holder.setElement(buildAddMatterLayout(isWant)));
        fillable.addElement(add);
    }

    /**
     * @param isWant whether we want to build this for wants (true) or rewards (false)
     * @return a layout with a list of wants or rewards
     */
    public Layout buildAddMatterLayout(boolean isWant) {
        types.clear();
        Layout layout = new Layout();
        //TODO repeat less code
        if (isWant)
            context.getWants().forEach(want -> {
                ClickableElement addType = new ClickableElement(Cell.forItemStack(want.getEditGUIAddItem()));
                addType.setClickAction(clickEvent -> {
                    backInHistory();
                    holder.setElement(want.buildEditLayout(this));
                });
                types.addElement(addType);
            });
        else
            context.getRewards().forEach(reward -> {
                ClickableElement addType = new ClickableElement(Cell.forItemStack(reward.getEditGUIAddItem()));
                addType.setClickAction(clickEvent -> {
                    backInHistory();
                    holder.setElement(reward.buildEditLayout(this));
                });
                types.addElement(addType);
            });

        layout.addElement(0, 0, types);
        addBackButton(layout);
        return layout;
    }

    public void update() {
        types.clear();
        //wants
        wants.clear();
        createMatter(trade.getWants(), wants, true);
        addElement(1, 1, wants);

        //rewards
        rewards.clear();
        createMatter(trade.getRewards(), rewards, false);
        addElement(6, 1, rewards);
    }
}
