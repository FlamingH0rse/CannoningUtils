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

import java.util.ArrayList;
import java.util.List;

public class TntClearCommand implements TabExecutor {
    private final PluginMain main;
    public TntClearCommand(PluginMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Location loc = p.getLocation();
        World world = p.getWorld();

        // Parse radius argument
        int radius;
        try {
            if (args.length == 0) radius = 64; // Default radius
            else radius = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage("Enter a valid radius between 1 to 64");
            return true;
        }
        if (radius < 1 || radius > 64) {
            p.sendMessage("Enter a valid radius between 1 to 64");
            return true;
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

        // No dispensers found
        if (dispenserBlocks.size() == 0) {
            ColorUtils.send(p, "&4Found no dispensers to clear");
            return true;
        }

        // Fill tnt
        for (Block block : dispenserBlocks) {
            Dispenser disp = (Dispenser) block.getState();
            Inventory inv = disp.getInventory();

            inv.clear();
        }

        ColorUtils.send(p, "&aCleared &6&l" + dispenserBlocks.size() + " &adispensers");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
