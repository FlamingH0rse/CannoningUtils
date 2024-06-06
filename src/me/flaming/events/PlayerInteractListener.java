package me.flaming.events;

import me.flaming.PluginMain;
import me.flaming.utils.PlayerVar;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractListener implements Listener {
    private PluginMain main;
    public PlayerInteractListener(PluginMain main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();

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
