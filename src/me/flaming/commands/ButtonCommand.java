package me.flaming.commands;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.PlayerVar;
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

public class ButtonCommand implements TabExecutor {
    private final PluginMain main;
    private final ColorUtils color;
    public ButtonCommand(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        World world = p.getWorld();

        int[] coords = (int[]) PlayerVar.getVar(p, "button-loc", PersistentDataType.INTEGER_ARRAY);
        if (coords == null) {
            color.send(p, "&cNo button history found.");
            return false;
        }

        Block block = world.getBlockAt(coords[0], coords[1], coords[2]);
        if (!block.getType().toString().toLowerCase().endsWith("button")) {
            color.send(p, "&cThe clicked button was removed.");
            return false;
        }

        Powerable data = (Powerable) block.getBlockData();
        data.setPowered(!data.isPowered());

        color.send(p, "&aFired!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        return arguments;
    }
}
