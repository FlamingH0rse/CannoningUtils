package me.flaming.utils;

import me.flaming.PluginMain;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerVar {
    public static void setVar (Player player, String key, PersistentDataType type, Object value) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (value instanceof Double)
            playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (double) value);
        else if (value instanceof Boolean)
            playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (boolean) value);
        else if (value instanceof Integer)
            playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (int) value);
        else if (value instanceof Float)
            playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (float) value);
        else if (value instanceof String)
            playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (String) value);
        else if (value.getClass().isArray()) {
            if (value.getClass().getComponentType().equals(double.class))
                playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (double[]) value);
            else
                playerData.set(new NamespacedKey(PluginMain.getPlugin(), key), type, (int[]) value);

        }
    }

    public static Object getVar(Player player, String key, PersistentDataType type) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        return playerData.get(new NamespacedKey(PluginMain.getPlugin(), key), type);

    }


    private static <T, Z> void set(Player player, String key, PersistentDataType<T, Z> type, Z value) {
        NamespacedKey namespacedKey = new NamespacedKey(PluginMain.getPlugin(), key);
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        dataContainer.set(namespacedKey, type, value);
    }

    public static void set(Player player, String key, String value) {
        set(player, key, PersistentDataType.STRING, value);
    }

    public static void set(Player player, String key, int value) {
        set(player, key, PersistentDataType.INTEGER, value);
    }

    public static void set(Player player, String key, double value) {
        set(player, key, PersistentDataType.DOUBLE, value);
    }

    public static void set(Player player, String key, long value) {
        set(player, key, PersistentDataType.LONG, value);
    }

    public static void set(Player player, String key, byte value) {
        set(player, key, PersistentDataType.BYTE, value);
    }

    public static void set(Player player, String key, float value) {
        set(player, key, PersistentDataType.FLOAT, value);
    }

    private <T, Z> Z get(Player player, String key, PersistentDataType<T, Z> type, Z defaultValue) {
        NamespacedKey namespacedKey = new NamespacedKey(PluginMain.getPlugin(), key);
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        return dataContainer.getOrDefault(namespacedKey, type, defaultValue);
    }

    public String getString(Player player, String key, String defaultValue) {
        return get(player, key, PersistentDataType.STRING, defaultValue);
    }

    public int getInt(Player player, String key, int defaultValue) {
        return get(player, key, PersistentDataType.INTEGER, defaultValue);
    }

    public double getDouble(Player player, String key, double defaultValue) {
        return get(player, key, PersistentDataType.DOUBLE, defaultValue);
    }

    public long getLong(Player player, String key, long defaultValue) {
        return get(player, key, PersistentDataType.LONG, defaultValue);
    }

    public byte getByte(Player player, String key, byte defaultValue) {
        return get(player, key, PersistentDataType.BYTE, defaultValue);
    }

    public float getFloat(Player player, String key, float defaultValue) {
        return get(player, key, PersistentDataType.FLOAT, defaultValue);
    }
}
