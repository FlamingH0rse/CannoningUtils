package me.flaming.events;

import me.flaming.PluginMain;
import me.flaming.utils.ColorUtils;
import me.flaming.utils.NBTEditor;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.Repeater;
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
    private final ColorUtils color;
    private final NBTEditor nbtEditor;
    public PlayerInteractListener(PluginMain main) {
        this.main = main;
        color = new ColorUtils();
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
            String customItemName = NBTEditor.getString(handItem, "custom-item-name", "");

            // Sand wand
            if (customItemName.equals("sand-wand")) {
                e.setCancelled(true);
                Block targetBlock = e.getPlayer().getTargetBlock(null,100);

                // Iterate over the whole block column targeted till bottom
                int sandCount = 0;
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {
                    int targetX = targetBlock.getX();
                    int targetZ = targetBlock.getZ();
                    Block currentBlock = world.getBlockAt(targetX, y, targetZ);

                    // Delete all gravity blocks
                    List<Material> gravityBlocks = new ArrayList<>(List.of(Material.SAND, Material.RED_SAND, Material.GRAVEL, Material.ANVIL));
                    gravityBlocks.addAll(Arrays.stream(Material.values()).filter(mat -> mat.toString().toLowerCase().endsWith("concrete_powder")).toList());

                    if (gravityBlocks.contains(currentBlock.getType())) {
                        currentBlock.setType(Material.AIR);
                        sandCount++;
                    }
                }
                if (sandCount > 0)
                    color.send(p, "&eCleared " + sandCount + " blocks");
                return;
            }

            // Tick counter
            if (customItemName.equals("tick-counter")) {
                e.setCancelled(true);
                List<Material> tickableItems = List.of(
                        Material.REPEATER,
                        Material.COMPARATOR,
                        Material.PISTON,
                        Material.STICKY_PISTON,
                        Material.REDSTONE_TORCH,
                        Material.OBSERVER
                );

                if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block != null && tickableItems.contains(block.getType())) {
                    // Get tick counter
                    double tickCount = PlayerVar.getDouble(p, "r-tick-count", 0.0d);
                    boolean priority = PlayerVar.getBoolean(p, "r-priority", false);


                    // Check tick counter
                    if (block.getType() == Material.REPEATER)
                        tickCount += ((Repeater) block.getBlockData()).getDelay();

                    else if (block.getBlockData() instanceof Piston)
                        tickCount += 1.25;

                    else
                        tickCount++;

                    // Check priority counter
                    if (block.getType() == Material.COMPARATOR) priority = true;

                    // Update player variables
                    PlayerVar.set(p, "r-tick-count", tickCount);
                    PlayerVar.set(p, "r-priority", priority);

                    // Send update
                    String tickMsg = getTickMessage(tickCount, priority);
                    color.send(p, "&aTick count: " + tickMsg);

                }
                else if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
                    // Reset tick counter
                    PlayerVar.set(p, "r-tick-count", 0.0d);
                    PlayerVar.set(p, "r-priority", false);

                    // Send update
                    color.send(p, "&aReset tick counter");
                }
            }
        }

        // Button and lever interaction
        if (block != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // Save lever location
            if (block.getType() == Material.LEVER) {
                int[] coords = new int[]{block.getX(), block.getY(), block.getZ()};
                PlayerVar.set(p, "lever-loc", PersistentDataType.INTEGER_ARRAY, coords);
            }
            // Save button location
            else if (block.getBlockData() instanceof Switch) {
                int[] coords = new int[]{block.getX(), block.getY(), block.getZ()};
                PlayerVar.set(p, "button-loc", PersistentDataType.INTEGER_ARRAY, coords);
            }
        }
    }

    private static String getTickMessage(double rTicks, boolean comparator) {
        double gTicks = rTicks * 2;
        double seconds = gTicks/20;

        return rTicks + "&a RT &8|&a " + gTicks + " GT &8|&a " + seconds + "&a seconds";
    }
}
