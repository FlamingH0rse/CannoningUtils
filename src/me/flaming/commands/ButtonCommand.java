package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Switch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public class ButtonCommand implements TabExecutor {
    private final PluginMain main;
    public ButtonCommand(PluginMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        World world = p.getWorld();

        int[] coords = PlayerVar.get(p, "button-loc", PersistentDataType.INTEGER_ARRAY, new int[]{});

        // Check if previous button data exists
        if (coords.length == 0) {
            ColorUtils.send(p, "&cNo button history found.");
            return true;
        }

        // Check if button block exists at the co-ordinates
        Block block = world.getBlockAt(coords[0], coords[1], coords[2]);
        if (block.getType() == Material.LEVER || !(block.getBlockData() instanceof Switch)) {
            ColorUtils.send(p, "&cThe clicked button was removed.");
            return true;
        }

        // Click button
        int ticksPressed = (block.getType() == Material.STONE_BUTTON) ? 20 : 30;

        Powerable data = (Powerable) block.getBlockData();
        data.setPowered(true); block.setBlockData(data);

        // Un-click button
        new BukkitRunnable() {
            @Override
            public void run() {
                data.setPowered(false); block.setBlockData(data);
            }
        }.runTaskLater(main, ticksPressed);

        ColorUtils.send(p, "&aFired!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
