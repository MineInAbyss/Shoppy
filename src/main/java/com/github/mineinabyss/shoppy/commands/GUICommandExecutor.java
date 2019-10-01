package com.github.mineinabyss.shoppy.commands;

import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.gui.CreateShopGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GUICommandExecutor implements CommandExecutor {
    private ShoppyContext context;

    public GUICommandExecutor(ShoppyContext context) {
        this.context = context;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (label.equals("shoppy")) {
                if(args.length == 0)
                    player.sendMessage(ChatColor.RED + "Please enter an argument");
                else if(args[0].equalsIgnoreCase("create"))
                    new CreateShopGUI(player, JavaPlugin.getPlugin(Shoppy.class)).show(player);
                return true;
            }
        }

        return false;
    }
}
