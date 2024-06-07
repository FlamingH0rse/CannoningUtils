package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TntFillCommand implements TabExecutor {
    private final PluginMain main;
    private final ColorUtils color;
    public TntFillCommand(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player p = (Player) sender;
        Location loc = p.getLocation();
        World world = p.getWorld();

        // Parse radius argument
        int radius;
        try {
            if (args.length < 1) radius = 64; // Default radius
            else radius = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage("Enter a valid radius between 1 to 64");
            return false;
        }
        if (radius < 1 || radius > 64) {
            p.sendMessage("Enter a valid radius between 1 to 64");
            return false;
        }

        // Parse amount argument
        int amount;
        try {
            if (args.length < 2) amount = 576; // Default amount
            else amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            p.sendMessage("Enter a valid amount between 1 to 576");
            return false;
        }
        if (amount < 1 || amount > 576) {
            p.sendMessage("Enter a valid amount between 1 to 576");
            return false;
        }

        // Get dispenser locations
        List<Block> dispenserBlocks = new ArrayList<>();
        for (int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; x++) {
            for (int y = loc.getBlockY() - radius; y < loc.getBlockY() + radius; y++) {
                for (int z = loc.getBlockZ() - radius; z < loc.getBlockZ() + radius; z++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.DISPENSER) {
                        dispenserBlocks.add(world.getBlockAt(x, y, z));
                    }
                }
            }
        }

        if (dispenserBlocks.size() == 0) {
            color.send(p, "&4Found no dispensers to fill");
            return true;
        }
        // Fill tnt
        for (Block block : dispenserBlocks) {
            Dispenser disp = (Dispenser) block.getState();
            Inventory inv = disp.getInventory();

            List<ItemStack> itemStacks = getStacks(Material.TNT, amount);

            inv.addItem(itemStacks.toArray(ItemStack[]::new));
        }

        color.send(p, "&aFilled &6&l" + dispenserBlocks.size() + " &adispensers with &6&l" + amount + " &aTNT");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }

    public static List<ItemStack> getStacks (Material mat, int amount) {
        List<ItemStack> list = new ArrayList<>();
        ItemStack fullStack = new ItemStack(mat, 64);

        int fullStackSize = amount/mat.getMaxStackSize();
        int remStackSize = amount%mat.getMaxStackSize();

        for (int i = 0; i < fullStackSize; i++) {
            list.add(fullStack);
        }
        if (remStackSize != 0) list.add(new ItemStack(mat, remStackSize));

        return list;
    }
}
