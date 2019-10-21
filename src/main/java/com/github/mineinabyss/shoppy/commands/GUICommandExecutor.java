package com.github.mineinabyss.shoppy.commands;

import com.github.mineinabyss.shoppy.Shoppy;
import com.github.mineinabyss.shoppy.ShoppyContext;
import com.github.mineinabyss.shoppy.gui.MainGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GUICommandExecutor implements CommandExecutor, TabCompleter {
    public static final String noPermission = ChatColor.RED + "You don't have permission to do this";
    private ShoppyContext context;

    public GUICommandExecutor(ShoppyContext context) {
        this.context = context;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (label.equals("shoppy")) {
                if(!player.hasPermission("shoppy.gui")){
                    player.sendMessage(noPermission);
                    return true;
                }
                MainGUI gui = new MainGUI(player, JavaPlugin.getPlugin(Shoppy.class));
                if (args.length == 0)
                    gui.show(player);
                else if (args[0].equalsIgnoreCase("create"))
                    gui.createShop();
                else if (args[0].equalsIgnoreCase("edit"))
                    gui.editGUI();
                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("shoppy"))
            return Collections.emptyList();

        if (args.length <= 1)
            return Stream.of("create", "edit")
                    .filter(a -> a.startsWith(args[0]))
                    .collect(toList());
        return Collections.emptyList();
    }
}
