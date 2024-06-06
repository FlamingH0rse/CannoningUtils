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

public class EntityExplodeListener implements Listener {
    private PluginMain main;
    public EntityExplodeListener(PluginMain main) {
        this.main = main;
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();

        List<Block> blocks = e.blockList();
        e.setYield(0f);

        List<Block> safeBlocks = new ArrayList<>();
        for (Block block : blocks) {
            for (int y = -64; y < 320; y++) {
                Block currentBlock = world.getBlockAt(block.getX(), y, block.getZ());
                if (currentBlock.getType() == Material.EMERALD_BLOCK) {
                    safeBlocks.add(block);
                    break;
                }
            }
        }

        for (Block safeBlock : safeBlocks) {
            blocks.remove(safeBlock);
        }

    }
}
