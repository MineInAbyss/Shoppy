package com.github.mineinabyss.shoppy.gui;

import com.derongan.minecraft.guiy.gui.*;
import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.commands.GUICommandExecutor;
import com.github.mineinabyss.shoppy.shops.Shop;
import com.github.mineinabyss.shoppy.shops.Trade;
import com.github.mineinabyss.shoppy.shops.item.RewardItem;
import com.github.mineinabyss.shoppy.shops.item.WantItem;
import de.erethon.headlib.HeadLib;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainGUI extends GuiHolder {
    private Player player;
    private Shoppy plugin;
    private List<Layout> history = new ArrayList<>();
    private ClickableElement back;

    public MainGUI(Player player, Shoppy plugin) {
        super(6, "Shoppy", plugin);
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

        ClickableElement edit = new ClickableElement(Cell.forItemStack(HeadLib.QUARTZ_E.toItemStack(), "Edit"));
        edit.setClickAction(clickEvent -> editGUI());
        layout.addElement(1, 0, edit);

        addBackButton(layout);
        return layout;
    }

    public void editGUI() {
        if (player.hasPermission("shoppy.edit")) {
            player.closeInventory();
            plugin.getContext().beginEditSearch(player);
        } else
            player.sendMessage(GUICommandExecutor.noPermission);
    }

    public void createShop() {
        if (player.hasPermission("shoppy.create")) {
            Villager villager = (Villager) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
            villager.addScoreboardTag("shoppy");
            villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            villager.setInvulnerable(true);
            Shop shop = new Shop(villager.getUniqueId()).register();
            shop.addTrade(new Trade(new ItemStack(Material.DIAMOND_SWORD), new WantItem(new ItemStack(Material.STONE, 10)), new RewardItem(new ItemStack(Material.DIRT, 10))));
            player.sendMessage(ChatColor.GREEN + "Created shop!");
        } else
            player.sendMessage(GUICommandExecutor.noPermission);
    }
}
