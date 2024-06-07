package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.NBTEditor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;

public class TickCounterCommand implements TabExecutor {
    private final PluginMain main;
    private final ColorUtils color;
    private final NBTEditor nbtEditor;
    public TickCounterCommand(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
        nbtEditor = new NBTEditor();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        // Tick-counter Item
        ItemStack TCItem = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta TCItemMeta = TCItem.getItemMeta();

        TCItemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        TCItemMeta.setDisplayName(ColorUtils.getColored("&4&l&oTICK COUNTER"));
        TCItem.setItemMeta(TCItemMeta);

        nbtEditor.setData(TCItem, "custom-item-name", "tick-counter");

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
