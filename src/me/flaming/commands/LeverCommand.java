package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class LeverCommand implements TabExecutor {
    private final PluginMain main;
    public LeverCommand(PluginMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        World world = p.getWorld();

        int[] coords = PlayerVar.get(p, "lever-loc", PersistentDataType.INTEGER_ARRAY, new int[]{});

        // Check if previous lever data exists
        if (coords.length == 0) {
            ColorUtils.send(p, "&cNo lever history found.");
            return true;
        }

        // Check if lever block exists at the co-ordinates
        Block block = world.getBlockAt(coords[0], coords[1], coords[2]);
        if (block.getType() != Material.LEVER) {
            ColorUtils.send(p, "&cThe flicked lever was removed.");
            return true;
        }

        // Toggle lever
        Powerable data = (Powerable) block.getBlockData();
        data.setPowered(!data.isPowered());
        block.setBlockData(data);

        ColorUtils.send(p, "&aLever switched!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
