package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.NBTEditor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TickCounterCommand implements TabExecutor {
    private final PluginMain main;
    public TickCounterCommand(PluginMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        // Tick-counter Item
        ItemStack TCItem = new ItemStack(Material.BLAZE_ROD, 1);

        NBTEditor.enchant(TCItem, Enchantment.ARROW_INFINITE, 1, true, true);
        NBTEditor.rename(TCItem, "&4&l&oTICK COUNTER");
        NBTEditor.set(TCItem, "custom-item-name", "tick-counter");

        // Add item to inventory
        p.getInventory().addItem(TCItem);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
