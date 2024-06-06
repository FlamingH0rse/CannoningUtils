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

public class TntClearCommand implements TabExecutor {
    private final PluginMain main;
    private final ColorUtils color;
    public TntClearCommand(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Location loc = p.getLocation();
        World world = p.getWorld();

        // Parse radius argument
        int radius;
        try {
            radius = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage("Enter a valid radius between 1 to 64");
            return false;
        }
        if (radius < 1 || radius > 64) {
            p.sendMessage("Enter a valid radius between 1 to 64");
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

        // Fill tnt
        for (Block block : dispenserBlocks) {
            Dispenser disp = (Dispenser) block.getState();
            Inventory inv = disp.getInventory();

            inv.clear();
        }

        color.send(p, "&aSuccessfully cleared dispensers in radius of &6&l" + radius);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
