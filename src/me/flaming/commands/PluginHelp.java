package me.flaming.commands;

import me.flaming.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import java.util.ArrayList;
import java.util.List;

public class PluginHelp implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        String m = """
                &6========== &eCannoningUtils Help &6==========
                &a/help &f- &7Show this help message
                &a/fire &f- &7Presses the button
                &a/lever start &f- &7Toggles lever
                &a/sandwand stop &f- &7Gives sandwand that is used to clear sand stacks
                &a/tntfill [radius] [amount] reload &f- &7Fills dispensers with tnt
                &a/tntclear [radius] <val> &f- &7Clears tnt from dispensers
                &6=========================================
                """;

        sender.sendMessage(ColorUtils.getColored(m));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
