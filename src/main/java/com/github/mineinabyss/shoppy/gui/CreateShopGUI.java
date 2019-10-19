package com.github.mineinabyss.shoppy.gui;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Trade;
import com.github.mineinabyss.shoppy.shops.rewards.RewardItem;
import com.github.mineinabyss.shoppy.shops.wants.WantItem;
import de.erethon.headlib.HeadLib;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CreateShopGUI extends GuiHolder {
    private Player player;
    private Shoppy plugin;
    private List<Layout> history = new ArrayList<>();
    private ClickableElement back;

    public CreateShopGUI(Player player, Shoppy plugin) {
        super(6, "Create Shop", plugin);
        this.plugin = plugin;
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

        ClickableElement name = new ClickableElement(Cell.forItemStack(HeadLib.CHECKMARK.toItemStack(), "Create"));
        name.setClickAction(clickEvent -> createShop());
        layout.addElement(0, 0, name);

        addBackButton(layout);
        return layout;
    }

    private void createShop() {
        Villager villager = (Villager) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        villager.addScoreboardTag("shoppy");
        Shop shop = new Shop(villager.getUniqueId());
        shop.addTrade(new Trade(new ItemStack(Material.DIAMOND_SWORD), new WantItem(new ItemStack(Material.STONE, 10)), new RewardItem(new ItemStack(Material.DIRT, 10))));
        Shoppy.getInstance().getContext().addShop(shop);
    }
}
