package me.flaming.events;

import me.flaming.PluginMain;
import me.flaming.utils.NBTEditor;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashSet;

public class PlayerInteractListener implements Listener {
    private PluginMain main;
    private final NBTEditor nbtEditor;
    public PlayerInteractListener(PluginMain main) {
        this.main = main;
        nbtEditor = new NBTEditor();
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        ItemStack handItem = e.getItem();

        // Sand wand and tick counter listener
        if (handItem != null && nbtEditor.getData(handItem, "custom-item-name", PersistentDataType.STRING) != null) {
            String customItemName = (String) nbtEditor.getData(handItem, "custom-item-name", PersistentDataType.STRING);

            e.setCancelled(true);

            if (customItemName == "sand-wand") {
                Block targetBlock = e.getPlayer().getTargetBlock(null,100);

                targetBlock.setType(Material.AIR);
            }
        }

        if (block == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (block.getType().toString().toLowerCase().endsWith("button")) {
            PlayerVar.setVar(e.getPlayer(), "button-loc", PersistentDataType.INTEGER_ARRAY, new int[]{block.getX(), block.getY(), block.getZ()});
        }
        if (block.getType() == Material.LEVER) {
            PlayerVar.setVar(e.getPlayer(), "lever-loc", PersistentDataType.INTEGER_ARRAY, new int[]{block.getX(), block.getY(), block.getZ()});
        }
    }
}
