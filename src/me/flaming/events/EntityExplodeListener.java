package me.flaming.events;

import me.flaming.PluginMain;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityExplodeListener implements Listener {
    private final PluginMain main;
    public EntityExplodeListener(PluginMain main) {
        this.main = main;
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();

        List<Block> affectedBlocks = e.blockList();
        e.setYield(0f);

        // Remove the protected blocks from affected blocks
        affectedBlocks.removeIf(b -> {
            for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {

                Block currentBlock = world.getBlockAt(b.getX(), y, b.getZ());

                if (currentBlock.getType() == Material.EMERALD_BLOCK) return true;
            }
            return false;
        });
    }
}
