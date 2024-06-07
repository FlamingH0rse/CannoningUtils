package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.NBTEditor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class SandwandCommand implements TabExecutor {
    private final PluginMain main;
    private final ColorUtils color;
    private final NBTEditor nbtEditor;
    public SandwandCommand(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
        nbtEditor = new NBTEditor();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        // Sandwand Item
        ItemStack sandWandItem = new ItemStack(Material.BONE, 1);

        NBTEditor.enchant(sandWandItem, Enchantment.ARROW_INFINITE, 1, true, true);
        NBTEditor.rename(sandWandItem, "&e&l&oSAND WAND");
        NBTEditor.set(sandWandItem, "custom-item-name", "sand-wand");

        // Add item to inventory
        p.getInventory().addItem(sandWandItem);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
