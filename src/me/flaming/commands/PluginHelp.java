package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PluginHelp implements TabExecutor {
    private final PluginMain main;
    public PluginHelp (PluginMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player p = (Player) sender;

        String m = """
                &6========== &eMyPlugin Help &6==========
                &a/help &f- &7Show this help message
                &a/fire &f- &7Presses the last button
                &a/lever start &f- &7Toggles last lever
                &a/sandwand stop &f- &7Gives a sandwand that is used to clear sand stacks
                &a/tntfill [radius] [amount] &f- &7Fills dispensers with TnT
                &a/tntclear [radius] &f- &7Clears TnT from dispensers
                &6===================================
                """;

        ColorUtils.send(p, m);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
