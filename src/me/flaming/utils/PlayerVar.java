package me.flaming.utils;

import me.flaming.PluginMain;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerVar {
    public static void setVar (Player player, String key, PersistentDataType type, Object value) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (int[]) value);
    }

    public static Object getVar(Player player, String key, PersistentDataType type) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        return playerData.get(new NamespacedKey(PluginMain.getPlugin(), key), type);

    }
}
