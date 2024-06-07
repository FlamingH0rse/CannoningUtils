package me.flaming.events;

import me.flaming.PluginMain;
import me.flaming.utils.NBTEditor;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Switch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.generator.WorldInfo;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PlayerInteractListener implements Listener {
    private PluginMain main;
    private final NBTEditor nbtEditor;
    public PlayerInteractListener(PluginMain main) {
        this.main = main;
        nbtEditor = new NBTEditor();
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        World world = p.getWorld();

        Block block = e.getClickedBlock();
        ItemStack handItem = e.getItem();


        // Sand wand and tick counter listener
        if (handItem != null) {
            Object customItemName = nbtEditor.getData(handItem, "custom-item-name", PersistentDataType.STRING);

            e.setCancelled(true);

            // Sand wand
            if (Objects.equals(customItemName, "sand-wand")) {
                Block targetBlock = e.getPlayer().getTargetBlock(null,100);

                // Iterate over the whole block column targeted
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {
                    int targetX = targetBlock.getX();
                    int targetZ = targetBlock.getZ();
                    Block currentBlock = world.getBlockAt(targetX, y, targetZ);

                    // Delete all gravity blocks
                    List<Material> gravityBlocks = new ArrayList<>(List.of(Material.SAND, Material.RED_SAND, Material.GRAVEL, Material.ANVIL));
                    gravityBlocks.addAll(Arrays.stream(Material.values()).filter(mat -> mat.toString().toLowerCase().endsWith("concrete_powder")).toList());

                    if (gravityBlocks.contains(currentBlock.getType())) {
                        currentBlock.setType(Material.AIR);
                    }
                }
            }

            // Tick counter
            if (Objects.equals(customItemName, "tick-counter")) {

            }
        }

        // Button and lever interaction
        if (block != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // Save lever location
            if (block.getType() == Material.LEVER) {
                PlayerVar.setVar(e.getPlayer(), "lever-loc", PersistentDataType.INTEGER_ARRAY, new int[]{block.getX(), block.getY(), block.getZ()});
            }
            // Save button location
            else if (block.getBlockData() instanceof Switch) {
                PlayerVar.setVar(e.getPlayer(), "button-loc", PersistentDataType.INTEGER_ARRAY, new int[]{block.getX(), block.getY(), block.getZ()});
            }
        }
    }
}
